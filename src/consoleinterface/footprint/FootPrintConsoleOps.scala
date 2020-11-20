package consoleinterface.footprint

import consoleinterface.DateChoice.getUserDate
import main.footprint.footprintstructs.waste.{Food, Recycled, Waste}
import consoleinterface.footprint.inputs.TransportationInput.fuelInput
import consoleinterface.{AddTransportTrip, AddWaste, SetEnergySource, SetWaterConsumption, UserChoice}
import main.footprint.TransportMeans._
import main.footprint.footprintstructs.FootPrintData
import main.footprint.footprintstructs.energy.{Coal, Electricity, EnergySource, Gas, Oil, TypeOfEnergySource, Wood}

import scala.io.StdIn.readLine

object FootPrintConsoleOps{

  def addTransportTrip(): UserChoice ={
    println("Select Mean of Transport:\n1.Car\n2.Plane\n3.Bus\n4.Subway\n5.Train")
    val input = readLine().toInt
    val mean = getTransportMean(input)
    println("Type number of Km:")
    val km = readLine().toDouble
    println("Date of the trip:")
    val date = getUserDate()
    AddTransportTrip(mean,km,date)
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

  def setElectricity(): UserChoice ={
    println("Type the amount of KhW of electricity you use on average per month")
    val amount = readLine().toDouble
    val energySource = EnergySource(Electricity,amount, 0)
    SetEnergySource(energySource)
  }

  def setEnergySources(): UserChoice ={
    println("Select what type of energy do you use at your household: \n1.Gas\n2.Oil\n3.Wood\n4.Coal")
    val source = readLine().toInt
    println("Type the amount of KhW of the source you use on average per month")
    val amount = readLine().toDouble
    val energySource = EnergySource(getEnergySourceType(source),amount, 0)
    SetEnergySource(energySource)
  }

  def getEnergySourceType(source: Int): TypeOfEnergySource ={
    source match {
      case 1 => Gas
      case 2 => Oil
      case 3 => Wood
      case 4 => Coal
    }
  }

  def setWaterConsumption(): UserChoice ={
    println("How many liters do you spend on average per month")
    val amount = readLine().toDouble
    SetWaterConsumption(amount)
  }

}
