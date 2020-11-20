package main.calorieCounter.caloricstructures

case class Body(height: Int, weight: Double, age: Int, biologicalSex: BiologicalSex, lifestyle: Lifestyle)

object Body {
  trait BiologicalSex
  case object Male extends BiologicalSex
  case object Female extends BiologicalSex

  trait Lifestyle
  case object Sedentary extends Lifestyle
  case object Moderated extends Lifestyle
  case object Active extends Lifestyle
  case object VeryActive extends Lifestyle
  case object ExtremelyActive extends Lifestyle
}
