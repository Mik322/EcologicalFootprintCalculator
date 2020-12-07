package main.footprint.transport

import main.Date
import main.States.FootPrintState

case class TransportTrip(mean: TransportMean, km: Double, emissions: Double, date: Date) {
  override def toString: String = TransportTrip.getTransportTripString(this)
}

object TransportTrip {
  def getTransportEmissionsString(footPrintState: FootPrintState): String = {
    val totalEmissions = getTotalEmissions(footPrintState.transportTrips)
    s"Your total of Transportation Emissions is ${totalEmissions}"
  }

  def getTotalEmissions(transportTrips: List[TransportTrip]): Double = transportTrips match {
    case head :: tail => head.emissions + TransportTrip.getTotalEmissions(tail)
    case Nil => 0
  }

  def history(trips: List[TransportTrip]): String = trips.sortBy(_.date).map(trip => trip.toString).mkString("\n")

  def getTransportTripString(trip: TransportTrip): String = {
    s"${trip.km} km travelled by ${trip.mean} with a total emission of ${trip.emissions} g/CO2 on day ${trip.date}"
  }
}
