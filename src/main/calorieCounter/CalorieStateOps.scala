package main.calorieCounter

import main.{Date, States}
import CalorieCalculations.createBody
import consoleinterface.caloriescouter.options.AddCaloricActivity
import consoleinterface.StartOptions.NewProfile
import main.States.{CalorieCounter, FootPrintState}
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

  def createStates(newProfile: NewProfile): States = {
    val bodyParams = newProfile.bodyParams
    val footPrintData = newProfile.footPrintData
    val body = createBody(bodyParams.height, bodyParams.height, bodyParams.age, bodyParams.gender, bodyParams.lifestyle)
    val footPrint = FootPrintState(0, List(), None, List(), None, footPrintData)
    val calorieCounter = CalorieCounter(body, List(), KeepWeight, List((bodyParams.weight, bodyParams.date)), Map())
    States(newProfile.profileName, footPrint, calorieCounter)
  }
}
