package main.footprint.footprintstructs.transport

import main.Date
import main.footprint.TransportMeans.TransportMean

case class TransportTrip(mean: TransportMean, km: Double, emissions: Double, date: Date)
