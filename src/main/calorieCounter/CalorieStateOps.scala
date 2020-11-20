package main.calorieCounter

import main.{CalorieCounter, Date, FootPrintState, States}
import consoleinterface.AddCaloricActivity
import consoleinterface.StartOptions.{NewProfile}
import CalorieCounterOps.createBody
import main.calorieCounter.caloricstructures.Goal.KeepWeight
import main.calorieCounter.caloricstructures.{ActivityType, CaloricActivity}
import main.footprint.footprintstructs.FootPrintData
import sun.jvm.hotspot.oops.ProfileData

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

  def changeWeight(counter: CalorieCounter, weight: Double, date: Date): CalorieCounter = {
    val newBody = counter.body.copy(weight = weight)
    counter.copy(body = newBody, weightHistoric = counter.weightHistoric.appended((weight, date)))
  }

  def createStates(newProfile: NewProfile): States = {
    val bodyParams = newProfile.bodyParams
    val footPrintData = newProfile.footPrintData
    val body = createBody(bodyParams.height, bodyParams.height, bodyParams.age, bodyParams.gender, bodyParams.lifestyle)
    val footPrint = FootPrintState(0, List(), None, List(), None, footPrintData)
    val calorieCounter = CalorieCounter(body, List(), KeepWeight, List((bodyParams.weight, bodyParams.date)))
    States(footPrint, calorieCounter)
  }
}
