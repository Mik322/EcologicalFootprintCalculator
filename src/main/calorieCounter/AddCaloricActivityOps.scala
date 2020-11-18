package main.calorieCounter

import consoleinterface._
import main.CalorieCounter
import CalorieCounterOps.calculateExerciseCalories
import consoleinterface.caloriescouter.CaloricActivitiesChoice._
import CalorieStateOps.addCaloricActivity
import main.calorieCounter.caloricstructures.{Body, CaloricMaps, Drink, Food, Sport}

object AddCaloricActivityOps {

  def addCaloricActivityToState(activity: AddCaloricActivity, state: CalorieCounter, maps: CaloricMaps): CalorieCounter = activity match {
    case AddDrink(_,_,_) => addCaloricActivity(state, activity , drinkDensity(activity, maps), drinkAttributes)
    case AddFood(_,_,_) => addCaloricActivity(state, activity, foodDensity(activity, maps), foodAttributes)
    case AddSport(_,_,_) => addCaloricActivity(state, activity, sportDensity(activity, maps, state.body), sportAttributes)
  }

  def drinkDensity(activity: AddCaloricActivity, maps: CaloricMaps): Option[Float] = {
    val density = maps.drinksMap get activity.asInstanceOf[AddDrink].drink
    density match {
      case None => None
      case Some(value) => Some(value.asInstanceOf[Float]/100)
    }
  }

  def foodDensity(activity: AddCaloricActivity, maps: CaloricMaps): Option[Float] = {
    val density = maps.foodMap get activity.asInstanceOf[AddFood].food
    density match {
      case None => None
      case Some(value) => Some(value.asInstanceOf[Float]/100)
    }
  }

  def sportDensity(activity: AddCaloricActivity, maps: CaloricMaps, body: Option[Body]): Option[Float] = {
    val density = maps.exercisesMap get activity.asInstanceOf[AddSport].sport
    density match {
      case None => None
      case Some(exerciseMET) => body match {
        case None => None
        case Some(bodyParams) => Some(-calculateExerciseCalories(exerciseMET, 1, bodyParams.weight))
      }
    }
  }

  def foodAttributes(activity: AddCaloricActivity) = {
    val food = activity.asInstanceOf[AddFood]
    (food.food, food.quantity, Food, food.date)
  }

  def drinkAttributes(activity: AddCaloricActivity) = {
    val drink = activity.asInstanceOf[AddDrink]
    (drink.drink, drink.quantity, Drink, drink.date)
  }

  def sportAttributes(activity: AddCaloricActivity) = {
    val sport = activity.asInstanceOf[AddSport]
    (sport.sport, sport.minutes, Sport, sport.date)
  }

}
