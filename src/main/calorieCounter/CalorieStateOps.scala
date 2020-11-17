package main.calorieCounter

import main.{CalorieCounter, Date}
import consoleinterface.AddCaloricActivity
import main.calorieCounter.caloricstructures.{ActivityType, CaloricActivity, Drink, Food, Sport}

object CalorieStateOps {
  def addCaloricActivity(counter: CalorieCounter,
                         activity: AddCaloricActivity,
                         density: Option[Float],
                         activityAttributes: AddCaloricActivity => (String, Int, ActivityType, Date)): CalorieCounter =
  {
    density match {
      case None => counter
      case Some(value) => {
        val (name, quantity, activityType, date) = activityAttributes(activity)
        val consumedCalories = (value * quantity).asInstanceOf[Int]
        val newActivities = counter.activities.appended(CaloricActivity(activityType, name, quantity, consumedCalories, date))
        counter.copy(activities = newActivities)
      }
    }
  }

}
