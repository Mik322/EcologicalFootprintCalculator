package main.calorieCounter

import consoleinterface._
import main.CalorieCounter
import consoleinterface.caloriescouter.CaloricActivitiesChoice._
import CalorieStateOps.{addDrinkCalories, addFoodCalories}

object AddCaloricActivityOps {

  def addCaloricActityToState(activity: AddCaloricActivity, state: CalorieCounter, maps: CaloricMaps): CalorieCounter = activity match {
    case AddDrink(_,_) => addDrinkCalories(state, activity.asInstanceOf[AddDrink], maps.drinksMap)
    case AddFood(_,_) => addFoodCalories(state, activity.asInstanceOf[AddFood], maps.foodMap)
    case AddSport(_,_) => state
  }

}
