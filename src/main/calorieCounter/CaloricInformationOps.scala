package main.calorieCounter

import consoleinterface.CaloricInformation
import consoleinterface.caloriescouter.CaloricInformation
import consoleinterface.caloriescouter.CaloricInformation._
import main.{CalorieCounter, Date}
import main.calorieCounter.CalorieCounterOps.{calculateBurnedCalories, calculateCaloriesToGoal, calculateConsumedCalories}
import main.calorieCounter.caloricstructures.{CaloricActivity, Goal}
import main.calorieCounter.caloricstructures.Goal.Goal

import scala.annotation.tailrec

object CaloricInformationOps {
  def getCaloricInformation(info: CaloricInformation, counter: CalorieCounter): Unit = {
    info match {
      case GetListCaloricActivities => {
        Impure.printListOfActivities(counter.activities)
      }

      case GetCaloriesInDay(date) => {
        lazy val activities = counter.activities.filter(a => a.date == date)
        val calories = getCalories(counter, activities)
        Impure.printCaloricInformation(calories._1, calories._2, calories._3, date)
      }

      case GetLastDaysCalories(days) => {
        val startDate = Date.today().minusDays(days)
        lazy val activities = counter.activities.filter(a => a.date >= startDate)
        val calories = getCalories(counter, activities)
        Impure.printNDaysCalories(calories._1, calories._2, calories._3, days)
      }

      case GetListCaloricActivitiesInDays(startDate, endDate) => {
        lazy val activities = counter.activities.filter(a => a.date >= startDate && a.date <= endDate)
        Impure.printListOfActivities(activities)
      }

      case CaloricInformation.GetGoalInformation => Impure.printGoalInformation(counter.goal, calculateCaloriesToGoal(counter.body, counter.goal))

      case GetWaterNeeds(date) =>
        val waterNeeds = CalorieCounterOps.calcWaterIntake(counter.body, date, counter.activities)
        println(s"You need to drink ${waterNeeds} mL today")
    }
  }

  def getCalories(counter: CalorieCounter, activities: List[CaloricActivity]): (Int, Int, Int) = {
    val goalCalories =  calculateCaloriesToGoal(counter.body, counter.goal)
    (calculateConsumedCalories(activities), calculateBurnedCalories(activities), goalCalories)
  }

  object Impure {
    def printCaloricInformation(caloriesConsumed: Int, caloriesBurned: Int, goalCalories: Int, date: Date) = {
      println(s"In day ${date} you consume a total of ${caloriesConsumed} calories and burned a total of ${caloriesBurned} calories.")
      println(s"To reach your goal you need a daily net intake of ${goalCalories} calories and you have a net of ${caloriesConsumed - caloriesBurned}")
    }

    def printNDaysCalories(caloriesConsumed: Int, caloriesBurned: Int, goalCalories: Int, days: Int) = {
      println(s"In the last ${days} day(s) you consumed on average ${caloriesConsumed / days} calories p/day and burned ${caloriesBurned / days} calories p/day.")
      println(s"To reach your goal you need a daily net intake of ${goalCalories} calories, your average net calories is ${(caloriesConsumed - caloriesBurned) / days} calories p/day.")
    }

    def printGoalInformation(goal: Goal, calories: Int): Unit = {
      val goalFrase = goal match {
        case Goal.LoseALotOfWeight => "lose 1kg per week"
        case Goal.LoseWeight => "lose 0.5kg per week"
        case Goal.KeepWeight => "keep weight"
        case Goal.GainWeight => "gain 0.5kg per week"
        case Goal.GainALotOfWeight => "gain 1kg per week"
      }

      println(s"To ${goalFrase} you need to have a net caloric intake of ${calories} per week")
    }

    @tailrec
    def printListOfActivities(activities: List[CaloricActivity]): Unit = {
      activities match {
        case ::(head, next) => {
          println(s"${head.name}, ${head.quantity}, date: ${head.date}")
          printListOfActivities(next)
        }
        case Nil =>
      }
    }
  }

}
