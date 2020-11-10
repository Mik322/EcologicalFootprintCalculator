package consoleinterface.footprint

import consoleinterface.{AddTransportTrip, UserChoice}
import consoleinterface.footprint.inputs.TransportationInput.fuelInput
import main.footprint.TransportMeans._

import scala.io.StdIn.readLine

object FootPrintConsoleOps{

  def addTransportTrip(): UserChoice ={
    println("Select Mean of Transport:\n1.Car\n2.Plane\n3.Boat\n4.Bus\n5.Subway\n6.Train")
    val mean = readLine().toInt
    println("Type number of Km:")
    val km = readLine().toDouble
    AddTransportTrip(getTransportMean(mean),km)
  }

  def getTransportMean(mean: Int): TransportMean = mean match{
    case 1 => {
      println("Type consumption(l/100km):")
      val consumption = readLine().toDouble
      val fuel = fuelInput()
      Car(consumption,fuel)
    }
    case 2 => Plane
    case 3 => Boat
    case 4 => Bus
    case 5 => SubWay
    case 6 => Train
  }

}
