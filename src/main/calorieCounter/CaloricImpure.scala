package main.calorieCounter

import main.calorieCounter.caloricstructures.Body

case object CaloricImpure {
  def printBodyInformation(body: Body): Unit = {
    println(s"Your height: ${body.height}cm")
    println(s"Your weight: ${body.weight}kg")
    println(s"Your age: ${body.age}")
    println(s"Your gender: ${body.gender}")
    println(s"Your lifestyle: ${body.lifestyle}")
  }

  def printCupsOfWaterToDrink(cups: Int) = {
    if (cups > 0) println(s"You still need to drink at least ${cups} cups of water")
    else println("You've drink the recommended water already")
  }

}
