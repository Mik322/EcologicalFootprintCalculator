package main.calorieCounter

import main.calorieCounter.caloricstructures.Body

case object CaloricImpure {
  def printBodyInformation(body: Body): Unit = {
    println(s"Your height: ${body.height}cm")
    println(s"Your weight: ${body.weight}kg")
    println(s"Your age: ${body.age}")
    println(s"Your biological sex: ${body.biologicalSex}")
    println(s"Your lifestyle: ${body.lifestyle}")
  }

}
