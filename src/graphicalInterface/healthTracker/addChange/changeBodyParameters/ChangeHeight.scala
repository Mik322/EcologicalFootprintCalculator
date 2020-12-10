package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import main.healthTracker.Body

class ChangeHeight {
  @FXML
  var heightInput: TextField = _
  @FXML
  var heightErrorLabel: Label = _
  @FXML
  var changedLabel: Label = _

  def changeHeight() = {
    heightErrorLabel.setVisible(false)
    changedLabel.setVisible(false)
    try {
      val heightValue = heightInput.getText.toInt
      if (heightValue <= 0)
        heightErrorLabel.setVisible(true)
      else {
        val healthTracker = FxApp.getHealthTracker
        val newHealthTracker = Body.changeBody(BodyChange.ChangeHeight(heightInput.getText.toInt), healthTracker)
        FxApp.updateHealthTracker(newHealthTracker)
        changedLabel.setVisible(true)
      }
    } catch {
      case _: NumberFormatException => heightErrorLabel.setVisible(true)
    }
  }
}
