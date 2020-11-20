package main.calorieCounter

import caloricstructures.{CaloricActivity, Drink, Food, Sport}
import main.calorieCounter.caloricstructures.Goal.Goal
import main.calorieCounter.caloricstructures._


object CalorieCounterOps {

  def calculateExerciseCalories(MET:Double , time:Int, weight:Double): Float=(time * (MET * 3.5 * weight) / 200).asInstanceOf[Float]

  def calculateBiologicalSexBMR(body: Body, multiplier: Double):Int = body.biologicalSex match {
    case Male =>((66.47 + (13.75 * body.weight) + (5.003 * body.height) - (6.755 * body.age))*multiplier).asInstanceOf[Int]
    case Female => ((655.1 + (9.563 * body.weight) + (1.85 * body.height) - (4.676 * body.age))*multiplier).asInstanceOf[Int]
  }

  def calculateBMR(body: Body):Int = {
    val multiplier = body.lifestyle match {
      case Sedentary => 1.2
      case Moderated => 1.375
      case Active => 1.55
      case VeryActive => 1.725
      case ExtremelyActive => 1.9
    }
    calculateBiologicalSexBMR(body, multiplier)
  }

  def getSumOfCalories(list: List[CaloricActivity]): Int = list.foldLeft(0)((a, b) => a + b.caloricChange)


  def calculateConsumedCalories(activities: List[CaloricActivity]): Int = {
    val list = activities.filter(a => a.activityType == Food || a.activityType == Drink)
    getSumOfCalories(list)
  }

  def calculateBurnedCalories(activities: List[CaloricActivity]): Int = getSumOfCalories(activities.filter(a => a.activityType == Sport))

  def calculateCaloriesToGoal(body: Body, goal: Goal): Int = {
    val bmr = calculateBMR(body)
    goal.id + bmr
  }

  def createBody(height: Int, weight: Double, age: Int, biologicalSex: BiologicalSex, lifestyle: Lifestyle): Body = Body(height, weight, age, biologicalSex, lifestyle)
}
