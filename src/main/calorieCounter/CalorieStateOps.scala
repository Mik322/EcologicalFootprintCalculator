package main.calorieCounter

import main.{CalorieCounter, Date, FootPrintState, States}
import consoleinterface.StartOptions.SetBodyParams
import CalorieCalculations.createBody
import consoleinterface.caloriescouter.options.AddCaloricActivity
import main.calorieCounter.caloricstructures.Goal.KeepWeight
import main.calorieCounter.caloricstructures.{ActivityType, CaloricActivity}

object CalorieStateOps {
  def addCaloricActivity(counter: CalorieCounter,
                         activity: AddCaloricActivity,
                         density: Float,
                         activityAttributes: AddCaloricActivity => (String, Int, ActivityType, Date)): CalorieCounter =
  {
    val (name, quantity, activityType, date) = activityAttributes(activity)
    val consumedCalories = (density * quantity).toInt
    val newActivities = counter.activities.appended(CaloricActivity(activityType, name, quantity, consumedCalories, date))
    counter.copy(activities = newActivities)
  }

  def createStates(bodyParams: SetBodyParams): States = {
    val body = createBody(bodyParams.height, bodyParams.weight, bodyParams.age, bodyParams.gender, bodyParams.lifestyle)
    val footPrint = FootPrintState(0, List(), None, List(), None)
    val calorieCounter = CalorieCounter(body, List(), KeepWeight, List((bodyParams.weight, bodyParams.date)))
    States(footPrint, calorieCounter)
  }
}
