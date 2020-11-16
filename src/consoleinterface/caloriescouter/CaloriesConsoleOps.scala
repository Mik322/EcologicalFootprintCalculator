package consoleinterface.caloriescouter

import consoleinterface.UserChoice
import consoleinterface.caloriescouter.CaloricActivitiesChoice._
import main.Date

import scala.io.StdIn.readLine

object CaloriesConsoleOps {
  def printList(list: List[String], index: Int): Unit = {
    list match {
      case ::(head, next) => {
        println(s"${index}.  ${head.split(':')(0)}")
        printList(next, index+1)
      }
      case Nil => {}
    }
  }

  def getActivityInput(list: List[String], choice: (String, Date)=> UserChoice, date: Date): UserChoice = {
    print("Select number: ")
    val input = readLine().toInt
    choice(list(input), date)
  }

  def addFoodChoice(food: String, date: Date): AddFood = {
    print("Quantity (in grams): ")
    val quantity = readLine().toInt
    AddFood(food, quantity, date)
  }

  def addDrinkChoice(drink: String, date: Date): AddDrink = {
    print("Quantity (in mL): ")
    val quantity = readLine().toInt
    AddDrink(drink, quantity, date)
  }

  def addSportChoice(sport: String, date: Date): AddSport = {
    print("time: ")
    val time = readLine().toInt
    AddSport(sport, time, date)
  }
}
