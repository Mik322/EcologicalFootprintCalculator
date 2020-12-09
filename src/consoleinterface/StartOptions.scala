package consoleinterface

import main.Date
import main.healthTracker.Body.{BiologicalSex, Lifestyle}

trait StartOptions

object StartOptions {
  case object LoadState extends StartOptions
  case class NewProfile(profileName: String, bodyParams: BodyParams, footPrintData: FootPrintData) extends StartOptions
  case class BodyParams(height: Int, weight: Double, age: Int, biologicalSex: BiologicalSex, lifestyle: Lifestyle, date: Date)
  case class FootPrintData(points: Int, electricityPerMonth: Double)
}
