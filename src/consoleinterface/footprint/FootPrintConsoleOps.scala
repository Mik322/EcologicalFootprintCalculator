package consoleinterface.footprint

import main.footprint.footprintstructs.waste.{Waste, Food, Recycled}
import consoleinterface.footprint.inputs.TransportationInput.fuelInput
import consoleinterface.{AddTransportTrip, AddWaste, SetEnergySource, UserChoice}
import main.footprint.TransportMeans._
import main.footprint.footprintstructs.energy.{Electricity, EnergySource, Gas, TypeOfEnergySource}
import main.footprint.footprintstructs._

import scala.io.StdIn.readLine

object FootPrintConsoleOps{

  def addTransportTrip(): UserChoice ={
    println("Select Mean of Transport:\n1.Car\n2.Plane\n3.Bus\n4.Subway\n5.Train")
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
    println("Select what type of energy do you use at your household: \n1.Electricity\n2.Gas")
    val source = readLine().toInt
    println("Type the amount of KhW of the source you use on average per month")
    val amount = readLine().toDouble
    val energySource = EnergySource(getEnergySourceType(source),amount, 0)
    SetEnergySource(energySource)
  }

  def getEnergySourceType(source: Int): TypeOfEnergySource ={
    source match {
      case 1 => Electricity
      case 2 => Gas
    }
  }

}
