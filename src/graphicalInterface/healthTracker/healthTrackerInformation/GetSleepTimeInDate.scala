package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label}
import main.Date
import main.healthTracker.HealthInformationOps.getSleepInDayString
import main.healthTracker.SleepTracker

class GetSleepTimeInDate {
  @FXML
  var sleepDate: DatePicker = _
  @FXML
  var sleepLabel: Label =_

  def getSleepTime(): Unit ={
    val date = Date(sleepDate.getValue)
    val healthTracker = FxApp.getHealthTracker
    val sleepInDay = SleepTracker.getSleepInDay(healthTracker.sleepTracker,date)
    sleepLabel.setText(getSleepInDayString(sleepInDay,date))
    sleepLabel.setVisible(true)
  }
}
