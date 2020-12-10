package graphicalInterface.healthTracker.addChange

import consoleinterface.UserChoice.AddSleep
import graphicalInterface.{FxApp, HomePage}
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label, TextField}
import main.Date
import main.healthTracker.SleepTracker.addSleep

class AddSleep {
  @FXML
  var sleepTime: TextField = _
  @FXML
  var sleepDate: DatePicker = _
  @FXML
  var timeErrorLabel: Label = _
  @FXML
  var dateErrorLabel: Label = _
  @FXML
  var addedLabel: Label = _

  def addSleepTrack() = {
    timeErrorLabel.setVisible(false)
    dateErrorLabel.setVisible(false)
    addedLabel.setVisible(false)
    try {
      val sleepT = sleepTime.getText().toInt
      val sleepD = sleepDate.getValue
      if (sleepT <= 0)
        timeErrorLabel.setVisible(true)
      else if (sleepD == null) {
        dateErrorLabel.setText("Please choose a sleep date!")
        dateErrorLabel.setVisible(true)
      }
      else {
        val sleepDay = Date(sleepDate.getValue)
        if (sleepDay > Date.today()) {
          dateErrorLabel.setText("You can't choose a future date!")
          dateErrorLabel.setVisible(true)
        }
        else {
          val sleep = AddSleep(sleepT, sleepDay)
          val healthTracker = FxApp.getHealthTracker
          val newSleepTracker = addSleep(healthTracker.sleepTracker, sleep)
          val newHealthTracker = healthTracker.copy(sleepTracker = newSleepTracker)
          FxApp.updateHealthTracker(newHealthTracker)
          addedLabel.setVisible(true)
        }
      }
    } catch {
      case _: NumberFormatException => timeErrorLabel.setVisible(true)
    }
  }
}
