package main.footprint.footprintstructs

import main.FootPrintState

object TransportEmissionImpure {
  def printTransportEmissions(footPrintState: FootPrintState): Unit = {
    val totalEmissions = getTotalEmissions(footPrintState.transportTrips)
    println(s"Your total of Transportation Emissions is ${totalEmissions}")
  }

  def getTotalEmissions(transportTrips: List[TransportTrip]): Double = transportTrips match {
      case head::tail => head.emissions + getTotalEmissions(tail)
      case Nil => 0
    }
}
