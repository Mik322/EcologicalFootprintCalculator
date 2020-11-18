package main

import scala.annotation.tailrec
import fileOperations.FileOperations
import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface.AddCaloricActivity
import consoleinterface._
import calorieCounter.{AddCaloricActivityOps, CaloricInformationOps}
import calorieCounter.caloricstructures.{Body, CaloricMaps}
import calorieCounter.caloricstructures.Goal.KeepWeight
import calorieCounter.CaloricImpure
import main.footprint.TransportMeans._
import main.footprint._
import main.footprint.FootPrintOps
import main.footprint.footprintstructs.energy.{EnergyImpure, EnergySource}
import main.footprint.footprintstructs.transport.TransportationImpure
import main.footprint.footprintstructs.waste.WasteImpure
import footprintstructs.waste.TypeOfWaste


object Application extends App {

  val c = CalorieCounter(None, List(), KeepWeight, List());
  val f = FootPrintState(0, List(), None, List());

  val foodMap = FileOperations.loadCaloriesMap("Food.txt", s => s.toInt)
  val drinksMap = FileOperations.loadCaloriesMap("Drinks.txt", s => s.toInt)
  val exercisesMap = FileOperations.loadCaloriesMap("Exercises.txt", s => s.toDouble)

  val caloricMaps = CaloricMaps(foodMap, drinksMap, exercisesMap)

  @tailrec
  def main_loop(footPrintState: FootPrintState, calorieCounter: CalorieCounter): Unit = {
    printOptions()
    val userChoice = getUserChoice(caloricMaps)

    userChoice match {
      case Quit => {}

      // Saves the current states to a file
      case SaveStates => {
        FileOperations.saveStates(footPrintState, calorieCounter)
        main_loop(footPrintState, calorieCounter)
      }

      // Loads the states that are in the file States in the project directory
      case LoadStates => {
        val states = FileOperations.loadStates()
        states match {
          case None =>
          case Some(value) => main_loop(value._1, value._2)
        }
      }

      // Adds a caloric activity (Food, Drink or Sport) to the calorie counter
      case activity: AddCaloricActivity => {
        val newCalorieCounter = AddCaloricActivityOps.addCaloricActivityToState(activity, calorieCounter, caloricMaps)
        main_loop(footPrintState, newCalorieCounter)
      }

      // Sets the Weight goal
      case SetGoal(goal) => {
        val newCalorieCounter = calorieCounter.copy(goal = goal)
        main_loop(footPrintState, newCalorieCounter)
      }

      // Handles all types of caloric Information requests
      case information: CaloricInformation => {
        CaloricInformationOps.getCaloricInformation(information, calorieCounter)
        main_loop(footPrintState, calorieCounter)
      }

      //Sets the new body
      case SetBodyParams(height, weight, age, gender, lifestyle, date) => {
        val body = Body(height, weight, age, gender, lifestyle)
        val newCalorieCounter = calorieCounter.copy(body = Some(body), weightHistoric = List((weight, date)))
        main_loop(footPrintState, newCalorieCounter)
      }

      // Prints the body params
      case GetBody => {
        CaloricImpure.printBodyInformation(calorieCounter)
        main_loop(footPrintState, calorieCounter)
      }

      case AddTransportTrip(mean: TransportMean, km: Double) => mean match {
        case Car(consumption, fuel) => {
          val newFootPrint = FootPrintOps.addCarConsumption(footPrintState, consumption, km, fuel)
          main_loop(newFootPrint, calorieCounter)
        }
        case publicTransport => {
          val newFootPrint = FootPrintOps.addPublicTransportEmissions(footPrintState, publicTransport, km)
          main_loop(newFootPrint, calorieCounter)
        }
      }

      case GetTransportEmissions => {
        TransportationImpure.printTransportEmissions(footPrintState)
        main_loop(footPrintState, calorieCounter)
      }

      case GetTransportHistory => {
        TransportationImpure.history(footPrintState.transportTrips)
        main_loop(footPrintState, calorieCounter)
      }

      case AddWaste(kg: Int, typeOfWaste: TypeOfWaste) => {
        val newFootPrint = FootPrintOps.addWaste(footPrintState, kg, typeOfWaste)
        main_loop(newFootPrint, calorieCounter)
      }

      case GetWasteEmissions => {
        WasteImpure.printWasteEmissions(footPrintState)
        main_loop(footPrintState, calorieCounter)
      }

      case SetEnergySource(source: EnergySource) => {
        val newFootPrintState = FootPrintOps.setEnergySource(footPrintState, source)
        main_loop(newFootPrintState, calorieCounter)
      }

      case GetEnergyEmissions => {
        EnergyImpure.getEnergyEmissions(footPrintState)
        main_loop(footPrintState, calorieCounter)
      }
    }
  }

  main_loop(f, c)

}
