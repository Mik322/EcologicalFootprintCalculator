package main

import main.calorieCounter.caloricstructures.Body
import calorieCounter.caloricstructures.CaloricActivity
import main.calorieCounter.caloricstructures.GoalType.Goal
import main.footprint.footprintstructs.energy.EnergySource
import main.footprint.footprintstructs.transport.TransportTrip
import main.footprint.footprintstructs.waste.Waste

case class States(footPrintState: FootPrintState, calorieCounter: CalorieCounter)

case class FootPrintState(carbonFootPrint: Double, transportTrips: List[TransportTrip], waste: Option[Waste], energySources: List[EnergySource])
case class CalorieCounter(body: Option[Body], activities: List[CaloricActivity], goal: Goal)
