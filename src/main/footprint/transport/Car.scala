package main.footprint.transport

import main.Date
import main.States.FootPrintState
import main.footprint.transport.Fuel.{Diesel, Electric, Hydrogen, Petrol}

case class Car(name: String, consumption: Double, fuel: Fuel) extends TransportMean {
  override def toString: String = {
    s"${this.fuel} ${this.name}"
  }
}

object Car extends TransportMean {
  override def toString: String = "Car"

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
    trips.filter(t => t.mean.isInstanceOf[Car])
      .foldRight(0.0)((t, c) => c + getCarEmissionInTrip(t))
  }

  def getKmByCar(trips: List[TransportTrip], carName: String): Double = {
    filterCar(trips, carName).foldRight(0.0)((trip, totalKm) => trip.km + totalKm)
  }

  def getEmissionByCar(trips: List[TransportTrip], carName: String): Double = {
    filterCar(trips, carName).foldRight(0.0)((trip, emissions) => emissions + getCarEmissionInTrip(trip))
  }

  private def filterCar(trips: List[TransportTrip], carName: String): List[TransportTrip] = {
    trips.filter(t => t.mean.isInstanceOf[Car] && t.mean.asInstanceOf[Car].name == carName)
  }

  def getMonthFuelConsumption(footPrintState: FootPrintState, month: Date): Double = {
    filterCarMonth(footPrintState.transportTrips, month)
      .filter(t => t.mean.asInstanceOf[Car].fuel != Electric)
      .foldLeft(0.0)((tracker, transport) => tracker + Car.getCarConsumptionInTrip(transport))
  }

  def getMonthlyCarEmission(footPrintState: FootPrintState, month: Date): Double = {
    filterCarMonth(footPrintState.transportTrips, month)
      .foldLeft(0.0)((tracker, transport) => tracker + Car.getCarEmissionInTrip(transport))
  }

  private def filterCarMonth(transportTrips: List[TransportTrip], month: Date): List[TransportTrip] = {
    transportTrips.filter(t => t.mean.isInstanceOf[Car] && t.date.getMonth() == month.getMonth() && t.date.getYear() == month.getYear())
  }

}
