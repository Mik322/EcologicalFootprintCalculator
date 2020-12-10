package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.TextField
import main.healthTracker.Body

class ChangeAge {
  @FXML
  var ageInput : TextField = _

  def changeAge(): Unit ={
    val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = Body.changeBody(BodyChange.ChangeAge(ageInput.getText.toInt),healthTracker)
    FxApp.updateHealthTracker(newHealthTracker)
  }
}
