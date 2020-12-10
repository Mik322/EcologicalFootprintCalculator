package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddWaterCup
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.Label
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState
import main.healthTracker.CaloricMaps

class AddCupOfWater {
  private var home: HomePage = _
  private var caloricMaps : CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }
  @FXML
  var addedLabel: Label = _

  def addCupOfWater() ={
    val water = AddWaterCup(Date.today)
    val healthTracker = home.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(water,healthTracker,caloricMaps)
    home.updateHealthTracker(newHealthTracker)
    addedLabel.setVisible(true)
  }
}
