package main.footprint.transport

import main.Date
import main.States.FootPrintState

case class Car(consumption: Double, fuel: Fuel) extends TransportMean

object Car {
  def getCarKmInDateRange(footPrintState: FootPrintState, startDate: Date, endDate: Date): Double = {
    footPrintState.transportTrips
      .filter(trip => trip.mean.isInstanceOf[Car] && trip.date>=startDate && trip.date<=endDate)
      .foldLeft(0.0)((km, trip) => km + trip.km)
  }

  def getCarConsumptionInTrip(trip: TransportTrip): Double = trip.km * (trip.mean.asInstanceOf[Car].consumption/100)

  def getCarEmissionInTrip(trip: TransportTrip): Double = {
    val car = trip.mean.asInstanceOf[Car]
    car.fuel match {
      case Fuel.Electric => 0
      case Fuel.Hydrogen => 0
        //TODO: Calculate emissions
      case _ => getCarConsumptionInTrip(trip)
    }
  }
}
