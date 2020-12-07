package main

import main.footprint.energy.Electricity
import main.footprint.transport.{Car, TransportTrip}
import States._
import consoleinterface.StartOptions.NewProfile
import main.footprint.StaticData
import main.footprint.waste.Waste
import main.healthTracker.{Body, CaloricActivity, Goal}
import main.healthTracker.Body.createBody
import main.healthTracker.Goal.KeepWeight

case class States(profileName: String, footPrintState: FootPrintState, healthTracker: HealthTracker)

object States {
  case class FootPrintState(transportTrips: List[TransportTrip], cars: List[Car], waste: Waste, electricity: Electricity, staticData: StaticData)
  case class HealthTracker(body: Body, activities: List[CaloricActivity], goal: (Goal.Value,Date), weightHistory: List[(Double, Date)], sleepTracker: Map[Date, Int])

  def createStates(newProfile: NewProfile): States = {
    val bodyParams = newProfile.bodyParams
    val staticData = StaticData(newProfile.footPrintData.points, newProfile.footPrintData.kmByCarPerMonth, newProfile.footPrintData.consumptionCar)
    val body = createBody(bodyParams.height, bodyParams.height, bodyParams.age, bodyParams.biologicalSex, bodyParams.lifestyle)
    val footPrint = FootPrintState(List(), List(), Waste(0,0), Electricity(newProfile.footPrintData.electricityPerMonth, List()), staticData)
    val calorieCounter = HealthTracker(body, List(), (KeepWeight,Date.today()), List((bodyParams.weight, bodyParams.date)), Map())
    States(newProfile.profileName, footPrint, calorieCounter)
  }

}
