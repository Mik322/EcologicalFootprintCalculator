package main

import main.footprint.TransportMean
import calorieCounter.Body

case class FootPrintState(carbonFootPrint: Int, transportation: List[TransportMean])
case class CalorieCounter(caloriesConsumed: Int, caloriesBurned: Int, body: Option[Body], foods: List[String], drinks: List[String], exercises: List[String])

