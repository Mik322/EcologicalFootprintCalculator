package consoleinterface.footprint

import consoleinterface.UserChoice.{GetEcologicalFootPrint, GetEnergyEmissions, GetTransportEmissions, GetTransportHistory, GetWasteEmissions, GoToMainMenu}
import consoleinterface._
import consoleinterface.footprint.FootPrintConsoleOps.printTryAgain

import scala.io.StdIn.readLine

object FootPrintOptions {
  def footPrintOptions(): UserChoice = {
    println("1.Add/Change\n2.Visualization/Calculation\n0.Go back")

    val input = readLine()
    input match {
      case "1" => addMenu()
      case "2" => visualizeMenu()
      case "0" => GoToMainMenu
      case _ => {
        printTryAgain()
        footPrintOptions()
      }
    }
  }

  def addMenu(): UserChoice = {
    println("1.Transportation\n2.Waste\n3.Energy\n0.Go back")
    val input = readLine()

    input match{
      case "1" => transportationMenu()
      case "2" => wasteMenu()
      case "3" => energyMenu()
      case "0" => footPrintOptions()
      case _ => {
        printTryAgain()
        addMenu()
      }
    }
  }

  def visualizeMenu(): UserChoice ={
    println("1.See how many Earths would we need if everybody lived like you\n0.Go back")
    val input = readLine()
    input match {
      case "1" => GetEcologicalFootPrint
      case "0" => footPrintOptions()
      case _ => {
        printTryAgain()
        visualizeMenu()
      }
    }
  }

  def transportationMenu(): UserChoice = {
    println("1.Add Trip\n2.See total emissions\n3.See Last Trips\n0.Go back")
    val input = readLine()

    input match {
      case "1" => FootPrintConsoleOps.addTransportTrip()
      case "2" => GetTransportEmissions
      case "3" => GetTransportHistory
      case "0" => addMenu()
      case _ => {
        printTryAgain()
        transportationMenu()
      }
    }
  }

  def wasteMenu(): UserChoice ={
    println("1.Add food waste\n2.Add reciclyng waste\n3.See your total emissions\n0.Go back")
    val input = readLine()

    input match {
      case "1" => FootPrintConsoleOps.addFoodWaste()
      case "2" => FootPrintConsoleOps.addRecycledWaste()
      case "3" => GetWasteEmissions
      case "0" => addMenu()
      case _ => {
        printTryAgain()
        wasteMenu()
      }
    }
  }

  def energyMenu(): UserChoice ={
    println("1.Set electricity consumption\n1. Set heating sources\n2. See your total emissions from energy use\n0. Go back")
    val input = readLine()

    input match {
      case "1" => FootPrintConsoleOps.setEnergySources()
      case "2" => GetEnergyEmissions
      case "0" => addMenu()
      case _ => {
        printTryAgain()
        energyMenu()
      }
    }
  }

}
