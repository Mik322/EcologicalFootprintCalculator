package main

import main.footprint.energy.EnergySource
import main.footprint.transport.TransportTrip
import States._
import consoleinterface.StartOptions.NewProfile
import main.footprint.FootPrintData
import main.footprint.waste.Waste
import main.healthTracker.{Body, CaloricActivity, Goal}
import main.healthTracker.Body.createBody
import main.healthTracker.Goal.KeepWeight

case class States(profileName: String, footPrintState: FootPrintState, healthTracker: HealthTracker)

object States {
  case class FootPrintState(ecologicalFootPrint: Double, transportTrips: List[TransportTrip], waste: Option[Waste], energySources: List[EnergySource], water: Option[Double], footPrintData: FootPrintData)
  case class HealthTracker(body: Body, activities: List[CaloricActivity], goal: (Goal.Value,Date), weightHistory: List[(Double, Date)], sleepTracker: Map[Date, Int])

  def createStates(newProfile: NewProfile): States = {
    val bodyParams = newProfile.bodyParams
    val footPrintData = newProfile.footPrintData
    val body = createBody(bodyParams.height, bodyParams.height, bodyParams.age, bodyParams.biologicalSex, bodyParams.lifestyle)
    val footPrint = FootPrintState(0, List(), None, List(), None, footPrintData)
    val calorieCounter = HealthTracker(body, List(), (KeepWeight,Date.today()), List((bodyParams.weight, bodyParams.date)), Map())
    States(newProfile.profileName, footPrint, calorieCounter)
  }

}
