package main.calorieCounter

import main.CalorieCounter
import main.calorieCounter.Body

case object CaloricImpure {
  def printCaloricInformation(calorieCounter: CalorieCounter) = {
    println(s"You consume a total of ${calorieCounter.caloriesConsumed} calories, burned a total of ${calorieCounter.caloriesBurned} calories.")
    println(s"Having a net calorie intake of ${calorieCounter.caloriesConsumed - calorieCounter.caloriesBurned} calories")
  }

  def printBodyInformation(calorieCounter: CalorieCounter) = calorieCounter.body match {
    case None => println("FUCK OFF!")
    case Some(value) => {
      println(s"Your height: ${value.height}cm")
      println(s"Your weight: ${value.weight}kg")
      println(s"Your age: ${value.age}")
      println(s"Your gender: ${value.gender}")
      println(s"Your lifestyle: ${value.lifestyle}")
    }
  }

}
