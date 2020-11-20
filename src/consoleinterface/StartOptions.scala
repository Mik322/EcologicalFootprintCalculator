package consoleinterface

import main.Date
import main.calorieCounter.caloricstructures.Body.{Gender, Lifestyle}
import main.footprint.footprintstructs.FootPrintData

object StartOptions {
  trait StartOptions

  case object LoadState extends StartOptions
  case class NewProfile(profileName: String, bodyParams: BodyParams, footPrintData: FootPrintData) extends StartOptions
  case class BodyParams(height: Int, weight: Double, age: Int, gender: Gender, lifestyle: Lifestyle, date: Date)
}
