package graphicalInterface.healthTracker.healthTrackerInformation

import graphicalInterface.HomePage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import main.Date
import main.healthTracker.HealthInformationOps.{getBodyParametersString, getCupsOfWaterToDrinkString, getNecessarySleepString, getWeightHistoric, getWeightTrack}
import main.healthTracker.{CaloricActivity, CaloricMaps, SleepTracker}

class HealthTrackerInformation {

  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
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
    val loader = new FXMLLoader(getClass.getResource("GetCaloriesInDay.fxml"))
    healthTrackerInformationDisplay.getChildren.clear()
    healthTrackerInformationDisplay.getChildren.add(loader.load())
    loader.getController[GetCaloriesInDay].initialize(home,caloricMaps)
  }

  def getListOfCaloricActivitiesDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("GetListOfCaloricActivities.fxml"))
    healthTrackerInformationDisplay.getChildren.clear()
    healthTrackerInformationDisplay.getChildren.add(loader.load())
    loader.getController[GetListOfCaloricActivities].initialize(home,caloricMaps)
  }

  def getNetCaloriesInLastDaysDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("GetCaloriesInLastDays.fxml"))
    healthTrackerInformationDisplay.getChildren.clear()
    healthTrackerInformationDisplay.getChildren.add(loader.load())
    loader.getController[GetCaloriesInLastDays].initialize(home,caloricMaps)
  }

  def seeListOfCaloricActivitiesInADateRangeDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("GetListOfCaloricActivitiesInADateRange.fxml"))
    healthTrackerInformationDisplay.getChildren.clear()
    healthTrackerInformationDisplay.getChildren.add(loader.load())
    loader.getController[GetListOfCaloricActivitiesInADateRange].initialize(home,caloricMaps)
  }

  def getSleepTimeInDateDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("GetSleepTimeInDate.fxml"))
    healthTrackerInformationDisplay.getChildren.clear()
    healthTrackerInformationDisplay.getChildren.add(loader.load())
    loader.getController[GetSleepTimeInDate].initialize(home,caloricMaps)
  }

  def getAverageSleepInADateRangeDisplay() ={
    val loader = new FXMLLoader(getClass.getResource("GetSleepTimeInDateRange.fxml"))
    healthTrackerInformationDisplay.getChildren.clear()
    healthTrackerInformationDisplay.getChildren.add(loader.load())
    loader.getController[GetSleepTimeInDateRange].initialize(home,caloricMaps)
  }
  
}
