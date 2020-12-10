package graphicalInterface

import graphicalInterface.FxApp.loadPage
import graphicalInterface.footprintCalculator.FootprintCalculator
import graphicalInterface.healthTracker.HealthTrackerInterface
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.{Pane, VBox}
import javafx.stage.Stage
import main.States
import main.States.{FootPrintState, HealthTracker}
import main.fileOperations.FileOperations.{loadCaloriesMap, saveStates}
import main.healthTracker.CaloricMaps

class HomePage {
  @FXML
  private var label: Label = _
  @FXML
  private var homePane: Pane = _
  @FXML
  private var side_bar: VBox = _

  var states: States = _
  private var caloricMaps: CaloricMaps = _

  def footprintCalculator(): Unit = {
    //side_bar.backgroundProperty()
    loadPage[FootprintCalculator](getClass.getResource("footprintCalculator/FootprintCalculator.fxml"), homePane).initialize(this)
  }

  def healthTracker(): Unit = {
    loadPage[HealthTrackerInterface](getClass.getResource("healthTracker/HealthTrackerInterface.fxml"), homePane). initialize(this, caloricMaps)
  }

  def saveProfile(): Unit = {
    saveStates(states)
  }

  def quit(): Unit = {
    homePane.getScene.getWindow.asInstanceOf[Stage].close()
  }

  def updateFootPrint(footPrintState: FootPrintState): Unit = states = states.copy(footPrintState = footPrintState)
  def updateHealthTracker(healthTracker: HealthTracker): Unit = states = states.copy(healthTracker = healthTracker)

  def getFootPrint: FootPrintState = states.footPrintState
  def getHealthTracker: HealthTracker = states.healthTracker

  def initialize(states: States): Unit = {
    this.states = states
    val int = converter(s => s.toInt) _
    caloricMaps = CaloricMaps(loadCaloriesMap("Food.txt", int), loadCaloriesMap("Drinks.txt", int), loadCaloriesMap("Exercises.txt", s => s.toDouble))
    //label.getScene.getRoot.getStylesheets.add(getClass.getResource("Style.css").toString)
  }

  private def converter[A](func: String => A)(value: String): A = func(value)

}
