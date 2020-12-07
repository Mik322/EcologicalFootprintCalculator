package consoleinterface.footprint

import consoleinterface.DateChoice.getUserDate
import consoleinterface.UserChoice
import consoleinterface.UserChoice.{AddCar, AddTransportTrip, AddWaste, SetElectricitySources}
import consoleinterface.footprint.inputs.TransportationInput.fuelInput
import main.footprint.energy.TypeOfElectricitySource
import main.footprint.energy.TypeOfElectricitySource._
import main.footprint.transport.TransportMean._
import main.footprint.transport.{Car, TransportMean}
import main.footprint.waste.TypeOfWaste.{Food, Recycled}

import scala.io.StdIn.readLine

object FootPrintConsoleOps{

  def addCar(): UserChoice ={
    println("Type your car name:")
    val name = readLine()
    println("Type your car consumption:")
    val consumption = readLine().toDouble
    val fuel = fuelInput()
    AddCar(name, consumption, fuel)
  }

  def addTransportTrip(cars: List[Car]): UserChoice ={
    println("Select Mean of Transport:\n1.Car\n2.Plane\n3.Bus\n4.Subway\n5.Train")
    val input = readLine().toInt
    val mean = getTransportMean(input, cars)
    println("Type number of Km:")
    val km = readLine().toDouble
    println("Date of the trip:")
    val date = getUserDate()
    AddTransportTrip(mean,km,date)
  }

  def getTransportMean(mean: Int, cars: List[Car]): TransportMean = mean match{
    case 1 => {
      println("Enter your car:")
      println(cars.map(c => s"${c.name}").mkString("\n"))
      val input = readLine()
      val car = cars.find(car => input == car.name)
      car match {
        case None => cars(0)
        case Some(value) => value
      }
    }
    case 2 => Plane
    case 3 => Bus
    case 4 => SubWay
    case 5 => Train
  }

  def addFoodWaste(): UserChoice ={
    println("How much Kg of food waste do you produce a week?")
    val kg = readLine().toInt
    AddWaste(kg,Food)
  }

  def addRecycledWaste(): UserChoice ={
    println("How much Kg of recycled waste do you produce a week?")
    val kg = readLine().toInt
    AddWaste(kg,Recycled)
  }

  def setEnergySources(): UserChoice ={
    def loop(sources: List[(TypeOfElectricitySource, Double)], totalPercentage: Double): List[(TypeOfElectricitySource, Double)] = totalPercentage match {
      case t if t< 1.0 =>
        println(s"You need to declare ${(1.0-t) * 100}% of your electricity sources")
        val (source, percentage) = getEnergySourceType(1.0 - t)
        loop(sources.appended((source, percentage)), totalPercentage + percentage)
      case t if t >=1.0 && t <= 1.001 => sources
    }
    SetElectricitySources(loop(List(), 0.0))
  }

  def getEnergySourceType(maximum: Double): (TypeOfElectricitySource, Double) ={
    println("Select one of the sources that appears on the electricity bill: \n1.Gas\n2.Oil\n3.Coal\n4.Biomass\n5.Hydro\n6.Solar\n7.Wind\n8.Nuclear")
    val sourceType = readLine().toInt match {
      case 1 => Gas
      case 2 => Oil
      case 3 => Coal
      case 4 => Biomass
      case 5 => Hydro
      case 6 => Solar
      case 7 => Wind
      case 8 => Nuclear
    }
    (sourceType, getPercentageSource(maximum))
  }

  @scala.annotation.tailrec
  def getPercentageSource(maximum: Double): Double = {
    println(s"Whats the percentage from this source? Insert a number smaller or equal to ${maximum*100} or type maximum")
    readLine() match {
      case in if in.toLowerCase == "maximum" => maximum
      case input => input.toDouble match {
        case p if p <= maximum + 0.0009 => p
        case _ => println("That's an invalid number, please try again")
          getPercentageSource(maximum)
      }
    }
  }

  def printTryAgain(): Unit ={
    println("Please type again one of the available options")
  }

}
