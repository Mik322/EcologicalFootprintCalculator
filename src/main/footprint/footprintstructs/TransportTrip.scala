package main.footprint.footprintstructs

import main.footprint.TransportMeans.TransportMean

case class TransportTrip(mean: TransportMean, km: Double, emissions: Double)
