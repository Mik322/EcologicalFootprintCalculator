package main.calorieCounter

import consoleinterface.{AddDrink, AddFood}
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
}
