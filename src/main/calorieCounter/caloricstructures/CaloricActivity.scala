package main.calorieCounter.caloricstructures

case class CaloricActivity(activityType: ActivityType, name: String, quantity: Int, caloricChange: Int)

trait ActivityType;
case object Food extends ActivityType
case object Drink extends ActivityType
case object Sport extends ActivityType