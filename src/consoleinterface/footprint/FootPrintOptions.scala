package consoleinterface.footprint

import consoleinterface._

import scala.io.StdIn.readLine

object FootPrintOptions {
  def footPrintOptions(): UserChoice = {
    println("1.Add/Change\n2.Visualization/Calculation\n0.Quit")

    val input = readLine()
    input match {
      case "1" => addMenu()
      //case "2" => calcMenu()
    }
  }

  def addMenu(): UserChoice = {
    println("1.Transportation\n2.Waste\n3.Energy\n4.Water\n5.Gas\n0.Quit")
    val input = readLine()

    input match{
      case "1" => transportationMenu()
      case "2" => wasteMenu()
      case "3" => energyMenu()
    }

  }

  def transportationMenu(): UserChoice = {
    println("1.Add Trip\n2.See total emissions\n3.See Last Trips\n0.Quit")
    val input = readLine().toInt

    input match {
      case 1 => FootPrintConsoleOps.addTransportTrip()
      case 2 => GetTransportEmissions
      case 3 => GetTransportHistory
    }
  }

  def wasteMenu(): UserChoice ={
    println("1.Add food waste\n2.Add reciclyng waste\n3.See your total emissions\n0.Quit")
    val input = readLine().toInt

    input match {
      case 1 => FootPrintConsoleOps.addFoodWaste()
      case 2 => FootPrintConsoleOps.addRecycledWaste()
      case 3 => GetWasteEmissions
    }
  }

  def energyMenu(): UserChoice ={
    println("1.Set energy sources\n2.See your total emissions from energy use\n0.Quit")
    val input = readLine().toInt

    input match {
      case 1 => FootPrintConsoleOps.setEnergySources()
      case 2 => GetEnergyEmissions
    }
  }

}
