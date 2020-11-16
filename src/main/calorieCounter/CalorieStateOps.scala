package main.calorieCounter

import main.CalorieCounter
import consoleinterface.AddCaloricActivity
import main.calorieCounter.caloricstructures.{ActivityType, Drink, Food, Sport, CaloricActivity}

object CalorieStateOps {
  def addCaloricActivity(counter: CalorieCounter,
                         activity: AddCaloricActivity,
                         density: Option[Float],
                         activityAttributes: (AddCaloricActivity) => (String, Int, ActivityType)): CalorieCounter =
  {
    density match {
      case None => counter
      case Some(value) => {
        val (name, quantity, activityType) = activityAttributes(activity)
        val consumedCalories = (value * quantity).asInstanceOf[Int]
        val newActivities = counter.activities.appended(CaloricActivity(activityType, name, quantity, consumedCalories))
        counter.copy(activities = newActivities)
      }
    }
  }
}
