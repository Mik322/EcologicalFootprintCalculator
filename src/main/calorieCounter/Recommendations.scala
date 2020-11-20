package main.calorieCounter

import main.{CalorieCounter, Date}

object Recommendations {

  def cupsOfWaterToDrink(counter: CalorieCounter, date: Date): Int = CalorieCalculations.getRemainingWaterNeeded(counter, date) / 250

}
