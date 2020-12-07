package consoleinterface.footprint.inputs

import main.footprint.transport.Fuel
import main.footprint.transport.Fuel.{Diesel, Petrol}

import scala.io.StdIn.readLine

object TransportationInput {
  def fuelInput(): Fuel ={
    println("Select fuel type:\n1.Diesel\n2.Petrol")
    val fuel = readLine().toInt
    fuel match {
      case 1 => Diesel
      case 2 => Petrol
    }
  }
}
