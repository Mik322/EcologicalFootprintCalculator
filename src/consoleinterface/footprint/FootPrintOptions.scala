package consoleinterface.footprint

import consoleinterface.UserChoice.{DeleteCar, GetCars, GetEcologicalFootPrint, GetEnergyEmissions, GetEnergySources, GetMonthFuelConsumption, GetTotalCarEmissions, GetTotalEmissions, GetTotalEmissionsByCar, GetTotalKmByCar, GetTransportEmissions, GetTransportHistory, GetWasteEmissions, GoToMainMenu}
import consoleinterface._
import consoleinterface.footprint.FootPrintConsoleOps.printTryAgain
import main.footprint.transport.Car

import scala.io.StdIn.readLine

object FootPrintOptions {
  def footPrintOptions(cars: List[Car]): UserChoice = {
    println("1. Transportation\n2. Waste\n3. Electricity\n4. See how many Earths would we need if everybody lived like you\n5. See your total emissions\n0.Go back")

    val input = readLine()
    input match {
      case "1" => transportationMenu(cars)
      case "2" => wasteMenu(cars)
      case "3" => energyMenu(cars)
      case "4" => GetEcologicalFootPrint
      case "5" => GetTotalEmissions
      case "0" => GoToMainMenu
      case _ => {
        printTryAgain()
        footPrintOptions(cars)
      }
    }
  }

  def transportationMenu(cars: List[Car]):UserChoice = {
    println("1. Garage\n2. Add Transportation Trip\n3. See last transportation trips\n4. See total emissions from transportation\n0.Go back")
    val input = readLine()

    input match {
      case "1" => garageMenu(cars)
      case "2" => FootPrintConsoleOps.addTransportTrip(cars)
      case "3" => GetTransportHistory
      case "4" => GetTransportEmissions
    }
  }

  def garageMenu(cars: List[Car]): UserChoice = {
    println("0. Go Back\n1. Add Car")
    if (cars.nonEmpty) {
      println("2. Delete Car\n3. Edit Car\n4. See total emissions by car\n5. See total kms by car\n6. See emissions of all cars\n7. See your cars\n8. Get fuel consumption of a month")
    }
    val input = readLine()

    input match {
      case "1" => FootPrintConsoleOps.addCar()
      case "0" => footPrintOptions(cars)
      case in if cars.nonEmpty => in match {
        case "2" => DeleteCar(FootPrintConsoleOps.getCar(cars))
        case "3" => FootPrintConsoleOps.editCar(cars)
        case "4" => GetTotalEmissionsByCar(FootPrintConsoleOps.getCar(cars))
        case "5" => GetTotalKmByCar(FootPrintConsoleOps.getCar(cars))
        case "6" => GetTotalCarEmissions
        case "7" => GetCars
        case "8" => GetMonthFuelConsumption(DateChoice.getMonth)
        case _ =>printTryAgain()
          transportationMenu(cars)
      }
      case _ => printTryAgain()
        transportationMenu(cars)
    }
  }

  def wasteMenu(cars: List[Car]): UserChoice ={
    println("1.Add food waste\n2.Add recycled waste\n3.See your total emissions from waste\n0.Go back")
    val input = readLine()

    input match {
      case "1" => FootPrintConsoleOps.addFoodWaste()
      case "2" => FootPrintConsoleOps.addRecycledWaste()
      case "3" => GetWasteEmissions
      case "0" => footPrintOptions(cars)
      case _ => {
        printTryAgain()
        wasteMenu(cars)
      }
    }
  }

  def energyMenu(cars: List[Car]): UserChoice ={
    println("1. Change monthly electricity consumption\n2. Set electricity sources\n3. See your total emissions from electricity use\n4. See your energy sources\n5. See how many solar panels do you need\n0. Go back")
    val input = readLine()

    input match {
      case "1" => FootPrintConsoleOps.changeElectricityConsumption()
      case "2" => FootPrintConsoleOps.setEnergySources()
      case "3" => GetEnergyEmissions
      case "4" => GetEnergySources
      case "5" => FootPrintConsoleOps.getRequiredSolarPanels()
      case "0" => footPrintOptions(cars)
      case _ => {
        printTryAgain()
        energyMenu(cars)
      }
    }
  }
}
