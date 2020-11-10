package consoleinterface.caloriescouter

import consoleinterface.{UserChoice}
import consoleinterface.caloriescouter.CaloricActivitiesChoice._

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

  def getActivityInput(list: List[String], choice: (String)=> UserChoice): UserChoice = {
    print("Select number: ")
    val input = readLine().toInt
    choice(list(input))
  }

  def addFoodChoice(food: String): AddFood = {
    print("Quantity (in grams): ")
    val quantity = readLine().toInt
    AddFood(food, quantity)
  }

  def addDrinkChoice(drink: String): AddDrink = {
    print("Quantity (in mL): ")
    val quantity = readLine().toInt
    AddDrink(drink, quantity)
  }

  def addSportChoice(sport: String): AddSport = {
    print("time: ")
    val time = readLine().toInt
    AddSport(sport, time)
  }
}
