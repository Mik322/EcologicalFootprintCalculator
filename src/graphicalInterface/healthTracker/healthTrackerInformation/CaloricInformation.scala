package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.Label
import main.footprint.transport.Car
import main.healthTracker.CaloricActivity.ActivityType
import main.healthTracker.CaloricMaps

class CaloricInformation {
  @FXML
  var activityType: Label = _
  @FXML
  var activity: Label = _
  @FXML
  var quantityTime: Label = _
  @FXML
  var date: Label = _

  def initialize(activityType: String,activity: String, quantityTime: String, date: String): Unit = {
    this.activityType.setText(activityType)
    this.activity.setText(activity)
    this.quantityTime.setText(quantityTime)
    this.date.setText(date)
  }
}
