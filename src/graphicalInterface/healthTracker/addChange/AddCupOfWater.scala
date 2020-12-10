package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddWaterCup
import graphicalInterface.FxApp
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState

class AddCupOfWater {
  def addCupOfWater(): Unit ={
    val water = AddWaterCup(Date.today())
    val healthTracker = FxApp.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(water,healthTracker,FxApp.caloricMaps)
    FxApp.updateHealthTracker(newHealthTracker)
  }
}
