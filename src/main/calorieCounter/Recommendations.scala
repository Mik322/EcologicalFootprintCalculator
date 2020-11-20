package main.calorieCounter

import main.States.CalorieCounter
import main.Date

object Recommendations {

  def cupsOfWaterToDrink(counter: CalorieCounter, date: Date): Int = CalorieCalculations.getRemainingWaterNeeded(counter, date) / 250

}
