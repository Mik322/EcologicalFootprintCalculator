package main.healthTracker

import consoleinterface.caloriescouter.options.CaloricInformation._
import consoleinterface.caloriescouter.options.CaloricInformation
import main.Date
import main.States.HealthTracker
import main.healthTracker.HealthCalculations.{calculateBurnedCalories, calculateCaloriesToGoal, calculateConsumedCalories}

import scala.annotation.tailrec

object CaloricInformationOps {
  def getCaloricInformationString(info: CaloricInformation, counter: HealthTracker): String = {
    info match {
      case GetListCaloricActivities => {
        getListOfActivitiesString(counter.activities, "")
      }

      case GetCaloriesInDay(date) => {
        lazy val activities = counter.activities.filter(a => a.date == date)
        val calories = getCalories(counter, activities)
        caloricInformation(calories._1, calories._2, calories._3, date)
      }

      case GetLastDaysCalories(days) => {
        val startDate = Date.today().minusDays(days)
        lazy val activities = counter.activities.filter(a => a.date >= startDate)
        val calories = getCalories(counter, activities)
        getNDaysCaloriesString(calories._1, calories._2, calories._3, days)
      }

      case GetListCaloricActivitiesInDays(startDate, endDate) => {
        lazy val activities = counter.activities.filter(a => a.date >= startDate && a.date <= endDate)
        getListOfActivitiesString(activities, "")
      }

      case GetGoalInformation => getGoalInformation(counter.goal._1, calculateCaloriesToGoal(counter.body, counter.goal._1))
      case GetWeightHistory => getWeightHistoric(counter.weightHistory.sortBy(_._2), "")
      case GetWeightTrack => getWeightTrack(counter.weightHistory.sortBy(_._2), counter.goal)

      case GetWaterNeeds(date) =>
        val waterNeeds = CaloricActivity.cupsOfWaterToDrink(counter, date)
        getCupsOfWaterToDrinkString(waterNeeds)

      case GetNecessarySleep =>
        val necessarySleep = sleepTracker.SleepTracker.getNecessarySleep(counter.body.age)
        getNecessarySleepString(necessarySleep)

      case GetSleepInDay(date) =>
        val sleepInDay = sleepTracker.SleepTracker.getSleepInDay(counter.sleepTracker,date)
        getSleepInDayString(sleepInDay,date)

      case GetAverageSleepInDays(date1,date2) =>
        val averageSleepInDays = sleepTracker.SleepTracker.getAverageSleep(counter.sleepTracker,date1,date2)
        getAverageSleepInDaysString(averageSleepInDays,date1,date2)
      }


  }

  def getCalories(counter: HealthTracker, activities: List[CaloricActivity]): (Int, Int, Int) = {
    val goalCalories = calculateCaloriesToGoal(counter.body, counter.goal._1)
    (calculateConsumedCalories(activities), calculateBurnedCalories(activities), goalCalories)
  }


  private def caloricInformation(caloriesConsumed: Int, caloriesBurned: Int, goalCalories: Int, date: Date): String = {
    val firstLine = s"In day ${date} you consumed a total of ${caloriesConsumed} calories and burned a total of ${caloriesBurned} calories.\n"
    val secondLine = s"To reach your goal you need a daily net intake of ${goalCalories} calories and you have a net of ${caloriesConsumed - caloriesBurned}"
    firstLine.concat(secondLine)

  }

  private def getNDaysCaloriesString(caloriesConsumed: Int, caloriesBurned: Int, goalCalories: Int, days: Int): String = {
    val s1 = s"In the last ${days} day(s) you consumed on average ${caloriesConsumed / days} calories p/day and burned ${caloriesBurned / days} calories p/day.\n"
    val s2 = s"To reach your goal you need a daily net intake of ${goalCalories} calories, your average net calories is ${(caloriesConsumed - caloriesBurned) / days} calories p/day."
    s1.concat(s2)
  }

  private def getGoalInformation(goal: Goal.Value, calories: Int): String = s"To ${goalPhrase(goal)} you need to have a net caloric intake of ${calories} per week"

  private def goalPhrase(goal: Goal.Value): String = goal match {
    case Goal.KeepWeight => "keep weight"
    case _ => s"${if (goal.kgChanged > 0) "gain" else "lose"} ${goal.kgChanged.abs} per week"
  }

  private def getCupsOfWaterToDrinkString(cups: Int): String = {
    if (cups > 0) s"You still need to drink at least ${cups} cups of water"
    else "You've drink the recommended water already"
  }

  @tailrec
  private def getListOfActivitiesString(activities: List[CaloricActivity], str: String): String = {
    activities match {
      case ::(head, next) => {
        val activity = s"${head.name}, ${head.quantity}, date: ${head.date}\n"
        getListOfActivitiesString(next, str.concat(activity))
      }
      case Nil => str
    }
  }

  @tailrec
  private def getWeightHistoric(weightHistory: List[(Double, Date)], str: String): String = {
    weightHistory match {
      case ::(head, next) => {
        val weight = s"You had ${head._1}kg in ${head._2}\n"
        getWeightHistoric(next, str.concat(weight))
      }
      case Nil => str
    }
  }

  private def getWeightTrack(weightHistory: List[(Double, Date)], goal: (Goal.Value, Date)): String = {
    val (_, goalDate) = goal
    val temp = weightHistory.filter { case (_, d) => d >= goalDate }
    val avg = (temp.last._1 - temp.head._1) / (temp.last._2 - temp.head._2) * 7

    val str = if (avg < 0.0) {
      s"You lost an average of ${avg.abs}kg per week since ${temp.head._2} until ${temp.last._2}"
    }
    else if (avg == 0.0) {
      s"Your weight now is equal to the weight when you started your goal"
    } else {
      s"You gained an average of ${avg.abs}kg per week since ${temp.head._2} until ${temp.last._2}"
    }
    val goal_str = s"\nYour goal is to ${goalPhrase(goal._1)}"
    str.concat(goal_str)
  }

  private def getNecessarySleepString(tuple: (Int, Int)): String = {
    s"You need to sleep ${tuple._1} to ${tuple._2} hours per day"
  }

  private def getSleepInDayString(i: Int,date: Date): String = i match {
    case 0 => s"You don't have any sleep record on ${date}"
    case _ => s"You slept ${i} hours on ${date}"
  }

  private def getAverageSleepInDaysString(d: Double,date1: Date,date2: Date ): String = d match {
    case 0.0 => s"You don't have any sleep record from ${date1} to ${date2}"
    case _ => s"You slept an average of ${d} hours from ${date1} to ${date2}(counting only days that you have a sleep record)"
  }


}
