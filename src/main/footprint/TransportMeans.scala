package main.footprint

import main.footprint.TransportMeans.TransportMean
import main.footprint.footprintstructs.Fuel



object TransportMeans{
    trait TransportMean

    case class Car(consumption: Double, fuel: Fuel) extends TransportMean
    case object Plane extends TransportMean
    case object Bus extends TransportMean
    case object SubWay extends TransportMean
    case object Train extends TransportMean
}



