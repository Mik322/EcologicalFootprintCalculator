package consoleinterface

import main.Date
import main.calorieCounter.caloricstructures.{Gender, Lifestyle}

object StartOptions {
  trait StartOptions

  case object LoadState extends StartOptions
  case class SetBodyParams(height: Int, weight: Double, age: Int, gender: Gender, lifestyle: Lifestyle, date: Date) extends StartOptions
}
