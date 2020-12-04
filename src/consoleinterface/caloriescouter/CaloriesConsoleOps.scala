package consoleinterface.caloriescouter

import consoleinterface.caloriescouter.options.AddCaloricActivity._
import consoleinterface.StartOptions.BodyParams
import consoleinterface.{AddSleep, DateChoice, SetGoal, UserChoice}
import main.Date
import main.healthTracker.caloricstructures.Goal._
import inputs.BodyInput
import main.healthTracker.caloricstructures.Goal

import scala.io.StdIn.readLine

object CaloriesConsoleOps {
  def printList(list: List[String], index: Int): Unit = {
    list match {
      case ::(head, next) => {
        println(s"${index}.  ${head.split(':')(0)}")
        printList(next, index + 1)
      }
      case Nil => {}
    }
  }

  def getBodyInput(): BodyParams = {
    println("Insert your body parameters")
    val height = BodyInput.heightInput()
    val weight = BodyInput.weightInput()
    val age = BodyInput.ageInput()
    val biologicalSex = BodyInput.biologicalSexInput()
    val lifestyle = BodyInput.lifestyleInput()
    BodyParams(height, weight, age, biologicalSex, lifestyle, Date.today())
  }

  def getActivityInput(list: List[String], choice: (String, Date) => UserChoice, date: Date): UserChoice = {
    print("Select number: ")
    try {
      readLine().toInt match {
        case input if (input < 0 || input >= list.size) =>
          println("Wrong number selection, please try again")
          getActivityInput(list, choice, date)
        case input => choice(list(input), date)
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid number")
        getActivityInput(list, choice, date)
    }

  }

  def addFoodChoice(food: String, date: Date): AddFood = {
    print("Quantity (in grams): ")
    try{
      readLine().toInt match {
        case quantity if(quantity<0) =>
          println("You can't eat a negative quantity, please try again")
          addFoodChoice(food,date)
        case quantity => AddFood(food, quantity, date)
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid number")
        addFoodChoice(food,date)
    }

  }

  def addDrinkChoice(drink: String, date: Date): AddDrink = {
    print("Quantity (in mL): ")
    try{
      readLine().toInt match {
        case quantity if(quantity<0) =>
          println("You can't drink a negative quantity, please try again")
          addDrinkChoice(drink,date)
        case quantity => AddDrink(drink, quantity, date)
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid number")
        addDrinkChoice(drink,date)
    }
  }

  def addSportChoice(sport: String, date: Date): AddSport = {
    print("time(in minutes): ")
    try{
      readLine().toInt match {
        case quantity if(quantity<0) =>
          println("You can't train a negative time, please try again")
          addSportChoice(sport,date)
        case quantity => AddSport(sport, quantity, date)
      }
    } catch {
      case _: NumberFormatException =>
        println("Invalid number")
        addSportChoice(sport,date)
    }
  }

  def getUserGoal(): UserChoice = {
    println("1. Lose 1kg per week\n2. Lose 0.5kg per week\n3. Keep Weight\n4. Gain 0.5kg per week\n5. Gain 1kg per week")
    SetGoal(getGoalInput(), Date.today())
  }

  private def getGoalInput(): Goal.Value = readLine() match {
    case "1" => LoseALotOfWeight
    case "2" => LoseWeight
    case "3" => KeepWeight
    case "4" => GainWeight
    case "5" => GainALotOfWeight
    case _ =>
      println("Invalid option. Please try again.")
      getGoalInput()
  }

  def getSleep(): UserChoice = {
    try{
      println("Type in the amount of hours of your sleep:")
      val hours = readLine().toInt
      println("Please enter the date of your sleep:")
      val date = DateChoice.getUserDate()
      AddSleep(hours,date)
    }catch{
      case _: NumberFormatException => {
        println("Invalid input")
        getSleep()
      }
    }
  }
}


