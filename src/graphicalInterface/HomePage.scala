package graphicalInterface

import graphicalInterface.footprintCalculator.FootprintCalculator
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Node, Parent}
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.stage.Stage
import main.States
import main.States.{FootPrintState, HealthTracker}
import main.fileOperations.FileOperations.{loadCaloriesMap, saveStates}
import main.healthTracker.CaloricMaps
import healthTracker.HealthTrackerInterface

class HomePage {
  @FXML
  private var label: Label = _
  @FXML
  private var homePage: Pane = _

  var states: States = _

  private var footPrintPane: Parent = _
  private var healthTrackerPane: Parent = _

  def footprintCalculator(): Unit = setHomePane(footPrintPane)

  def healthTracker(): Unit = setHomePane(healthTrackerPane)

  def saveProfile(): Unit = {
    saveStates(states)
  }

  def quit(): Unit = {
    homePage.getScene.getWindow.asInstanceOf[Stage].close()
  }

  def updateFootPrint(footPrintState: FootPrintState): Unit = states = states.copy(footPrintState = footPrintState)
  def updateHealthTracker(healthTracker: HealthTracker): Unit = states = states.copy(healthTracker = healthTracker)

  def getFootPrint: FootPrintState = states.footPrintState
  def getHealthTracker: HealthTracker = states.healthTracker

  def initialize(states: States): Unit = {
    this.states = states
    val int = converter(s => s.toInt) _
    val caloricMaps = CaloricMaps(loadCaloriesMap("Food.txt", int), loadCaloriesMap("Drinks.txt", int), loadCaloriesMap("Exercises.txt", s => s.toDouble))

    val footPrintLoader = new FXMLLoader(getClass.getResource("footprintCalculator/FootprintCalculator.fxml"))
    val healthTrackerLoader =new FXMLLoader(getClass.getResource("healthTracker/HealthTrackerInterface.fxml"))
    footPrintPane = footPrintLoader.load()
    healthTrackerPane = healthTrackerLoader.load()
    footPrintLoader.getController[FootprintCalculator].initialize(this)
    healthTrackerLoader.getController[HealthTrackerInterface].initialize(this, caloricMaps)
  }

  private def converter[A](func: String => A)(value: String): A = func(value)

  private def setHomePane(node: Node): Unit = {
    homePage.getChildren.clear()
    homePage.getChildren.add(node)
  }

}
