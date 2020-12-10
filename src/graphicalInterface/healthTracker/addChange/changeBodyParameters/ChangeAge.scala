package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import main.healthTracker.Body

class ChangeAge {
  @FXML
  var ageInput: TextField = _
  @FXML
  var ageErrorLabel: Label = _
  @FXML
  var changedLabel: Label = _

  def changeAge() = {
    ageErrorLabel.setVisible(false)
    changedLabel.setVisible(false)
    try {
      val ageValue = ageInput.getText.toInt
      if (ageValue < 14 || ageValue > 100)
        ageErrorLabel.setVisible(true)
      else {
        val healthTracker = FxApp.getHealthTracker
        val newHealthTracker = Body.changeBody(BodyChange.ChangeAge(ageValue), healthTracker)
        FxApp.updateHealthTracker(newHealthTracker)
        changedLabel.setVisible(true)
      }
    }
    catch {
      case _: NumberFormatException => ageErrorLabel.setVisible(true)
    }
  }

}

