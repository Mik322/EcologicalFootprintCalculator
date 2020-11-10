package main.calorieCounter

import consoleinterface.caloriescouter.CaloricActivitiesChoice.{AddDrink, AddFood}
import main.CalorieCounter
import main.calorieCounter.caloricstructures.CaloricAction

object CalorieStateOps {
  def addFoodCalories(calorieCounter: CalorieCounter, addFood: AddFood, foodMap: Map[String, Int]): CalorieCounter = {
    val caloricDensity = foodMap get addFood.food
    caloricDensity match {

      case None => calorieCounter

      case Some(value) => {
        val consumedCalories =  CalorieCounterOps.calculateFoodCalories(value, addFood.quantity)
        val newTotalConsumedCalories = consumedCalories + calorieCounter.caloriesConsumed
        val newHistoric = calorieCounter.historic.copy(food = calorieCounter.historic.food.appended(CaloricAction(addFood.food, addFood.quantity, consumedCalories)))
        calorieCounter.copy(caloriesConsumed = newTotalConsumedCalories, historic = newHistoric)
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
        val newHistoric = calorieCounter.historic.copy(drink = calorieCounter.historic.drink.appended(CaloricAction(drink.drink, drink.quantity, consumedCalories)))
        calorieCounter.copy(caloriesConsumed = newTotalConsumedCalories, historic = newHistoric)
      }
    }
  }
}
