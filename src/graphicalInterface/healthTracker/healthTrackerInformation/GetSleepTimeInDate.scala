package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label}
import main.{Date}
import main.healthTracker.{CaloricMaps, SleepTracker}
import main.healthTracker.HealthInformationOps.{caloricInformation, getCalories, getSleepInDayString}

class GetSleepTimeInDate {
  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
  }

  @FXML
  var sleepDate: DatePicker = _
  @FXML
  var sleepLabel: Label =_

  def getSleepTime() ={
    val date = Date(sleepDate.getValue)
    val healthTracker = home.getHealthTracker
    val sleepInDay = SleepTracker.getSleepInDay(healthTracker.sleepTracker,date)
    sleepLabel.setText(getSleepInDayString(sleepInDay,date))
    sleepLabel.setVisible(true)
  }
}
