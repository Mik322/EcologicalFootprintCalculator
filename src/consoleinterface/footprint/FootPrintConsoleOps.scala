package consoleinterface.footprint

import consoleinterface.DateChoice.getUserDate
import consoleinterface.UserChoice
import consoleinterface.UserChoice.{AddCar, AddTransportTrip, AddWaste, ChangeElectricityConsumption, DeleteCar, EditCar, GetRequiredSolarPanels, GetTotalEmissionsByCar, GetTotalKmByCar, GoToMainMenu, SetElectricitySources}
import consoleinterface.footprint.inputs.TransportationInput.fuelInput
import main.footprint.energy.TypeOfElectricitySource
import main.footprint.energy.TypeOfElectricitySource._
import main.footprint.transport.TransportMean._
import main.footprint.transport.{Car, TransportMean}
import main.footprint.waste.TypeOfWaste.{Food, Recycled}

import scala.io.StdIn.readLine

object FootPrintConsoleOps {

  def addCar(): UserChoice = {
    println("Type your car name:")
    val name = readLine()
    println("Type your car consumption:")
    val consumption = readLine().toDouble
    val fuel = fuelInput()
    AddCar(name, consumption, fuel)
  }

  def editCar(cars: List[Car]): UserChoice = {
    val car = getCar(cars)
    val index = cars.indexOf(car)
    EditCar(index, readLine())
  }

  @scala.annotation.tailrec
  def getCar(cars: List[Car]): Car = {
    println("Enter your car:")
    println(cars.map(c => s"${c.name}").mkString("\n"))
    println("Cancel")
    val input = readLine()
    val car = cars.find(car => input == car.name)
    car match {
      case None =>
        println("There is no car with that name")
        getCar(cars)
      case Some(value) => value
    }
  }

  def addTransportTrip(cars: List[Car]): UserChoice = {
    println("Select Mean of Transport:\n1.Car\n2.Plane\n3.Bus\n4.Subway\n5.Train")
    val input = readLine().toInt
    val mean = getTransportMean(input, cars)
    println("Type number of Km:")
    val km = readLine().toDouble
    println("Date of the trip:")
    val date = getUserDate()
    AddTransportTrip(mean, km, date)
  }

  def getTransportMean(mean: Int, cars: List[Car]): TransportMean = mean match {
    case 1 => getCar(cars)
    case 2 => Plane
    case 3 => Bus
    case 4 => SubWay
    case 5 => Train
  }

  def addFoodWaste(): UserChoice = {
    println("How much Kg of food waste do you produce a week?")
    val kg = readLine().toInt
    AddWaste(kg, Food)
  }

  def addRecycledWaste(): UserChoice = {
    println("How much Kg of recycled waste do you produce a week?")
    val kg = readLine().toInt
    AddWaste(kg, Recycled)
  }

  def setEnergySources(): UserChoice = {
    @scala.annotation.tailrec
    def loop(sources: List[(TypeOfElectricitySource, Double)], totalPercentage: Double): List[(TypeOfElectricitySource, Double)] = totalPercentage match {
      case t if t < 1.0 =>
        println(s"You need to declare ${(1.0 - t) * 100}% of your electricity sources")
        val (source, percentage) = getEnergySourceType(1.0 - t)
        loop(sources.appended((source, percentage)), totalPercentage + percentage)
      case t if t >= 1.0 && t <= 1.001 => sources
    }

    SetElectricitySources(loop(List(), 0.0))
  }

  def getEnergySourceType(maximum: Double): (TypeOfElectricitySource, Double) = {
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
    println(s"Whats the percentage from this source? Insert a number smaller or equal to ${maximum * 100} or type maximum")
    readLine() match {
      case in if in.toLowerCase == "maximum" => maximum
      case input => input.toDouble / 100 match {
        case p if p <= maximum + 0.0009 => p
        case _ => println("That's an invalid number, please try again")
          getPercentageSource(maximum)
      }
    }
  }

  def changeElectricityConsumption(): ChangeElectricityConsumption = {
    println("How much KwH do you spend on average per month on electricity?")
    val consumption = readLine().toDouble
    ChangeElectricityConsumption(consumption)
  }

  def getRequiredSolarPanels(): UserChoice = {
    println("Please type your desired solar panels power: (Average is between 270 and 370 Watts)")
    val power = readLine().toDouble
    println("Please type your average daily sun light hours")
    val hours = readLine().toInt
    GetRequiredSolarPanels(power, hours)
  }

  def printTryAgain(): Unit = {
    println("Please type again one of the available options")
  }

}
