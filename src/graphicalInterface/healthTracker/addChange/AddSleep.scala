package graphicalInterface.healthTracker.addChange

import java.text.DateFormat

import consoleinterface.UserChoice.AddSleep
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, TextField}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState
import main.healthTracker.CaloricMaps
import main.healthTracker.SleepTracker.addSleep

class AddSleep {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage,caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  @FXML
  var sleepTime: TextField = _

  @FXML
  var sleepDate: DatePicker = _

  def addSleepTrack() ={
    val sleep = AddSleep(sleepTime.getText().toInt,Date(sleepDate.getValue))
    val healthTracker=home.getHealthTracker
    val newSleepTracker = addSleep(healthTracker.sleepTracker,sleep)
    val newHealthTracker = healthTracker.copy(sleepTracker = newSleepTracker)
    home.updateHealthTracker(newHealthTracker)

  }
}
