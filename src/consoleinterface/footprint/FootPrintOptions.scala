package consoleinterface.footprint

import consoleinterface.UserChoice.{GetEcologicalFootPrint, GetEnergyEmissions, GetTransportEmissions, GetTransportHistory, GetWasteEmissions, GoToMainMenu}
import consoleinterface._
import consoleinterface.footprint.FootPrintConsoleOps.printTryAgain
import main.footprint.transport.Car

import scala.io.StdIn.readLine

object FootPrintOptions {
  def footPrintOptions(cars: List[Car]): UserChoice = {
    println("1.Add/Change\n2.Visualization/Calculation\n0.Go back")

    val input = readLine()
    input match {
      case "1" => addMenu(cars)
      case "2" => visualizeMenu(cars)
      case "0" => GoToMainMenu
      case _ => {
        printTryAgain()
        footPrintOptions(cars)
      }
    }
  }

  def addMenu(cars: List[Car]): UserChoice = {
    println("1.Transportation\n2.Waste\n3.Energy\n0.Go back")
    val input = readLine()

    input match{
      case "1" => transportationMenu(cars)
      case "2" => wasteMenu(cars)
      case "3" => energyMenu(cars)
      case "0" => footPrintOptions(cars)
      case _ => {
        printTryAgain()
        addMenu(cars)
      }
    }
  }

  def visualizeMenu(cars : List[Car]): UserChoice ={
    println("1.See how many Earths would we need if everybody lived like you\n0.Go back")
    val input = readLine()
    input match {
      case "1" => GetEcologicalFootPrint
      case "0" => footPrintOptions(cars)
      case _ => {
        printTryAgain()
        visualizeMenu(cars)
      }
    }
  }

  def transportationMenu(cars: List[Car]): UserChoice = {
    println("1.Add Car\n2.Add Trip\n3.See total emissions\n4.See Last Trips\n0.Go back")
    val input = readLine()

    input match {
      case "1" => FootPrintConsoleOps.addCar()
      case "2" => FootPrintConsoleOps.addTransportTrip(cars)
      case "3" => GetTransportEmissions
      case "4" => GetTransportHistory
      case "0" => addMenu(cars)
      case _ => {
        printTryAgain()
        transportationMenu(cars)
      }
    }
  }

  def wasteMenu(cars: List[Car]): UserChoice ={
    println("1.Add food waste\n2.Add reciclyng waste\n3.See your total emissions\n0.Go back")
    val input = readLine()

    input match {
      case "1" => FootPrintConsoleOps.addFoodWaste()
      case "2" => FootPrintConsoleOps.addRecycledWaste()
      case "3" => GetWasteEmissions
      case "0" => addMenu(cars)
      case _ => {
        printTryAgain()
        wasteMenu(cars)
      }
    }
  }

  def energyMenu(cars: List[Car]): UserChoice ={
    println("1.Set electricity consumption\n2. Set electricity sources\n3. See your total emissions from electricity use\n0. Go back")
    val input = readLine()

    input match {
      case "1" => GetEnergyEmissions
      case "2" => FootPrintConsoleOps.setEnergySources()
      case "3" => GetEnergyEmissions
      case "0" => addMenu(cars)
      case _ => {
        printTryAgain()
        energyMenu(cars)
      }
    }
  }
}
