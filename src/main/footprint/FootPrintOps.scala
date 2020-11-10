package main.footprint

import main.FootPrintState
import main.footprint.TransportMeans.Car
import main.footprint.footprintstructs.{Diesel, Fuel, Petrol, TransportTrip}

object FootPrintOps {
  def addPublicTransportEmissions(): Unit ={

  }

  def addCarConsumption(footPrintState: FootPrintState, consumption: Double, km: Double, fuel: Fuel): FootPrintState ={
    val emissions = calcFuelEmissions(fuel,consumption,km)
    val totalEmissions = emissions + footPrintState.carbonFootPrint
    val trip = TransportTrip(Car(consumption,fuel),km, emissions)
    val trips = footPrintState.transportTrips.appended(trip)
    footPrintState.copy(carbonFootPrint = totalEmissions, transportTrips = trips)
  }

  def calcFuelEmissions(fuel: Fuel, consumption: Double, km: Double): Double = fuel match{
    //An average consumption of 5 liters/100 km then corresponds to 5 l x 2640 g/l / 100 (per km) = 132 g CO2/km
    case Diesel => consumption * 2640 / 100 * km
    //An average consumption of 5 liters/100 km then corresponds to 5 l x 2392 g/l / 100 (per km) = 120 g CO2/km.
    case Petrol => consumption * 2392 / 100 * km
  }
}
