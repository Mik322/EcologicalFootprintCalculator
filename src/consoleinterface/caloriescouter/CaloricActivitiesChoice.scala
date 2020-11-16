package consoleinterface.caloriescouter

import consoleinterface.AddCaloricActivity
import main.Date

object CaloricActivitiesChoice {
  case class AddFood(food: String, quantity: Int, date: Date) extends AddCaloricActivity
  case class AddSport(sport: String, minutes: Int, date: Date) extends AddCaloricActivity
  case class AddDrink(drink: String, quantity: Int, date: Date) extends AddCaloricActivity
}
