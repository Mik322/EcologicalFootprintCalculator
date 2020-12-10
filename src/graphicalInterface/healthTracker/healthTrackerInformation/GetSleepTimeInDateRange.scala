package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label}
import main.Date
import main.healthTracker.{CaloricMaps, SleepTracker}
import main.healthTracker.HealthInformationOps.getAverageSleepInDaysString

class GetSleepTimeInDateRange {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  @FXML
  var startDate: DatePicker = _
  @FXML
  var endDate: DatePicker = _
  @FXML
  var sleepTimeLabel: Label = _
  @FXML
  var startErrorLabel: Label = _
  @FXML
  var endErrorLabel: Label = _

  def getSleepTime() = {
    startErrorLabel.setVisible(false)
    endErrorLabel.setVisible(false)
    sleepTimeLabel.setVisible(false)
    val startD = startDate.getValue
    val endD = endDate.getValue
    if (startD == null || endD == null) {
      if (startD == null) {
        startErrorLabel.setText("Please choose a date!")
        startErrorLabel.setVisible(true)
      }
      if (endD == null) {
        endErrorLabel.setText("Please choose a date!")
        endErrorLabel.setVisible(true)
      }
    }
    else {
      val start = Date(startD)
      val end = Date(endD)
      if (start > Date.today() || end > Date.today()) {
        if (start > Date.today()) {
          startErrorLabel.setText("You can't choose a future date!")
          startErrorLabel.setVisible(true)
        }
        if (end > Date.today()) {
          endErrorLabel.setText("You can't choose a future date!")
          endErrorLabel.setVisible(true)
        }
      } else if (end < start) {
        endErrorLabel.setText("The end date can't be a day before the start day!")
        endErrorLabel.setVisible(true)
      }
      else {
        val healthTracker = home.getHealthTracker
        val averageSleepInDays = SleepTracker.getAverageSleep(healthTracker.sleepTracker, start, end)
        sleepTimeLabel.setText(getAverageSleepInDaysString(averageSleepInDays, start, end))
        sleepTimeLabel.setVisible(true)
      }
    }
  }
}
