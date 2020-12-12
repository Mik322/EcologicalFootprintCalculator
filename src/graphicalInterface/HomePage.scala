package graphicalInterface

import graphicalInterface.FxApp.{loadPage, saveStates}
import graphicalInterface.footprintCalculator.FootprintCalculator
import graphicalInterface.healthTracker.HealthTrackerInterface
import javafx.fxml.FXML
import javafx.scene.layout.{Pane, VBox}
import javafx.stage.Stage
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonBar.ButtonData
import javafx.scene.control.ButtonType

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
    val alert = new Alert(AlertType.INFORMATION)
    alert.setTitle("Saved profile")
    alert.setHeaderText(null)
    alert.setContentText("Your profile has been saved with success!")
    alert.showAndWait()
  }

  def quit(): Unit = {
    val alert = new Alert(AlertType.CONFIRMATION)
    alert.setTitle("Quit")
    alert.setHeaderText(null)
    alert.setContentText("Are you sure you want to quit?")
    val quit = new ButtonType("Quit")
    val cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE)
    alert.getButtonTypes.setAll(quit, cancel)
    val result = alert.showAndWait
    if (result.get eq quit) {
      homePane.getScene.getWindow.asInstanceOf[Stage].close()
    }
  }
}
