package main.healthTracker

import consoleinterface.healthtracker.options.AddCaloricActivity
import consoleinterface.healthtracker.options.AddCaloricActivity.{AddDrink, AddFood, AddSport, AddWaterCup}
import main.healthTracker.HealthCalculations.calculateExerciseCalories
import main.Date
import main.States.HealthTracker

case class CaloricActivity(activityType: CaloricActivity.ActivityType, name: String, quantity: Int, caloricChange: Int, date: Date)

object CaloricActivity {
  trait ActivityType;
  case object Food extends ActivityType
  case object Drink extends ActivityType
  case object Sport extends ActivityType
  case object Water extends ActivityType

  def addCaloricActivityToState(activity: AddCaloricActivity, state: HealthTracker, maps: CaloricMaps): HealthTracker = {
    val (calories, attributeFunction) = activity match {
      case AddDrink(drink,quantity,_) => ((maps.drinksMap(drink).toFloat/100 * quantity).toInt, drinkAttributes)
      case AddFood(food,quantity,_) => ((maps.foodMap(food).toFloat/100 * quantity).toInt, foodAttributes)
      case AddSport(sport, minutes, _) =>(-calculateExerciseCalories(maps.exercisesMap(sport), minutes, state.body.weight).toInt, sportAttributes)
      case AddWaterCup(date) => (0, (a: AddCaloricActivity) => ("Water", 250, Water, date))
    }

    addCaloricActivity(state, activity, calories, attributeFunction)
  }

  def addCaloricActivity(counter: HealthTracker,
                         activity: AddCaloricActivity,
                         calories: Int,
                         activityAttributes: AddCaloricActivity => (String, Int, ActivityType, Date)): HealthTracker =
  {
    val (name, quantity, activityType, date) = activityAttributes(activity)
    val newActivities = counter.activities.appended(CaloricActivity(activityType, name, quantity, calories, date))
    counter.copy(activities = newActivities)
  }

  def foodAttributes = (activity: AddCaloricActivity) => {
    val food = activity.asInstanceOf[AddFood]
    (food.food, food.quantity, Food, food.date)
  }

  def drinkAttributes = (activity: AddCaloricActivity) => {
    val drink = activity.asInstanceOf[AddDrink]
    (drink.drink, drink.quantity, Drink, drink.date)
  }

  def sportAttributes = (activity: AddCaloricActivity) => {
    val sport = activity.asInstanceOf[AddSport]
    (sport.sport, sport.minutes, Sport, sport.date)
  }

  def cupsOfWaterToDrinkAndDrank(tracker: HealthTracker, date: Date): (Int,Int) = (HealthCalculations.getRemainingWaterNeeded(tracker, date)._1/250,HealthCalculations.getRemainingWaterNeeded(tracker, date)._2 /250)
}