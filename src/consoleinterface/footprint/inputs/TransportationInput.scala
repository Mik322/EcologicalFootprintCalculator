package consoleinterface.footprint.inputs

import main.footprint.transport.Fuel
import main.footprint.transport.Fuel.{Diesel, Electric, Hydrogen, Petrol}

import scala.io.StdIn.readLine

object TransportationInput {
  def fuelInput(): Fuel ={
    println("Select fuel type:\n1.Diesel\n2.Petrol\n3.Electric\n4.Hydrogen")
    val fuel = readLine().toInt
    fuel match {
      case 1 => Diesel
      case 2 => Petrol
      case 3 => Electric
      case 4 => Hydrogen
      case _ => fuelInput()
    }
  }
}
