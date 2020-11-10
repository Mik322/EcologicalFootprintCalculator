package main.calorieCounter

import consoleinterface.{AddDrink, AddExercise, AddFood}
import main.CalorieCounter

object CalorieStateOps {
  def addFoodCalories(calorieCounter: CalorieCounter, addFood: AddFood, foodMap: Map[String, Int]): CalorieCounter = {
    val caloricDensity = foodMap get addFood.food
    caloricDensity match {

      case None => calorieCounter

      case Some(value) => {
        val consumedCalories =  CalorieCounterOps.calculateFoodCalories(value, addFood.quantity)
        val newTotalConsumedCalories = consumedCalories + calorieCounter.caloriesConsumed
        calorieCounter.copy(caloriesConsumed = newTotalConsumedCalories, foods = calorieCounter.foods.appended(addFood.food))
      }
    }
  }

  def addDrinkCalories(calorieCounter: CalorieCounter, drink: AddDrink, drinkMap: Map[String, Int]): CalorieCounter = {
    val caloricDensity = drinkMap get drink.drink
    caloricDensity match {

      case None => calorieCounter

      case Some(value) => {
        val consumedCalories =  CalorieCounterOps.calculateDrinkCalories(value, drink.quantity)
        val newTotalConsumedCalories = consumedCalories + calorieCounter.caloriesConsumed
        calorieCounter.copy(caloriesConsumed = newTotalConsumedCalories, foods = calorieCounter.foods.appended(drink.drink))
      }
    }
  }
  def addExerciseCalories(calorieCounter: CalorieCounter, exercise: AddExercise, exerciseMap: Map[String, Double], weight:Double): CalorieCounter = {
    val caloricDensity = exerciseMap get exercise.exercise
    caloricDensity match {

      case None => calorieCounter

      case Some(value) => {
        val burnedCalories =  CalorieCounterOps.calculateExerciseCalories(value, exercise.time, weight)
        val newTotalBurnedCalories = burnedCalories + calorieCounter.caloriesBurned
        calorieCounter.copy(caloriesBurned = newTotalBurnedCalories, exercises = calorieCounter.exercises.appended(exercise.exercise))
      }
    }
  }

}
