package main.calorieCounter.caloricstructures

case class Body(height: Int, weight: Int, age: Int, gender: Gender, lifestyle: Lifestyle)

trait Gender
case object Male extends Gender
case object Female extends Gender

trait Lifestyle
case object Sedentary extends Lifestyle
case object Moderated extends Lifestyle
case object Active extends Lifestyle
case object Athlete extends Lifestyle