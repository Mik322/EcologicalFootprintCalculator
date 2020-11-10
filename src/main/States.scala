package main

import main.calorieCounter.caloricstructures.Body
import main.footprint.TransportMean
import calorieCounter.caloricstructures.Historic

case class FootPrintState(carbonFootPrint: Int, transportation: List[TransportMean])
case class CalorieCounter(caloriesConsumed: Int, caloriesBurned: Int, body: Option[Body], historic: Historic)
