package main.footprint

import main.FootPrintState
import main.footprint.TransportMeans.{Car, TransportMean}
import main.footprint.footprintstructs.{transport, _}
import main.footprint.TransportMeans._
import main.footprint.footprintstructs.energy.{Electricity, EnergySource, Gas}
import main.footprint.footprintstructs.transport.{Diesel, Fuel, Petrol, TransportTrip}
import main.footprint.footprintstructs.waste.{TypeOfWaste, Waste, Food, Recycled}

object FootPrintOps {
  def addPublicTransportEmissions(footPrintState: FootPrintState, publicTransport: TransportMean, km: Double): FootPrintState = {
    val emissions = calcPublicTransportEmissions(publicTransport,km)
    val totalEmissions = emissions + footPrintState.carbonFootPrint
    val trip = TransportTrip(publicTransport,km, emissions)
    val trips = footPrintState.transportTrips.appended(trip)
    footPrintState.copy(carbonFootPrint = totalEmissions, transportTrips = trips)
  }

  def calcPublicTransportEmissions(publicTransport: TransportMean, km: Double): Double = publicTransport match{
    //CO2 emissions from aviation fuel are 3.15 grams per gram of fuel [38] , which gives CO2 emissions from a Boeing 737-400 of 115 g per passenger km.
    case Plane => 115 * km
    //Average standard bus: 75 g C02/km , 14 passengers
    //https://www.delijn.be/en/overdelijn/organisatie/zorgzaam-ondernemen/milieu/co2-uitstoot-voertuigen.html
    case Bus => 75 * km
    case SubWay => 30.5 * km
    case Train => 28 * km
  }

  def addCarConsumption(footPrintState: FootPrintState, consumption: Double, km: Double, fuel: Fuel): FootPrintState ={
    val emissions = calcFuelEmissions(fuel,consumption,km)
    val totalEmissions = emissions + footPrintState.carbonFootPrint
    val trip = transport.TransportTrip(Car(consumption,fuel),km, emissions)
    val trips = footPrintState.transportTrips.appended(trip)
    footPrintState.copy(carbonFootPrint = totalEmissions, transportTrips = trips)
  }

  def calcFuelEmissions(fuel: Fuel, consumption: Double, km: Double): Double = fuel match{
    //An average consumption of 5 liters/100 km then corresponds to 5 l x 2640 g/l / 100 (per km) = 132 g CO2/km per car (not passenger)
    case Diesel => consumption * 2640 / 100 * km
    //An average consumption of 5 liters/100 km then corresponds to 5 l x 2392 g/l / 100 (per km) = 120 g CO2/km.
    case Petrol => consumption * 2392 / 100 * km
  }


  def addWaste(footPrintState: FootPrintState, kg: Int, typeOfWaste: TypeOfWaste): FootPrintState ={
    val emissions = getFoodEmissionsOfType(typeOfWaste, kg)
    val totalEmissions = emissions + footPrintState.carbonFootPrint
    val w = getWasteObject(footPrintState,kg,typeOfWaste)
    footPrintState.copy(carbonFootPrint = totalEmissions, waste = Some(w))
  }

  def getFoodEmissionsOfType(typeOfWaste: TypeOfWaste, kg: Double): Double ={
    typeOfWaste match {
      case Food => 1900 * kg
      case Recycled => 100 * kg //A alterar
    }
  }

  def getWasteObject(footPrintState: FootPrintState, kg:Int, typeOfWaste: TypeOfWaste): Waste ={
    typeOfWaste match {
      case Food => foodWaste(footPrintState, kg)
      case Recycled => recycledWaste(footPrintState,kg)
    }
  }

  def foodWaste(footPrintState: FootPrintState, kg: Int): Waste = {
    footPrintState.waste match {
      case None => Waste(kg, 0)
      case Some(value) => value.copy(foodWaste = value.foodWaste + kg)
    }
  }

  def recycledWaste(footPrintState: FootPrintState, kg: Int): Waste ={
    footPrintState.waste match {
      case None => Waste(0, kg)
      case Some(value) => value.copy(recycledWaste = value.recycledWaste + kg)
    }
  }

  def setEnergySource(footPrintState: FootPrintState, source: EnergySource): FootPrintState ={
    val emissions = getEnergyEmissionsOfType(source)
    val totalEmissions = emissions + footPrintState.carbonFootPrint
    val newSource = source.copy(emissions = emissions)
    val energySources = footPrintState.energySources.appended(newSource)
    footPrintState.copy(carbonFootPrint = totalEmissions,energySources = energySources)
  }

  def getEnergyEmissionsOfType(source: EnergySource): Double ={
    source.TypeOfSource match {
      case Electricity => 256 * source.amount
      case Gas => 184 * source.amount
    }
  }
}
