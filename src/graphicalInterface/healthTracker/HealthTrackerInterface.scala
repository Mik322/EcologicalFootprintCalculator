package graphicalInterface.healthTracker

import graphicalInterface.FxApp
import graphicalInterface.FxApp.loadPage
import graphicalInterface.healthTracker.addChange.AddChange
import graphicalInterface.healthTracker.healthTrackerInformation.HealthTrackerInformation
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import main.Date
import main.healthTracker.{CaloricActivity, SleepTracker}
import main.healthTracker.HealthInformationOps.{getBodyParametersString, getCupsOfWaterToDrinkString, getNecessarySleepString, getWeightHistoric, getWeightTrack}

class HealthTrackerInterface {
  @FXML
  private var healthTrackerDisplay: Pane = _
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
  @FXML
  def initialize(): Unit = {
    val healthTracker = FxApp.getHealthTracker
    val waterNeeds = CaloricActivity.cupsOfWaterToDrinkAndDrank(healthTracker, Date.today())
    waterNeedsLabel.setText(getCupsOfWaterToDrinkString(waterNeeds._1,waterNeeds._2))
    weightEvolutionLabel.setText(getWeightHistoric(healthTracker.weightHistory.sortBy(_._2), ""))
    goalTracking.setText(getWeightTrack(healthTracker.weightHistory.sortBy(_._2), healthTracker.goal))
    val necessarySleep = SleepTracker.getNecessarySleep(healthTracker.body.age)
    necessarySleepLabel.setText(getNecessarySleepString(necessarySleep))
    bodyParameters.setText(getBodyParametersString(healthTracker.body))
  }
  
  def addChangeMenu(): Unit ={
    loadPage[AddChange](healthTrackerDisplay)
  }

  def healthTrackerInformationMenu(): Unit ={
    loadPage[HealthTrackerInformation](healthTrackerDisplay)
  }
}
