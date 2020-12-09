package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label}
import main.Date
import main.healthTracker.CaloricMaps
import main.healthTracker.HealthInformationOps.{caloricInformation, getCalories}

class GetCaloriesInDay {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  @FXML
  var caloriesDate: DatePicker = _
  @FXML
  var caloriesDisplay: Label =_

  def getCaloriesConsumed() ={
    val date = Date(caloriesDate.getValue)
    val healthTracker = home.getHealthTracker
    lazy val activities = healthTracker.activities.filter(a => a.date == date)
    val calories = getCalories(healthTracker, activities)
    caloriesDisplay.setText(caloricInformation(calories._1, calories._2, calories._3, date))
    caloriesDisplay.setVisible(true)
  }
}
