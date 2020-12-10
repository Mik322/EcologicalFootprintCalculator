package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.FxApp
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.{DatePicker, Label}
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
  @FXML
  var startErrorLabel: Label = _
  @FXML
  var endErrorLabel: Label = _

  def applyDateRange(): Unit ={
    elements.getChildren.clear()
    startErrorLabel.setVisible(false)
    endErrorLabel.setVisible(false)
    val startD=startDate.getValue
    val endD=endDate.getValue
    if(startD==null || endD==null){
      if(startD==null){
        startErrorLabel.setText("Please choose a date!")
        startErrorLabel.setVisible(true)
      }
      if(endD==null){
        endErrorLabel.setText("Please choose a date!")
        endErrorLabel.setVisible(true)
      }
    }else{
      val start = Date(startD)
      val end = Date(endD)
      if(start>Date.today() || end>Date.today()){
        if(start>Date.today()){
          startErrorLabel.setText("You can't choose a future date!")
          startErrorLabel.setVisible(true)
        }
        if(end>Date.today()){
          endErrorLabel.setText("You can't choose a future date!")
          endErrorLabel.setVisible(true)
        }
      }else if(end < start){
        endErrorLabel.setText("The end date can't be a day before the start day!")
        endErrorLabel.setVisible(true)
      }else {
        lazy val newActivities = FxApp.getHealthTracker.activities.filter(a => a.date >= start && a.date <= end)
        val newHealthTracker = FxApp.getHealthTracker.copy(activities = newActivities)
        addInformation(newHealthTracker)
      }
    }
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
