package main.calorieCounter

import main.CalorieCounter
import main.calorieCounter.CalorieCounterOps.{calculateConsumedCalories, calculateBurnedCalories}

case object CaloricImpureFunctions {
  def printCaloricInformation(caloriesConsumed: Int, caloriesBurned: Int) = {
    println(s"You consume a total of ${caloriesConsumed} calories, burned a total of ${caloriesBurned} calories.")
    println(s"Having a net calorie intake of ${caloriesConsumed - caloriesBurned} calories")
  }
}
