package graphicalInterface.healthTracker

import graphicalInterface.FxApp.loadPage
import graphicalInterface.HomePage
import graphicalInterface.healthTracker.addChange.AddChange
import graphicalInterface.healthTracker.healthTrackerInformation.HealthTrackerInformation
import javafx.fxml.FXML
import javafx.scene.layout.Pane
import main.healthTracker.CaloricMaps

class HealthTrackerInterface {

  private var home: HomePage = _

  @FXML
  private var healthTrackerDisplay: Pane = _

  def initialize(home: HomePage): Unit = {
    this.home = home
  }

  def addChangeMenu() ={
    loadPage[AddChange](healthTrackerDisplay).initialize(home)
  }

  def healthTrackerInformationMenu() ={
    loadPage[HealthTrackerInformation](healthTrackerDisplay).initialize(home)
  }
}
