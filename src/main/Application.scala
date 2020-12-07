package main

import scala.annotation.tailrec
import fileOperations.FileOperations
import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface.UserChoice.{AddCar, AddSleep, AddTransportTrip, AddWaste, GetBody, GetEcologicalFootPrint, GetEnergyEmissions, GetTotalEmissions, GetTransportEmissions, GetTransportHistory, GetWasteEmissions, GoToMainMenu, Quit, SaveStates, SetEnergySource, SetGoal}
import consoleinterface._
import healthTracker.{Body, CaloricActivity, CaloricMaps}
import consoleinterface.caloriescouter.options.{AddCaloricActivity, BodyChange, CaloricInformation}
import main.healthTracker.CaloricInformationOps.getCaloricInformationString
import main.footprint.{FootPrintOps, StaticData}
import main.footprint.energy.{Electricity, EnergySource}
import main.footprint.transport.{Car, Fuel, TransportMean, TransportTrip}
import main.healthTracker.sleepTracker.SleepTracker.addSleep
import main.fileOperations.FileOperations._
import main.footprint.waste.{TypeOfWaste, Waste}


object Application extends App {

  val foodMap = loadCaloriesMap("Food.txt", s => s.toInt)
  val drinksMap = loadCaloriesMap("Drinks.txt", s => s.toInt)
  val exercisesMap = loadCaloriesMap("Exercises.txt", s => s.toDouble)

  val caloricMaps = CaloricMaps(foodMap, drinksMap, exercisesMap)

  @tailrec
  def main_loop(states: States): Unit = {
    printOptions()
    val userChoice = getUserChoice(caloricMaps, states.footPrintState.cars)

    userChoice match {
      case Quit =>

      case GoToMainMenu => main_loop(states)

      // Saves the current states to a file
      case SaveStates =>
        saveStates(states)
        main_loop(states)

      // Adds a caloric activity (Food, Drink or Sport) to the calorie counter
      case activity: AddCaloricActivity =>
        val newCalorieCounter = CaloricActivity.addCaloricActivityToState(activity, states.healthTracker, caloricMaps)
        main_loop(states.copy(healthTracker = newCalorieCounter))

      // Sets the Weight goal
      case SetGoal(goal,date) => {
        val newCalorieCounter = states.healthTracker.copy(goal = (goal,date))
        main_loop(states.copy(healthTracker = newCalorieCounter))
      }

      // Handles all types of caloric Information requests
      case information: CaloricInformation =>
        val infoString = getCaloricInformationString(information, states.healthTracker)
        printString(infoString)
        main_loop(states)

      // Prints the body params
      case GetBody =>
        val bodyString = Body.getBodyInformationString(states.healthTracker.body)
        printString(bodyString);
        main_loop(states)

        //Handles all types of body change
      case bodyParam: BodyChange =>
        val newCalorieCounter = Body.changeBody(bodyParam, states.healthTracker)
        main_loop(states.copy(healthTracker = newCalorieCounter))

      case sleep: AddSleep => {
        val newCalorieCounter = states.healthTracker.copy(sleepTracker = addSleep(states.healthTracker.sleepTracker,sleep))
        main_loop(states.copy(healthTracker = newCalorieCounter))
      }

      case AddCar(name: String, consumption: Double, fuel: Fuel) => {
        val car = Car(name, consumption, fuel)
        val new_cars = states.footPrintState.cars.appended(car)
        val newFootPrint = states.footPrintState.copy(cars = new_cars)
        main_loop(states.copy(footPrintState = newFootPrint))
      }

      case AddTransportTrip(mean: TransportMean, km: Double, date: Date) => {
        val newFootPrint = FootPrintOps.addTrip(states.footPrintState, mean, km, date)
        main_loop(states.copy(footPrintState = newFootPrint))
      }

      case GetTransportEmissions =>
        val emissions = TransportTrip.getTransportEmissionsString(states.footPrintState)
        printString(emissions)
        main_loop(states)

      case GetTransportHistory =>
        val history = TransportTrip.history(states.footPrintState.transportTrips)
        printString(history)
        main_loop(states)

      case AddWaste(kg: Int, typeOfWaste: TypeOfWaste) =>
        val newFootPrint = FootPrintOps.addWaste(states.footPrintState, kg, typeOfWaste)
        main_loop(states.copy(footPrintState = newFootPrint))

      case GetWasteEmissions =>
        val emissions = Waste.printWasteEmissions(states.footPrintState)
        printString(emissions)
        main_loop(states)

      case SetEnergySource(source: EnergySource) =>
       //val newFootPrintState = Electricity.setEnergySource(states.footPrintState, source)
        //main_loop(states.copy(footPrintState = newFootPrintState))

      case GetEnergyEmissions =>
        val emissions = EnergySource.getEnergyEmissionsString(states.footPrintState)
        printString(emissions)
        main_loop(states)

      case GetEcologicalFootPrint => {
        val earths = StaticData.getEarthsConsumedString(states.footPrintState)
        printString(earths)
        main_loop(states)
      }

      case GetTotalEmissions => {
        val emissions = FootPrintOps.getTotalEmissionsString(states.footPrintState)
        printString(emissions)
        main_loop(states)
      }
    }
  }

  private def printString(s: String): Unit = println(s)

  def start(): Unit = {
    //Gets the choice load or create new profile
    val choice = ConsoleOps.FirstPrompt()

    //Gets the states
    val states = choice match {
      case StartOptions.LoadState => {
        val username = FileOperations.getUsername()
        loadStates(username) match {
          //If there is no state saved loadStates returns None so it asks for a new Profile
          case None => {
            printLoadError
            val profileData = ConsoleOps.newProfile()
            States.createStates(profileData)
          }
          case Some(states) => states
        }
      }
      case profileData: StartOptions.NewProfile => States.createStates(profileData)
    }

    main_loop(states)
  }
  start()
}
