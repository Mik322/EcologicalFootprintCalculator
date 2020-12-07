package main.footprint.transport

import main.Date
import main.States.FootPrintState
import main.footprint.transport.TransportMean.{Bus, Plane, SubWay, Train}

case class TransportTrip(mean: TransportMean, km: Double, date: Date) {
  override def toString: String = TransportTrip.getTransportTripString(this)
}

object TransportTrip {
  def getTransportEmissionsString(footPrintState: FootPrintState): String = {
    val totalEmissions = getTotalEmissions(footPrintState.transportTrips)
    s"Your total of Transportation Emissions is ${totalEmissions}"
  }

  def calcPublicTransportEmissions(trip: TransportTrip): Double = trip.mean match{
    //CO2 emissions from aviation fuel are 3.15 grams per gram of fuel [38] , which gives CO2 emissions from a Boeing 737-400 of 115 g per passenger km.
    case Plane => 115 * trip.km
    //Average standard bus: 75 g C02/km , 14 passengers
    //https://www.delijn.be/en/overdelijn/organisatie/zorgzaam-ondernemen/milieu/co2-uitstoot-voertuigen.html
    case Bus => 75 * trip.km
    case SubWay => 30.5 * trip.km
    case Train => 28 * trip.km
  }

  def getTotalEmissions(transportTrips: List[TransportTrip]): Double = transportTrips match {
    case head :: tail => getEmissionsByType(head) + getTotalEmissions(tail)
    case Nil => 0
  }

  def getEmissionsByType(trip: TransportTrip) = trip.mean match {
    case car: Car => Car.getCarEmissionInTrip(trip)
    case _ => calcPublicTransportEmissions(trip)
  }

  def history(trips: List[TransportTrip]): String = trips.sortBy(_.date).map(trip => trip.toString).mkString("\n")

  def getTransportTripString(trip: TransportTrip): String = {
    s"${trip.km} km travelled by ${trip.mean} with a total emission of ${getEmissionsByType(trip)} g/CO2 on day ${trip.date}"
  }
}
