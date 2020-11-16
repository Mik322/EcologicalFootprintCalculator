package main.calorieCounter

import main.calorieCounter.caloricstructures.CaloricActivity
import main.calorieCounter.CalorieCounterOps.{calculateConsumedCalories, calculateBurnedCalories}

case object CaloricImpureFunctions {
  def printCaloricInformation(caloriesConsumed: Int, caloriesBurned: Int) = {
    println(s"You consume a total of ${caloriesConsumed} calories, burned a total of ${caloriesBurned} calories.")
    println(s"Having a net calorie intake of ${caloriesConsumed - caloriesBurned} calories")
  }

  def printListOfActivities(activities: List[CaloricActivity]): Unit = {
    activities match {
      case ::(head, next) => {
        println(s"${head.name}, ${head.quantity}, date: ${head.date}")
        printListOfActivities(next)
      }
      case Nil =>
    }
  }
}
