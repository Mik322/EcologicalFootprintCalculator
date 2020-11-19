package main

import scala.annotation.tailrec
import fileOperations.FileOperations
import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface.AddCaloricActivity
import consoleinterface._
import calorieCounter.{AddCaloricActivityOps, CaloricImpure, CaloricInformationOps, CalorieStateOps}
import calorieCounter.caloricstructures.CaloricMaps
import consoleinterface.caloriescouter.CaloriesConsoleOps
import main.footprint.TransportMeans._
import main.footprint._
import main.footprint.FootPrintOps
import main.footprint.footprintstructs.energy.{EnergyImpure, EnergySource}
import main.footprint.footprintstructs.transport.TransportationImpure
import main.footprint.footprintstructs.waste.WasteImpure
import footprintstructs.waste.TypeOfWaste
import main.footprint.footprintstructs.WaterImpure


object Application extends App {

  val foodMap = FileOperations.loadCaloriesMap("Food.txt", s => s.toInt)
  val drinksMap = FileOperations.loadCaloriesMap("Drinks.txt", s => s.toInt)
  val exercisesMap = FileOperations.loadCaloriesMap("Exercises.txt", s => s.toDouble)

  val caloricMaps = CaloricMaps(foodMap, drinksMap, exercisesMap)

  @tailrec
  def main_loop(footPrintState: FootPrintState, calorieCounter: CalorieCounter): Unit = {
    printOptions()
    val userChoice = getUserChoice(caloricMaps)

    userChoice match {
      case Quit =>

      // Saves the current states to a file
      case SaveStates =>
        FileOperations.saveStates(footPrintState, calorieCounter)
        main_loop(footPrintState, calorieCounter)

      // Adds a caloric activity (Food, Drink or Sport) to the calorie counter
      case activity: AddCaloricActivity =>
        val newCalorieCounter = AddCaloricActivityOps.addCaloricActivityToState(activity, calorieCounter, caloricMaps)
        main_loop(footPrintState, newCalorieCounter)

      // Sets the Weight goal
      case SetGoal(goal) =>
        val newCalorieCounter = calorieCounter.copy(goal = goal)
        main_loop(footPrintState, newCalorieCounter)

      // Handles all types of caloric Information requests
      case information: CaloricInformation =>
        CaloricInformationOps.getCaloricInformation(information, calorieCounter)
        main_loop(footPrintState, calorieCounter)

      // Prints the body params
      case GetBody =>
        CaloricImpure.printBodyInformation(calorieCounter.body)
        main_loop(footPrintState, calorieCounter)

      case ChangeWeight(weight, date) =>
        val newCalorieCounter = CalorieStateOps.changeWeight(calorieCounter, weight, date)
        main_loop(footPrintState, newCalorieCounter)

      case AddTransportTrip(mean: TransportMean, km: Double) => mean match {
        case Car(consumption, fuel) =>
          val newFootPrint = FootPrintOps.addCarConsumption(footPrintState, consumption, km, fuel)
          main_loop(newFootPrint, calorieCounter)
        case publicTransport =>
          val newFootPrint = FootPrintOps.addPublicTransportEmissions(footPrintState, publicTransport, km)
          main_loop(newFootPrint, calorieCounter)
      }

      case GetTransportEmissions =>
        TransportationImpure.printTransportEmissions(footPrintState)
        main_loop(footPrintState, calorieCounter)

      case GetTransportHistory =>
        TransportationImpure.history(footPrintState.transportTrips)
        main_loop(footPrintState, calorieCounter)

      case AddWaste(kg: Int, typeOfWaste: TypeOfWaste) =>
        val newFootPrint = FootPrintOps.addWaste(footPrintState, kg, typeOfWaste)
        main_loop(newFootPrint, calorieCounter)

      case GetWasteEmissions =>
        WasteImpure.printWasteEmissions(footPrintState)
        main_loop(footPrintState, calorieCounter)

      case SetEnergySource(source: EnergySource) =>
        val newFootPrintState = FootPrintOps.setEnergySource(footPrintState, source)
        main_loop(newFootPrintState, calorieCounter)

      case GetEnergyEmissions =>
        EnergyImpure.getEnergyEmissions(footPrintState)
        main_loop(footPrintState, calorieCounter)

      case SetWaterConsumption(amount: Double) =>
        val newFootPrintState = FootPrintOps.setWaterConsumption(footPrintState, amount)
        main_loop(newFootPrintState, calorieCounter)

      case GetWaterEmissions =>
        WaterImpure.printWaterImpure(footPrintState)
        main_loop(footPrintState,calorieCounter)
    }
  }

  def start() = {
    //Gets the choice load or create new profile
    val choice = ConsoleOps.FirstPrompt()

    //Gets the states
    val states = choice match {
      case StartOptions.LoadState => FileOperations.loadStates() match {
        //If there is no state saved loadStates returns None so it asks for a new Profile
        case None => {
          FileOperations.printLoadError()
          val bodyParams = CaloriesConsoleOps.getBodyInput()
          CalorieStateOps.createStates(bodyParams)
        }
        case Some(states) => states
      }
      case bodyParams: StartOptions.SetBodyParams => CalorieStateOps.createStates(bodyParams)
    }

    main_loop(states.footPrintState, states.calorieCounter)
  }

  start()
}
