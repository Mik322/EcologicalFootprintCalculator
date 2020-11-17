package main.calorieCounter

import consoleinterface.CaloricInformation
import consoleinterface.caloriescouter.CaloricInformation._
import main.{CalorieCounter, Date}
import main.calorieCounter.CalorieCounterOps.{calculateBurnedCalories, calculateConsumedCalories}
import main.calorieCounter.caloricstructures.CaloricActivity

import scala.annotation.tailrec

object CaloricInformationOps {
  def getCaloricInformation(info: CaloricInformation, counter: CalorieCounter) = {
    info match {
      case GetTotalCalories => {
        val calories = (calculateConsumedCalories(counter.activities), calculateBurnedCalories(counter.activities))
        Impure.printTotalCaloricInformation(calories._1, calories._2, None)
      }

      case GetListCaloricActivities => {
        Impure.printListOfActivities(counter.activities)
      }

      case GetCaloriesInDay(date) =>  {
        lazy val activities = counter.activities.filter(a => a.date == date)
        val calories = (calculateConsumedCalories(activities), calculateBurnedCalories(activities))
        Impure.printTotalCaloricInformation(calories._1, calories._2, Some(date))
      }
    }
  }


  object Impure {
    def printTotalCaloricInformation(caloriesConsumed: Int, caloriesBurned: Int, day: Option[Date]) = {
      day match {
        case Some(date) => println(s"In day ${date}")
        case None =>
      }
      println(s"You consume a total of ${caloriesConsumed} calories, burned a total of ${caloriesBurned} calories.")
      println(s"Having a net calorie intake of ${caloriesConsumed - caloriesBurned} calories")
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
