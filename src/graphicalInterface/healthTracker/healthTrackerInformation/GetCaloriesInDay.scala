package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label}
import main.Date
import main.healthTracker.HealthInformationOps.{caloricInformation, getCalories}

class GetCaloriesInDay {
  @FXML
  var caloriesDate: DatePicker = _
  @FXML
  var caloriesDisplay: Label =_

  def getCaloriesConsumed(): Unit ={
    val date = Date(caloriesDate.getValue)
    val healthTracker = FxApp.getHealthTracker
    lazy val activities = healthTracker.activities.filter(a => a.date == date)
    val calories = getCalories(healthTracker, activities)
    caloriesDisplay.setText(caloricInformation(calories._1, calories._2, calories._3, date))
    caloriesDisplay.setVisible(true)
  }
}
