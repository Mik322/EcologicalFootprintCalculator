package main.healthTracker

import main.healthTracker.Body.{Active, BiologicalSex, ExtremelyActive, Female, Lifestyle, Male, Moderated, Sedentary, VeryActive}
import main.Date
import main.States.HealthTracker
import main.healthTracker.CaloricActivity.{Drink, Food, Sport, Water}


object HealthCalculations {

  def calculateExerciseCalories(MET: Double, time: Int, weight: Double): Float = (time * (MET * 3.5 * weight) / 200).asInstanceOf[Float]

  def getSumOfCalories(list: List[CaloricActivity]): Int = list.foldLeft(0)((a, b) => a + b.caloricChange)

  def calculateConsumedCalories(activities: List[CaloricActivity]): Int = {
    val list = activities.filter(a => a.activityType == Food || a.activityType == Drink)
    getSumOfCalories(list)
  }

  def calculateBurnedCalories(activities: List[CaloricActivity]): Int = getSumOfCalories(activities.filter(a => a.activityType == Sport))

  def calculateCaloriesToGoal(body: Body, goal: Goal.Value): Int = {
    val bmr = Body.calculateBMR(body)
    goal.caloricChange + bmr
  }

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

  def getRemainingWaterNeeded(counter: HealthTracker, date: Date): Int = {
    val recommended = calculateDayRecommendedWater(counter.body, date, counter.activities)
    val consumed = getTotalWaterConsumedInDay(counter.activities, date)
    (recommended - consumed).toInt
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
