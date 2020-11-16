package main.calorieCounter.caloricstructures

import  main.Date

case class CaloricActivity(activityType: ActivityType, name: String, quantity: Int, caloricChange: Int, date: Date)

trait ActivityType;
case object Food extends ActivityType
case object Drink extends ActivityType
case object Sport extends ActivityType