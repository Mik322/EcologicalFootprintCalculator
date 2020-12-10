package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import main.Date
import main.healthTracker.Body

class ChangeWeight {
  @FXML
  var weightInput : TextField = _
  @FXML
  var weightErrorLabel : Label = _
  @FXML
  var changedLabel : Label = _


  def changeWeight() ={
    weightErrorLabel.setVisible(false)
    changedLabel.setVisible(false)
    try {
      val weightValue = weightInput.getText.toInt
      if (weightValue <= 0)
        weightErrorLabel.setVisible(true)
      else {
        val healthTracker = FxApp.getHealthTracker
        val newHealthTracker = Body.changeBody(BodyChange.ChangeWeight(weightValue, Date.today), healthTracker)
        FxApp.updateHealthTracker(newHealthTracker)
        changedLabel.setVisible(true)
      }
    } catch {
      case _: NumberFormatException => weightErrorLabel.setVisible(true)
    }
  }

}
