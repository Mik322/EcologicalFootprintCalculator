package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label, TextField}
import main.Date
import main.healthTracker.CaloricMaps
import main.healthTracker.HealthInformationOps.{getCalories, getNDaysCaloriesString}

class GetCaloriesInLastDays {
  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
  }

  @FXML
  var daysInput: TextField = _
  @FXML
  var caloriesInfo: Label =_

  def getCaloriesInLastDays() ={
    val healthTracker = home.getHealthTracker
    val days = daysInput.getText().toInt
    val startDate = Date.today().minusDays(days)
    lazy val activities = healthTracker.activities.filter(a => a.date >= startDate)
    val calories = getCalories(healthTracker, activities)
    caloriesInfo.setText(getNDaysCaloriesString(calories._1, calories._2, calories._3, days))
    caloriesInfo.setVisible(true)

  }
}
