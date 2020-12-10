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

  def footprintCalculator(): Unit = {
    side_bar.setStyle("-fx-background-color: #07245A")
    loadPage[FootprintCalculator](homePane).initialize(this)
  }

  def healthTracker(): Unit = {
    side_bar.setStyle("-fx-background-color: #20AF00")
    loadPage[HealthTrackerInterface](homePane). initialize(this)
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
  }
}
