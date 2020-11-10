package main.footprint

import main.FootPrintState
import main.footprint.TransportMeans.{Car, TransportMean}
import main.footprint.footprintstructs.{Diesel, Fuel, Petrol, TransportTrip}
import main.footprint.TransportMeans._

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
    val trip = TransportTrip(Car(consumption,fuel),km, emissions)
    val trips = footPrintState.transportTrips.appended(trip)
    footPrintState.copy(carbonFootPrint = totalEmissions, transportTrips = trips)
  }

  def calcFuelEmissions(fuel: Fuel, consumption: Double, km: Double): Double = fuel match{
    //An average consumption of 5 liters/100 km then corresponds to 5 l x 2640 g/l / 100 (per km) = 132 g CO2/km per car (not passenger)
    case Diesel => consumption * 2640 / 100 * km
    //An average consumption of 5 liters/100 km then corresponds to 5 l x 2392 g/l / 100 (per km) = 120 g CO2/km.
    case Petrol => consumption * 2392 / 100 * km
  }
}
