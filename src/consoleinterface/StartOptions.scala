package consoleinterface

import main.Date
import main.calorieCounter.caloricstructures.{BiologicalSex, Lifestyle}

object StartOptions {
  trait StartOptions

  case object LoadState extends StartOptions
  case class SetBodyParams(height: Int, weight: Double, age: Int, biologicalSex: BiologicalSex, lifestyle: Lifestyle, date: Date) extends StartOptions
}
