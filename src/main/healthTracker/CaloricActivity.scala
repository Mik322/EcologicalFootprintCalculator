package main.healthTracker

import consoleinterface.caloriescouter.options.AddCaloricActivity
import consoleinterface.caloriescouter.options.AddCaloricActivity.{AddDrink, AddFood, AddSport, AddWaterCup}
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
    val (density, attributeFunction) = activity match {
      case AddDrink(drink,_,_) => (maps.drinksMap(drink).toFloat/100, drinkAttributes)
      case AddFood(food,_,_) => (maps.foodMap(food).toFloat/100, foodAttributes)
      case AddSport(sport, _, _) =>(-calculateExerciseCalories(maps.exercisesMap(sport), 1, state.body.weight), sportAttributes)
      case AddWaterCup(date) => (0.toFloat, (a: AddCaloricActivity) => ("Water", 250, Water, date))
    }

    addCaloricActivity(state, activity, density, attributeFunction)
  }

  def addCaloricActivity(counter: HealthTracker,
                         activity: AddCaloricActivity,
                         density: Float,
                         activityAttributes: AddCaloricActivity => (String, Int, ActivityType, Date)): HealthTracker =
  {
    val (name, quantity, activityType, date) = activityAttributes(activity)
    val consumedCalories = (density * quantity).toInt
    val newActivities = counter.activities.appended(CaloricActivity(activityType, name, quantity, consumedCalories, date))
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

  def cupsOfWaterToDrink(tracker: HealthTracker, date: Date): Int = HealthCalculations.getRemainingWaterNeeded(tracker, date) / 250
}