package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.transportation.garage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.VBox
import main.States
import main.States.HealthTracker
import main.footprint.transport.Car
import main.healthTracker.{CaloricActivity, CaloricMaps}

class GetListOfCaloricActivities {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
    addInformation(home.getHealthTracker)
  }

  @FXML
  var elements: VBox = _

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

  def addElement(activity: CaloricActivity, healthTracker: HealthTracker) ={
    val activityType = activity.activityType.toString
    val name = activity.name
    val quantityTime = activity.quantity.toString
    val date = activity.date.toString
    val loader = new FXMLLoader(getClass.getResource("CaloricInformation.fxml"))
    elements.getChildren.add(loader.load())
    loader.getController[CaloricInformation].initialize(activityType,name,quantityTime,date)
  }


}
