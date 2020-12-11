package graphicalInterface

import graphicalInterface.FxApp.{loadPage, saveStates}
import graphicalInterface.footprintCalculator.FootprintCalculator
import graphicalInterface.healthTracker.HealthTrackerInterface
import javafx.fxml.FXML
import javafx.scene.layout.{Pane, VBox}
import javafx.stage.Stage

class HomePage {
  @FXML
  private var homePane: Pane = _
  @FXML
  private var side_bar: VBox = _


  def footprintCalculator(): Unit = {
    side_bar.setStyle("-fx-background-color: #1a9000")
    loadPage[FootprintCalculator](homePane)
  }

  def healthTracker(): Unit = {
    side_bar.setStyle("-fx-background-color: #07245A")
    loadPage[HealthTrackerInterface](homePane)
  }

  def saveProfile(): Unit = {
    saveStates()
  }

  def quit(): Unit = {
    homePane.getScene.getWindow.asInstanceOf[Stage].close()
  }
}
