package main.calorieCounter

import main.CalorieCounter

case object CaloricImpure {
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
