package main

import caloriecounter.body.Body
import main.footprint.TransportMean

case class FootPrintState(carbonFootPrint: Int, transportation: List[TransportMean])
case class CalorieCounter(caloriesConsumed: Int, caloriesBurned: Int, body: Option[Body])

