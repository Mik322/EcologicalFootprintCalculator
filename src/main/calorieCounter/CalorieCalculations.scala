package main.calorieCounter

import caloricstructures.{CaloricActivity, Drink, Food, Sport}
import main.calorieCounter.caloricstructures.Body.{Active, ExtremelyActive, Female, Gender, Lifestyle, Male, Moderated, Sedentary, VeryActive}
import main.Date
import main.States.CalorieCounter
import main.calorieCounter.caloricstructures.Goal
import main.calorieCounter.caloricstructures._


object CalorieCalculations {

  def calculateExerciseCalories(MET: Double, time: Int, weight: Double): Float = (time * (MET * 3.5 * weight) / 200).asInstanceOf[Float]

  def calculateGenderBMR(body: Body, multiplier: Double): Int = body.gender match {
    case Male => ((66.47 + (13.75 * body.weight) + (5.003 * body.height) - (6.755 * body.age)) * multiplier).asInstanceOf[Int]
    case Female => ((655.1 + (9.563 * body.weight) + (1.85 * body.height) - (4.676 * body.age)) * multiplier).asInstanceOf[Int]
  }

  def calculateBMR(body: Body): Int = {
    val multiplier = body.lifestyle match {
      case Sedentary => 1.2
      case Moderated => 1.375
      case Active => 1.55
      case VeryActive => 1.725
      case ExtremelyActive => 1.9
    }
    calculateGenderBMR(body, multiplier)
  }

  def getSumOfCalories(list: List[CaloricActivity]): Int = list.foldLeft(0)((a, b) => a + b.caloricChange)


  def calculateConsumedCalories(activities: List[CaloricActivity]): Int = {
    val list = activities.filter(a => a.activityType == Food || a.activityType == Drink)
    getSumOfCalories(list)
  }

  def calculateBurnedCalories(activities: List[CaloricActivity]): Int = getSumOfCalories(activities.filter(a => a.activityType == Sport))

  def calculateCaloriesToGoal(body: Body, goal: Goal.Value): Int = {
    val bmr = calculateBMR(body)
    goal.caloricChange + bmr
  }

  def createBody(height: Int, weight: Double, age: Int, gender: Gender, lifestyle: Lifestyle): Body = Body(height, weight, age, gender, lifestyle)

  def calculateDayRecommendedWater(body: Body, date: Date, activities: List[CaloricActivity]): Double = {
    import WaterAuxiliaries._
    val baseNeed = kgToPound(body.weight) * 2 / 3
    val lifestyleWaterNeeds = waterOuncesPerExerciseTime(lifeStyleSportTime(body.lifestyle))
    val dayExerciseWater = waterOuncesPerExerciseTime(getTotalTimeOfSportInDay(activities, date))
    ouncesToMLiters(baseNeed + lifestyleWaterNeeds + dayExerciseWater)
  }

  def getTotalWaterConsumedInDay(activities: List[CaloricActivity], date: Date): Int = {
    activities.filter(a => (a.activityType == Drink || a.activityType == Water) && a.date == date).foldLeft(0)((c, a) => c + a.quantity)
  }

  def getTotalTimeOfSportInDay(activities: List[CaloricActivity], date: Date): Int = {
    activities.filter(a => a.activityType == Sport && a.date == date).foldLeft(0)((c, a) => c + a.quantity)
  }

  def getRemainingWaterNeeded(counter: CalorieCounter, date: Date): Int = {
    val recommended = getTotalWaterConsumedInDay(counter.activities, date)
    val consumed = getTotalWaterConsumedInDay(counter.activities, date)
    recommended - consumed
  }

  object WaterAuxiliaries {
    def waterOuncesPerExerciseTime(time: Int): Double = 12 * time / 30

    def kgToPound(kg: Double): Double = kg * 2.2

    def ouncesToMLiters(ounces: Double): Double = ounces / 33.8 * 1000

    def lifeStyleSportTime(lifestyle: Lifestyle): Int = lifestyle match {
      case Sedentary => 0
      case Moderated => 10
      case Active => 30
      case VeryActive => 60
      case ExtremelyActive => 120
    }
  }

}
