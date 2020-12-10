package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.FxApp
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.DatePicker
import javafx.scene.layout.VBox
import main.Date
import main.States.HealthTracker
import main.healthTracker.CaloricActivity

class GetListOfCaloricActivitiesInADateRange {
  @FXML
  var elements: VBox = _
  @FXML
  var startDate: DatePicker = _
  @FXML
  var endDate: DatePicker = _

  def applyDateRange(): Unit ={
    elements.getChildren.clear()
    val start = Date(startDate.getValue)
    val end = Date(endDate.getValue)
    lazy val newActivities = FxApp.getHealthTracker.activities.filter(a => a.date >= start && a.date <= end)
    val newHealthTracker = FxApp.getHealthTracker.copy(activities = newActivities)
    addInformation(newHealthTracker)
  }

  def addInformation(healthTracker: HealthTracker): Unit = {
    val activities = healthTracker.activities
    activities match {
      case Nil =>
      case ::(head,next) =>
        addElement(head,healthTracker)
        val new_activities=healthTracker.copy(activities=next)
        addInformation(new_activities)
    }
  }

  def addElement(activity: CaloricActivity, healthTracker: HealthTracker): Unit ={
    val activityType = activity.activityType.toString
    val name = activity.name
    val quantityTime = activity.quantity.toString
    val date = activity.date.toString
    val loader = new FXMLLoader(getClass.getResource("CaloricInformation.fxml"))
    elements.getChildren.add(loader.load())
    loader.getController[CaloricInformation].initialize(activityType,name,quantityTime,date)
  }
}
