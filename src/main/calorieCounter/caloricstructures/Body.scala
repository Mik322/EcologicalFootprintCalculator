package main.calorieCounter.caloricstructures

case class Body(height: Int, weight: Double, age: Int, gender: Body.Gender, lifestyle: Body.Lifestyle)

object Body {
  trait Gender
  case object Male extends Gender
  case object Female extends Gender

  trait Lifestyle
  case object Sedentary extends Lifestyle
  case object Moderated extends Lifestyle
  case object Active extends Lifestyle
  case object VeryActive extends Lifestyle
  case object ExtremelyActive extends Lifestyle
}
