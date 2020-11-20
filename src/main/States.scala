package main

import main.calorieCounter.caloricstructures.Body
import calorieCounter.caloricstructures.CaloricActivity
import main.calorieCounter.caloricstructures.Goal
import main.footprint.footprintstructs.FootPrintData
import main.footprint.footprintstructs.energy.EnergySource
import main.footprint.footprintstructs.transport.TransportTrip
import main.footprint.footprintstructs.waste.Waste
import States._

case class States(profileName: String, footPrintState: FootPrintState, calorieCounter: CalorieCounter)

object States {
  case class FootPrintState(carbonFootPrint: Double, transportTrips: List[TransportTrip], waste: Option[Waste], energySources: List[EnergySource], water: Option[Double], footPrintData: FootPrintData)
  case class CalorieCounter(body: Body, activities: List[CaloricActivity], goal: (Goal,Date), weightHistoric: List[(Double, Date)], sleepTracker: Map[Date, Int])
}
