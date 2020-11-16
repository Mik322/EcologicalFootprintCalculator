package main

import main.calorieCounter.caloricstructures.Body
import main.footprint.TransportMean
import calorieCounter.caloricstructures.CaloricActivity

case class States(footPrintState: FootPrintState, calorieCounter: CalorieCounter)

case class FootPrintState(carbonFootPrint: Int, transportation: List[TransportMean])
case class CalorieCounter(body: Option[Body], activities: List[CaloricActivity])
