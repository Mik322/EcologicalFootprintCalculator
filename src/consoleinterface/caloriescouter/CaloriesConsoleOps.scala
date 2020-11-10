package consoleinterface.caloriescouter

import consoleinterface.{AddDrink, AddFood, AddExercise, UserChoice,SetBodyParams}
import main.calorieCounter._
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
  def getBodyInput():UserChoice = {
    print("Height(in cm): ")
    val height=readLine().toInt
    print("Weight(in kg): ")
    val weight=readLine().toDouble
    print("Age: ")
    val age=readLine().toInt
    val gender = BodyInput.genderInput()
    val lifestyle = BodyInput.lifestyleInput()
    SetBodyParams(height,weight,age,gender,lifestyle)
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

  def addExerciseChoice(exercise: String): AddExercise = {
    print("time (minutes): ")
    val time = readLine().toInt
    AddExercise(exercise, time)
  }

}

