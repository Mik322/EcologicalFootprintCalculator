package consoleinterface.caloriescouter

import consoleinterface.caloriescouter.options.AddCaloricActivity._
import consoleinterface.StartOptions.BodyParams
import consoleinterface.{SetGoal, UserChoice}
import main.Date
import main.calorieCounter.caloricstructures.Goal._
import inputs.BodyInput

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
  def getBodyInput(): BodyParams = {
  println("Insert your body parameters")
    print("Height(in cm): ")
    val height=readLine().toInt
    print("Weight(in kg): ")
    val weight=readLine().toDouble
    val age=BodyInput.ageInput()
    val biologicalSex = BodyInput.biologicalSexInput()
    val lifestyle = BodyInput.lifestyleInput()
    BodyParams(height,weight,age,biologicalSex,lifestyle, Date.today())
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
    println("1. Lose 1kg per week\n2. Lose 0.5kg per week\n3. Keep Weight\n4. Gain 0.5kg per week\n5. Gain 1kg per week")
    SetGoal(getGoalInput(),Date.today())
  }

  private def getGoalInput(): Goal = readLine() match {
      case "1" => LoseALotOfWeight
      case "2" => LoseWeight
      case "3" => KeepWeight
      case "4" => GainWeight
      case "5" => GainALotOfWeight
      case _ =>
        println("Invalid number. Please try again.")
        getGoalInput()
  }
}


