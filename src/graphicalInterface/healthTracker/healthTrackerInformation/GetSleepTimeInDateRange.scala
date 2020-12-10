package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label}
import main.Date
import main.healthTracker.HealthInformationOps.getAverageSleepInDaysString
import main.healthTracker.SleepTracker

class GetSleepTimeInDateRange {

  @FXML
  var startDate: DatePicker = _
  @FXML
  var endDate: DatePicker = _
  @FXML
  var sleepTimeLabel: Label = _

  def getSleepTime(): Unit ={
    val start = Date(startDate.getValue)
    val end = Date(endDate.getValue)
    val healthTracker = FxApp.getHealthTracker
    val averageSleepInDays = SleepTracker.getAverageSleep(healthTracker.sleepTracker,start,end)
    sleepTimeLabel.setText(getAverageSleepInDaysString(averageSleepInDays,start,end))
    sleepTimeLabel.setVisible(true)
  }
}
