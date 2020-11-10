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
    println("1.Transportation\n2.Waste\n3.Electricity\n4.Water\n5.Gas\n0.Quit")
    val input = readLine()

    input match{
      case "1" => transportationMenu()
    }

  }

  /*def calcMenu(): Unit = {

  }*/

  def transportationMenu(): UserChoice = {
    println("1.Add Trip\n2.See total emissions\n3.See Last Trips\n0.Quit")
    val input = readLine().toInt

    input match {
      case 1 => FootPrintConsoleOps.addTransportTrip()
      case 2 => GetTransportEmissions
      case 3 => GetTransportHistory
    }
  }
}
