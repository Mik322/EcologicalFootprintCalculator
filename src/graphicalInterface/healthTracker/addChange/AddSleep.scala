package graphicalInterface.healthTracker.addChange

import consoleinterface.UserChoice.AddSleep
import graphicalInterface.{FxApp, HomePage}
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, TextField}
import main.Date
import main.healthTracker.SleepTracker.addSleep

class AddSleep {
  @FXML
  var sleepTime: TextField = _

  @FXML
  var sleepDate: DatePicker = _

  def addSleepTrack(): Unit = {
    val sleep = AddSleep(sleepTime.getText().toInt, Date(sleepDate.getValue))
    val healthTracker = FxApp.getHealthTracker
    val newSleepTracker = addSleep(healthTracker.sleepTracker, sleep)
    val newHealthTracker = healthTracker.copy(sleepTracker = newSleepTracker)
    FxApp.updateHealthTracker(newHealthTracker)

  }
}
