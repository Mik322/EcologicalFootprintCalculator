package main

import calorieCounter.Body
import main.footprint.footprintstructs.energy.EnergySource
import main.footprint.footprintstructs.transport.TransportTrip
import main.footprint.footprintstructs.waste.Waste

case class FootPrintState(carbonFootPrint: Double, transportTrips: List[TransportTrip], waste: Option[Waste], energySources: List[EnergySource])
case class CalorieCounter(caloriesConsumed: Int, caloriesBurned: Int, body: Option[Body], foods: List[String], drinks: List[String], sports: List[String])

