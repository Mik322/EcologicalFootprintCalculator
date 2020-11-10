package main.calorieCounter

import main.CalorieCounter

case object CaloricImpure {
  def printCaloricInformation(calorieCounter: CalorieCounter) = {
    println(s"You consume a total of ${calorieCounter.caloriesConsumed} calories, burned a total of ${calorieCounter.caloriesBurned} calories.")
    println(s"Having a net calorie intake of ${calorieCounter.caloriesConsumed - calorieCounter.caloriesBurned} calories")
  }
}
