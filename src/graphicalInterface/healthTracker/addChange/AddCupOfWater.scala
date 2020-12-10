package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddWaterCup
import graphicalInterface.FxApp
import javafx.fxml.FXML
import javafx.scene.control.Label
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState

class AddCupOfWater {
  @FXML
  var addedLabel: Label = _

  def addCupOfWater() ={
    val water = AddWaterCup(Date.today)
    val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(water,healthTracker,FxApp.caloricMaps)
    FxApp.updateHealthTracker(newHealthTracker)
    addedLabel.setVisible(true)
  }
}
