package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.FxApp
import graphicalInterface.FxApp.loadPage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.{Pane, VBox}
import main.Date
import main.States.HealthTracker
import main.healthTracker.HealthInformationOps._
import main.healthTracker.{CaloricActivity, SleepTracker}

class HealthTrackerInformation {

  @FXML
  var healthTrackerInformationDisplay:  Pane = _

  @FXML
  var elements: VBox = _

  @FXML
  def initialize(): Unit = {
    addInformation(FxApp.getHealthTracker)
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

  def getCaloriesInDayDisplay(): Unit ={
    loadPage[HealthInfoInDay](healthTrackerInformationDisplay)
  }

  def getNetCaloriesInLastDaysDisplay(): Unit ={
    loadPage[GetCaloriesInLastDays](healthTrackerInformationDisplay)
  }

  def seeListOfCaloricActivitiesInADateRangeDisplay(): Unit ={
    loadPage[HealthInfoInADateRange](healthTrackerInformationDisplay)
  }

  
}
