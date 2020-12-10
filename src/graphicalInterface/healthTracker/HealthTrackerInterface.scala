package graphicalInterface.healthTracker

import graphicalInterface.FxApp.loadPage
import graphicalInterface.healthTracker.addChange.AddChange
import graphicalInterface.healthTracker.healthTrackerInformation.HealthTrackerInformation
import javafx.fxml.FXML
import javafx.scene.layout.Pane

class HealthTrackerInterface {
  @FXML
  private var healthTrackerDisplay: Pane = _

  def addChangeMenu(): Unit ={
    loadPage[AddChange](healthTrackerDisplay)
  }

  def healthTrackerInformationMenu(): Unit ={
    loadPage[HealthTrackerInformation](healthTrackerDisplay)
  }
}
