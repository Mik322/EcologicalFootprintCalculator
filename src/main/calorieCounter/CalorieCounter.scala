package main.calorieCounter

object CalorieCounter {
  def calculateFoodCalories(caloriesPer100Gram:Int, quantity:Int ):Int = quantity*caloriesPer100Gram/100

  def calculateDrinkCalories(caloriesPer100Ml:Int, quantity:Int ):Int = quantity*caloriesPer100Ml/100

  def calculateCaloriesBurntByDefault(gender:Int, height:Int, weight:Double, age:Int):Int = {
    if (gender==0) (66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age)).asInstanceOf[Int]
    else (655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age)).asInstanceOf[Int]
  }
}
