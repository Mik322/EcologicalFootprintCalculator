package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.TextField
import main.Date
import main.healthTracker.Body

class ChangeWeight {
  @FXML
  var weightInput : TextField = _

  def changeWeight(): Unit ={
    val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = Body.changeBody(BodyChange.ChangeWeight(weightInput.getText.toInt, Date.today()),healthTracker)
    FxApp.updateHealthTracker(newHealthTracker)
  }

}
