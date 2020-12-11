package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label}
import main.Date
import main.healthTracker.HealthInformationOps.{caloricInformation, getCalories, getSleepInDayString}
import main.healthTracker.SleepTracker

class HealthInfoInDay {
  @FXML
  var caloriesDate: DatePicker = _
  @FXML
  var caloriesDisplay: Label = _
  @FXML
  var dateErrorLabel: Label = _
  @FXML
  var sleepDisplay: Label = _

  def getCaloriesConsumed() = {
    dateErrorLabel.setVisible(false)
    caloriesDisplay.setVisible(false)
    sleepDisplay.setVisible(false)
    val dateVal = caloriesDate.getValue
    if (dateVal == null) {
      dateErrorLabel.setText("Please choose a sleep date!")
      dateErrorLabel.setVisible(true)
    }
    else {
      val date = Date(dateVal)
      if (date > Date.today()) {
        dateErrorLabel.setText("You can't choose a future date!")
        dateErrorLabel.setVisible(true)
      }
      else {
        val healthTracker = FxApp.getHealthTracker
        lazy val activities = healthTracker.activities.filter(a => a.date == date)
        val calories = getCalories(healthTracker, activities)
        caloriesDisplay.setText(caloricInformation(calories._1, calories._2, calories._3, date))
        caloriesDisplay.setVisible(true)
        val sleepInDay = SleepTracker.getSleepInDay(healthTracker.sleepTracker, date)
        sleepDisplay.setText(getSleepInDayString(sleepInDay, date))
        sleepDisplay.setVisible(true)
      }
    }
  }
}
