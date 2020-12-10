package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.TextField
import main.healthTracker.Body

class ChangeHeight {
  @FXML
  var heightInput : TextField = _

  def changeHeight(): Unit ={
    val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = Body.changeBody(BodyChange.ChangeHeight(heightInput.getText.toInt),healthTracker)
    FxApp.updateHealthTracker(newHealthTracker)
  }
}
