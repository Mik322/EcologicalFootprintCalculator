package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.FxApp.loadPage
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import main.Date
import main.healthTracker.HealthInformationOps._
import main.healthTracker.{CaloricActivity, SleepTracker}

class HealthTrackerInformation {

  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
    val healthTracker = home.getHealthTracker
    val waterNeeds = CaloricActivity.cupsOfWaterToDrinkAndDrank(healthTracker, Date.today)
    waterNeedsLabel.setText(getCupsOfWaterToDrinkString(waterNeeds._1,waterNeeds._2))
    weightEvolutionLabel.setText(getWeightHistoric(healthTracker.weightHistory.sortBy(_._2), ""))
    goalTracking.setText(getWeightTrack(healthTracker.weightHistory.sortBy(_._2), healthTracker.goal))
    val necessarySleep = SleepTracker.getNecessarySleep(healthTracker.body.age)
    necessarySleepLabel.setText(getNecessarySleepString(necessarySleep))
    bodyParameters.setText(getBodyParametersString(healthTracker.body))
  }

  @FXML
  var healthTrackerInformationDisplay:  Pane = _


  @FXML
  var waterNeedsLabel : Label = _
  @FXML
  var weightEvolutionLabel : Label = _
  @FXML
  var goalTracking : Label = _
  @FXML
  var necessarySleepLabel : Label = _
  @FXML
  var bodyParameters : Label = _

  def getCaloriesInDayDisplay(): Unit ={
    loadPage[GetCaloriesInDay](healthTrackerInformationDisplay).initialize(home)
  }

  def getListOfCaloricActivitiesDisplay(): Unit ={
    loadPage[GetListOfCaloricActivities](healthTrackerInformationDisplay).initialize(home)
  }

  def getNetCaloriesInLastDaysDisplay(): Unit ={
    loadPage[GetCaloriesInLastDays](healthTrackerInformationDisplay).initialize(home)
  }

  def seeListOfCaloricActivitiesInADateRangeDisplay(): Unit ={
    loadPage[GetListOfCaloricActivitiesInADateRange](healthTrackerInformationDisplay).initialize(home)
  }

  def getSleepTimeInDateDisplay(): Unit ={
    loadPage[GetSleepTimeInDate](healthTrackerInformationDisplay).initialize(home)
  }

  def getAverageSleepInADateRangeDisplay(): Unit ={
    loadPage[GetSleepTimeInDateRange](healthTrackerInformationDisplay).initialize(home)
  }
  
}
