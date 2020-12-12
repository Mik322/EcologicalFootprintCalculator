package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.FxApp
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.{Label, ScrollPane}
import javafx.scene.layout.{GridPane, VBox}
import main.Date
import main.States.HealthTracker
import main.healthTracker.HealthInformationOps.getAverageSleepInDaysString
import main.healthTracker.{CaloricActivity, SleepTracker}

class CaloricActivitiesDateRangeGrid {

  @FXML
  private var elements, container: VBox = _
  @FXML
  private var caloricActivities, sleepTime, caloricInfo: Label = _
  @FXML
  private var grid: GridPane = _
  @FXML
  private var scrollPane: ScrollPane = _

  private var startDate, endDate: Date = _

  def setDates(start: Date, end: Date): Unit = {
    this.startDate = start
    this.endDate = end

    val healthTracker = FxApp.getHealthTracker
    val averageSleepInDays = SleepTracker.getAverageSleep(healthTracker.sleepTracker, start, end)
    sleepTime.setText(getAverageSleepInDaysString(averageSleepInDays, start, end))
    sleepTime.setVisible(true)
    lazy val newActivities = healthTracker.activities.filter(a => a.date >= start && a.date <= end)
    if(newActivities.size==0){
      caloricInfo.setText(s"You don't have any caloric activities from ${start} to ${end}")
      container.getChildren.remove(grid)
      container.getChildren.remove(scrollPane)
    }else{
      val newHealthTracker = healthTracker.copy(activities = newActivities)
      addInformation(newHealthTracker)
      caloricActivities.setText(s"Your caloric activities from ${start} to ${end}")
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
