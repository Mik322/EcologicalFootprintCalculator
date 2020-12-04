package main

import main.healthTracker.caloricstructures.Body
import healthTracker.caloricstructures.CaloricActivity
import main.healthTracker.caloricstructures.Goal
import main.footprint.footprintstructs.FootPrintData
import main.footprint.footprintstructs.energy.EnergySource
import main.footprint.footprintstructs.transport.TransportTrip
import main.footprint.footprintstructs.waste.Waste
import States._

case class States(profileName: String, footPrintState: FootPrintState, healthTracker: HealthTracker)

object States {
  case class FootPrintState(carbonFootPrint: Double, transportTrips: List[TransportTrip], waste: Option[Waste], energySources: List[EnergySource], water: Option[Double], footPrintData: FootPrintData)
  case class HealthTracker(body: Body, activities: List[CaloricActivity], goal: (Goal.Value,Date), weightHistory: List[(Double, Date)], sleepTracker: Map[Date, Int])
}
