package consoleinterface.footprint

import consoleinterface.DateChoice.getUserDate
import consoleinterface.footprint.inputs.TransportationInput.fuelInput
import consoleinterface.UserChoice
import consoleinterface.UserChoice.{AddCar, AddTransportTrip, AddWaste, SetEnergySource}
import main.footprint.energy.TypeOfEnergySource.{Coal, Electricity, Gas, Oil, Wood}
import main.footprint.transport.TransportMean._
import main.footprint.energy.{EnergySource, TypeOfEnergySource}
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
    println("Select what type of energy do you use at your household: \n1.Gas\n2.Oil\n3.Wood\n4.Coal")
    val source = readLine().toInt
    println("Type the amount of KhW of the source you use on average per month")
    val amount = readLine().toDouble
    val energySource = EnergySource(getEnergySourceType(source),amount, 0)
    SetEnergySource(energySource)
  }

  def getEnergySourceType(source: Int): TypeOfEnergySource ={
    source match {
      case 1 => Gas
      case 2 => Oil
      case 3 => Wood
      case 4 => Coal
    }
  }

  def printTryAgain(): Unit ={
    println("Please type again one of the available options")
  }

}
