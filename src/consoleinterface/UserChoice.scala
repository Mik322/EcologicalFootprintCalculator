package consoleinterface

import main.calorieCounter.{Gender, Lifestyle}
import main.calorieCounter.Body._

trait UserChoice

case object SaveStates extends UserChoice
case object LoadStates extends UserChoice
case class SetBodyParams(height: Int, weight: Double, age: Int, gender: Gender, lifestyle: Lifestyle) extends UserChoice
case class AddFood(food: String, quantity: Int) extends UserChoice
case class AddExercise(exercise: String, time: Int) extends UserChoice
case class AddDrink(drink: String, quantity: Int) extends UserChoice
case object GetCalories extends UserChoice
case object GetBody extends UserChoice

case object Quit extends UserChoice