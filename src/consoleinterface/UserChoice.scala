package consoleinterface

import main.calorieCounter.caloricstructures.{Gender, Lifestyle}

trait UserChoice

case object SaveStates extends UserChoice
case object LoadStates extends UserChoice
case class SetBodyParams(height: Int, weight: Int, age: Int, gender: Gender, lifestyle: Lifestyle) extends UserChoice
trait AddCaloricActivity extends UserChoice
case object GetCalories extends UserChoice
case object GetListCaloricActivities extends UserChoice

case object Quit extends UserChoice