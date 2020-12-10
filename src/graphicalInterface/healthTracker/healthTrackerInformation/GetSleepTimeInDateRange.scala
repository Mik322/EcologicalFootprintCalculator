package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label}
import main.Date
import main.healthTracker.{CaloricMaps, SleepTracker}
import main.healthTracker.HealthInformationOps.getAverageSleepInDaysString

class GetSleepTimeInDateRange {
  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
  }

  @FXML
  var startDate: DatePicker = _
  @FXML
  var endDate: DatePicker = _
  @FXML
  var sleepTimeLabel: Label = _

  def getSleepTime()  ={
    val start = Date(startDate.getValue)
    val end = Date(endDate.getValue)
    val healthTracker = home.getHealthTracker
    val averageSleepInDays = SleepTracker.getAverageSleep(healthTracker.sleepTracker,start,end)
    sleepTimeLabel.setText(getAverageSleepInDaysString(averageSleepInDays,start,end))
    sleepTimeLabel.setVisible(true)
  }
}
