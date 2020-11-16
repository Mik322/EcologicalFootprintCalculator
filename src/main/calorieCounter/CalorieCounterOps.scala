package main.calorieCounter

import caloricstructures.{CaloricActivity, Drink, Food, Sport}
import main.CalorieCounter


object CalorieCounterOps {
  def calculateFoodCalories(caloriesPer100Gram:Int, quantity:Int ):Int = quantity*caloriesPer100Gram/100

  def calculateDrinkCalories(caloriesPer100Ml:Int, quantity:Int ):Int = quantity*caloriesPer100Ml/100

  def calculateCaloriesBurntByDefault(gender:Int, height:Int, weight:Double, age:Int):Int = {
    if (gender==0) (66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age)).asInstanceOf[Int]
    else (655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age)).asInstanceOf[Int]
  }

  def getSumOfCalories(list: List[CaloricActivity]): Int = list.foldLeft(0)((a, b) => a + b.caloricChange)


  def calculateConsumedCalories(calorieCounter: CalorieCounter): Int = {
    val list = calorieCounter.activities.filter((a) => a.activityType == Food || a.activityType == Drink)
    getSumOfCalories(list)
  }

  def calculateBurnedCalories(calorieCounter: CalorieCounter): Int = getSumOfCalories(calorieCounter.activities.filter(a => a.activityType == Sport))
}
