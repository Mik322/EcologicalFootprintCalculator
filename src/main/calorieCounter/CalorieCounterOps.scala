package main.calorieCounter

import caloricstructures.{CaloricActivity, Drink, Food, Sport}
import main.calorieCounter.caloricstructures._


object CalorieCounterOps {
  def calculateFoodCalories(caloriesPer100Gram:Int, quantity:Int ):Int = quantity*caloriesPer100Gram/100

  def calculateDrinkCalories(caloriesPer100Ml:Int, quantity:Int ):Int = quantity*caloriesPer100Ml/100

  def calculateExerciseCalories(MET:Double , time:Int, weight:Double):Int=(time * (MET * 3.5 * weight) / 200).asInstanceOf[Int]

  def calculateGenderBMR(body: Body, multiplier: Double):Int = body.gender match {
    case Male =>((66.47 + (13.75 * body.weight) + (5.003 * body.height) - (6.755 * body.age))*multiplier).asInstanceOf[Int]
    case Female => ((655.1 + (9.563 * body.weight) + (1.85 * body.height) - (4.676 * body.age))*multiplier).asInstanceOf[Int]
  }

  def calculateBMR(body: Body):Int = body.lifestyle match {
    case Sedentary => calculateGenderBMR(body,1.2)
    case Moderated => calculateGenderBMR(body,1.375)
    case Active => calculateGenderBMR(body,1.55)
    case VeryActive => calculateGenderBMR(body,1.725)
    case ExtremelyActive => calculateGenderBMR(body,1.9)
  }

  def getSumOfCalories(list: List[CaloricActivity]): Int = list.foldLeft(0)((a, b) => a + b.caloricChange)


  def calculateConsumedCalories(activities: List[CaloricActivity]): Int = {
    val list = activities.filter((a) => a.activityType == Food || a.activityType == Drink)
    getSumOfCalories(list)
  }

  def calculateBurnedCalories(activities: List[CaloricActivity]): Int = getSumOfCalories(activities.filter(a => a.activityType == Sport))
}
