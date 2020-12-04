package main.healthTracker

import main.{Date, States}
import HealthCalculations.createBody
import consoleinterface.caloriescouter.options.AddCaloricActivity
import consoleinterface.StartOptions.NewProfile
import main.States.{HealthTracker, FootPrintState}
import main.healthTracker.caloricstructures.Goal.KeepWeight
import main.healthTracker.caloricstructures.{ActivityType, CaloricActivity}

object CalorieStateOps {
  def addCaloricActivity(counter: HealthTracker,
                         activity: AddCaloricActivity,
                         density: Float,
                         activityAttributes: AddCaloricActivity => (String, Int, ActivityType, Date)): HealthTracker =
  {
    val (name, quantity, activityType, date) = activityAttributes(activity)
    val consumedCalories = (density * quantity).toInt
    val newActivities = counter.activities.appended(CaloricActivity(activityType, name, quantity, consumedCalories, date))
    counter.copy(activities = newActivities)
  }

  def changeWeight(counter: HealthTracker, weight: Double, date: Date): HealthTracker = {
    val newBody = counter.body.copy(weight = weight)
    counter.copy(body = newBody, weightHistory = counter.weightHistory.appended((weight, date)))
  }
  def createStates(newProfile: NewProfile): States = {
    val bodyParams = newProfile.bodyParams
    val footPrintData = newProfile.footPrintData
    val body = createBody(bodyParams.height, bodyParams.height, bodyParams.age, bodyParams.biologicalSex, bodyParams.lifestyle)
    val footPrint = FootPrintState(0, List(), None, List(), None, footPrintData)
    val calorieCounter = HealthTracker(body, List(), (KeepWeight,Date.today()), List((bodyParams.weight, bodyParams.date)), Map())
    States(newProfile.profileName, footPrint, calorieCounter)
  }
}
