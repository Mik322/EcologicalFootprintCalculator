package main.calorieCounter

import main.{CalorieCounter, Date, FootPrintState, States}
import consoleinterface.AddCaloricActivity
import consoleinterface.StartOptions.SetBodyParams
import CalorieCounterOps.createBody
import main.calorieCounter.caloricstructures.Goal.KeepWeight
import main.calorieCounter.caloricstructures.{ActivityType, CaloricActivity}

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
    counter.copy(body = newBody, weightHistory = counter.weightHistory.appended((weight, date)))
  }

  def createStates(bodyParams: SetBodyParams): States = {
    val body = createBody(bodyParams.height, bodyParams.height, bodyParams.age, bodyParams.biologicalSex, bodyParams.lifestyle)
    val footPrint = FootPrintState(0, List(), None, List(), None)
    val calorieCounter = CalorieCounter(body, List(), (KeepWeight,Date.today()), List((bodyParams.weight, bodyParams.date)))
    States(footPrint, calorieCounter)
  }
}
