package main.footprint.transport

import main.Date
import main.States.FootPrintState
import main.footprint.transport.Fuel.{Diesel, Electric, Hydrogen, Petrol}

case class Car(name: String, consumption: Double, fuel: Fuel) extends TransportMean {
  override def toString: String = {
    s"You have a ${this.fuel} ${this.name} with a consumption of ${this.consumption} l/100km"
  }
}

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
      //An average consumption of 5 liters/100 km then corresponds to 5 l x 2640 g/l / 100 (per km) = 132 g CO2/km per car (not passenger)
      case Diesel => car.consumption * 2640 / 100 * trip.km
      //An average consumption of 5 liters/100 km then corresponds to 5 l x 2392 g/l / 100 (per km) = 120 g CO2/km.
      case Petrol => car.consumption * 2392 / 100 * trip.km
      case Electric => 0
      case Hydrogen => 0
    }
  }

  def getTotalEmissions(trips: List[TransportTrip]): Double = {
    val carTrips = trips.filter(m => m.mean.isInstanceOf[Car])
    carTrips match {
      case ::(head, next) => getCarEmissionInTrip(head) + getTotalEmissions(next)
      case Nil => 0
    }
  }

}
