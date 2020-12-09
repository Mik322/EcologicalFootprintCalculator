package graphicalInterface.healthTracker.addChange

import consoleinterface.healthtracker.options.AddCaloricActivity.AddWaterCup
import graphicalInterface.HomePage
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

  def addCupOfWater() ={
    val water = AddWaterCup(Date.today)
    val healthTracker = home.getHealthTracker
    val newHealthTracker = addCaloricActivityToState(water,healthTracker,caloricMaps)
    home.updateHealthTracker(newHealthTracker)
  }
}
