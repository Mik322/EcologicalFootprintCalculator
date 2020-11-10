package main

import calorieCounter.Body
import footprint.footprintstructs.TransportTrip

case class FootPrintState(carbonFootPrint: Double, transportTrips: List[TransportTrip])
case class CalorieCounter(caloriesConsumed: Int, caloriesBurned: Int, body: Option[Body], foods: List[String], drinks: List[String], sports: List[String])

