package main

import main.footprint.footprintstructs.FootPrintData
import main.footprint.footprintstructs.energy.EnergySource
import main.footprint.footprintstructs.transport.TransportTrip
import main.footprint.footprintstructs.waste.Waste
import States._
import consoleinterface.StartOptions.NewProfile
import consoleinterface.caloriescouter.options.AddCaloricActivity
import main.healthTracker.CaloricActivity.ActivityType
import main.healthTracker.{Body, CaloricActivity, Goal}
import main.healthTracker.HealthCalculations.createBody
import main.healthTracker.Goal.KeepWeight

case class States(profileName: String, footPrintState: FootPrintState, healthTracker: HealthTracker)

object States {
  case class FootPrintState(carbonFootPrint: Double, transportTrips: List[TransportTrip], waste: Option[Waste], energySources: List[EnergySource], water: Option[Double], footPrintData: FootPrintData)
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
