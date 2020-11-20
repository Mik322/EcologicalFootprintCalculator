package main.footprint.footprintstructs.transport

import main.States.FootPrintState

object TransportationImpure {
  def printTransportEmissions(footPrintState: FootPrintState): Unit = {
    val totalEmissions = getTotalEmissions(footPrintState.transportTrips)
    println(s"Your total of Transportation Emissions is ${totalEmissions}")
  }

  def getTotalEmissions(transportTrips: List[TransportTrip]): Double = transportTrips match {
    case head :: tail => head.emissions + getTotalEmissions(tail)
    case Nil => 0
  }

  def history(trips: List[TransportTrip]): Unit = {
    val sorted = trips.sortBy(_.date)
    sorted match {
      case ::(head, next) => {
        printTransportHistory(head)
        history(next)
      }
      case Nil => println("")
    }
  }

  def printTransportHistory(trip: TransportTrip) = {
    println(s"${trip.km} km travelled by ${trip.mean} with a total emission of ${trip.emissions} g/CO2 on day ${trip.date}")
  }
}
