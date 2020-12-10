package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddWaterCup
import graphicalInterface.{FxApp, HomePage}
import main.Date
import main.healthTracker.CaloricActivity.addCaloricActivityToState
import main.healthTracker.CaloricMaps

class AddCupOfWater {
  private var home: HomePage = _

  def initialize(home: HomePage): Unit = {
    this.home = home
  }

  def addCupOfWater(): Unit ={
    val water = AddWaterCup(Date.today())
    val healthTracker = home.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(water,healthTracker,FxApp.caloricMaps)
    home.updateHealthTracker(newHealthTracker)
  }
}
