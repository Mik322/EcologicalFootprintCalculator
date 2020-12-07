package main.footprint

import main.Date
import main.States.FootPrintState
import main.footprint.transport.TransportMean._
import main.footprint.energy.EnergySource
import main.footprint.energy.TypeOfEnergySource.{Coal, Electricity, Gas, Oil, Wood}
import main.footprint.transport.Fuel.{Diesel, Petrol}
import main.footprint.transport.{Car, Fuel, TransportMean, TransportTrip}
import main.footprint.waste.TypeOfWaste.{Food, Recycled}
import main.footprint.waste.{TypeOfWaste, Waste}

object FootPrintOps {
  def addPublicTransportEmissions(footPrintState: FootPrintState, publicTransport: TransportMean, km: Double,date: Date): FootPrintState = {
    val emissions = calcPublicTransportEmissions(publicTransport,km)
    val totalEmissions = emissions + footPrintState.ecologicalFootPrint
    val trip = TransportTrip(publicTransport,km, emissions,date)
    val trips = footPrintState.transportTrips.appended(trip)
    footPrintState.copy(ecologicalFootPrint = totalEmissions, transportTrips = trips)
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

  def addCarConsumption(footPrintState: FootPrintState, consumption: Double, km: Double, fuel: Fuel, date: Date): FootPrintState ={
    val emissions = calcFuelEmissions(fuel,consumption,km)
    val totalEmissions = emissions + footPrintState.ecologicalFootPrint
    val trip = transport.TransportTrip(Car(consumption,fuel),km, emissions,date)
    val trips = footPrintState.transportTrips.appended(trip)
    footPrintState.copy(ecologicalFootPrint = totalEmissions, transportTrips = trips)
  }

  def calcFuelEmissions(fuel: Fuel, consumption: Double, km: Double): Double = fuel match{
    //An average consumption of 5 liters/100 km then corresponds to 5 l x 2640 g/l / 100 (per km) = 132 g CO2/km per car (not passenger)
    case Diesel => consumption * 2640 / 100 * km
    //An average consumption of 5 liters/100 km then corresponds to 5 l x 2392 g/l / 100 (per km) = 120 g CO2/km.
    case Petrol => consumption * 2392 / 100 * km
  }


  def addWaste(footPrintState: FootPrintState, kg: Int, typeOfWaste: TypeOfWaste): FootPrintState ={
    val emissions = getFoodEmissionsOfType(typeOfWaste, kg)
    val totalEmissions = emissions + footPrintState.ecologicalFootPrint
    val w = getWasteObject(footPrintState,kg,typeOfWaste,emissions)
    footPrintState.copy(ecologicalFootPrint = totalEmissions, waste = Some(w))
  }

  def getFoodEmissionsOfType(typeOfWaste: TypeOfWaste, kg: Double): Double ={
    typeOfWaste match {
      case Food => 1900 * kg
      case Recycled => 100 * kg //A alterar
    }
  }

  def getWasteObject(footPrintState: FootPrintState, kg:Int, typeOfWaste: TypeOfWaste, emissions: Double): Waste ={
    typeOfWaste match {
      case Food => foodWaste(footPrintState, kg, emissions)
      case Recycled => recycledWaste(footPrintState,kg,emissions)
    }
  }

  def foodWaste(footPrintState: FootPrintState, kg: Int, emissions: Double): Waste = {
    footPrintState.waste match {
      case None => Waste(kg, 0, emissions)
      case Some(value) => value.copy(foodWaste = value.foodWaste + kg, totalEmissions = value.totalEmissions + emissions)
    }
  }

  def recycledWaste(footPrintState: FootPrintState, kg: Int, emissions: Double): Waste ={
    footPrintState.waste match {
      case None => Waste(0, kg, emissions)
      case Some(value) => value.copy(recycledWaste = value.recycledWaste + kg, totalEmissions = value.totalEmissions + emissions)
    }
  }



  def getEnergyEmissionsOfType(source: EnergySource): Double ={
    source.TypeOfSource match {
      case Electricity => 256 * source.amount
      case Gas => 184 * source.amount
      case Oil => 314 * source.amount
      case Wood => 7 * source.amount
      case Coal => 414 * source.amount
    }
  }
}
