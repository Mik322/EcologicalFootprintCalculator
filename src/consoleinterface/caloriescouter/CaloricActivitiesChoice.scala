package consoleinterface.caloriescouter

import consoleinterface.AddCaloricActivity

object CaloricActivitiesChoice {
  case class AddFood(food: String, quantity: Int) extends AddCaloricActivity
  case class AddSport(sport: String, minutes: Int) extends AddCaloricActivity
  case class AddDrink(drink: String, quantity: Int) extends AddCaloricActivity
}
