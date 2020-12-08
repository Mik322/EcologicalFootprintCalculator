package main

import main.footprint.energy.Electricity
import main.footprint.transport.{Car, TransportTrip}
import States._
import consoleinterface.StartOptions.NewProfile
import main.footprint.waste.Waste
import main.healthTracker.{Body, CaloricActivity, Goal}
import main.healthTracker.Body.createBody
import main.healthTracker.Goal.KeepWeight

case class States(profileName: String, footPrintState: FootPrintState, healthTracker: HealthTracker)

object States {
  case class FootPrintState(points: Int, transportTrips: List[TransportTrip], cars: List[Car], waste: Waste, electricity: Electricity)
  case class HealthTracker(body: Body, activities: List[CaloricActivity], goal: (Goal.Value,Date), weightHistory: List[(Double, Date)], sleepTracker: Map[Date, Int])

  def createStates(newProfile: NewProfile): States = {
    val bodyParams = newProfile.bodyParams
    val body = createBody(bodyParams.height, bodyParams.weight, bodyParams.age, bodyParams.biologicalSex, bodyParams.lifestyle)
    val footPrint = FootPrintState(newProfile.footPrintData.points, List(), List(), Waste(0,0), Electricity(newProfile.footPrintData.electricityPerMonth, List()))
    val healthTracker = HealthTracker(body, List(), (KeepWeight,Date.today()), List((bodyParams.weight, bodyParams.date)), Map())
    States(newProfile.profileName, footPrint, healthTracker)
  }
}
