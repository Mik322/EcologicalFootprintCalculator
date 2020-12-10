package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label, TextField}
import main.Date
import main.healthTracker.CaloricMaps
import main.healthTracker.HealthInformationOps.{getCalories, getNDaysCaloriesString}

class GetCaloriesInLastDays {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  @FXML
  var daysInput: TextField = _
  @FXML
  var caloriesInfo: Label = _
  @FXML
  var daysErrorLabel: Label = _

  def getCaloriesInLastDays() = {
    daysErrorLabel.setVisible(false)
    caloriesInfo.setVisible(false)
    try {
      val healthTracker = home.getHealthTracker
      val days = daysInput.getText().toInt
      if (days <= 0)
        daysErrorLabel.setVisible(true)
      else {
        val startDate = Date.today().minusDays(days)
        lazy val activities = healthTracker.activities.filter(a => a.date >= startDate)
        val calories = getCalories(healthTracker, activities)
        caloriesInfo.setText(getNDaysCaloriesString(calories._1, calories._2, calories._3, days))
        caloriesInfo.setVisible(true)
      }
    } catch {
      case _: NumberFormatException => daysErrorLabel.setVisible(true)
    }
  }
}
