package graphicalInterface.healthTracker.addChange.changeBodyParameters

import graphicalInterface.FxApp.loadPage
import javafx.fxml.FXML
import javafx.scene.layout.Pane

class ChangeBodyParameters {
  @FXML
  var changeBodyParametersDisplay: Pane = _

  def changeHeightDisplay(): Unit ={
    loadPage[ChangeHeight](changeBodyParametersDisplay)
  }

  def changeWeightDisplay(): Unit ={
    loadPage[ChangeWeight](changeBodyParametersDisplay)
  }

  def changeAgeDisplay(): Unit ={
    loadPage[ChangeAge](changeBodyParametersDisplay)
  }

  def changeLifestyleDisplay(): Unit ={
    loadPage[ChangeLifestyle](changeBodyParametersDisplay)
  }
}
