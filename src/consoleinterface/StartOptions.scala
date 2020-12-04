package consoleinterface

import main.Date
import main.healthTracker.caloricstructures.Body.{BiologicalSex, Lifestyle}
import main.footprint.footprintstructs.FootPrintData

object StartOptions {
  trait StartOptions

  case object LoadState extends StartOptions
  case class NewProfile(profileName: String, bodyParams: BodyParams, footPrintData: FootPrintData) extends StartOptions
  case class BodyParams(height: Int, weight: Double, age: Int, biologicalSex: BiologicalSex, lifestyle: Lifestyle, date: Date)
}
