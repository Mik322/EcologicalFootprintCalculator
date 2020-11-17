package consoleinterface.caloriescouter

import consoleinterface.{UserChoice,SetGoal}
import consoleinterface.caloriescouter.CaloricActivitiesChoice._
import main.Date
import main.calorieCounter.caloricstructures.GoalType._

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

  def getUserGoal(): UserChoice = {
    println("1. Lose A Lot Of Weight\n2. Lose Weight\n3. Keep Weight\n4. Gain Weight\n5. Gain A Lot Of Weight")

    readLine() match {
      case "1" => SetGoal(LoseALotOfWeight)
      case "2" => SetGoal(LoseWeight)
      case "3" => SetGoal(KeepWeight)
      case "4" => SetGoal(GainWeight)
      case "5" => SetGoal(GainALotOfWeight)
      case _ => getUserGoal()
    }
  }
}
