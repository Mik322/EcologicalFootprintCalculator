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
  var sleepLabel: Label = _
  @FXML
  var dateErrorLabel: Label = _

  def getSleepTime() = {
    sleepLabel.setVisible(false)
    dateErrorLabel.setVisible(false)
    val dateValue = sleepDate.getValue
    if (dateValue == null) {
      dateErrorLabel.setText("Please choose a date!")
      dateErrorLabel.setVisible(true)
    } else {
      val date = Date(dateValue)
      if (date > Date.today()) {
        dateErrorLabel.setText("You can't choose a future date!")
        dateErrorLabel.setVisible(true)
      }
      else {
        val healthTracker = FxApp.getHealthTracker
        val sleepInDay = SleepTracker.getSleepInDay(healthTracker.sleepTracker, date)
        sleepLabel.setText(getSleepInDayString(sleepInDay, date))
        sleepLabel.setVisible(true)
      }
    }
  }
}
