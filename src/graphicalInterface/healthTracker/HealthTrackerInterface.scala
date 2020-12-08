package graphicalInterface.healthTracker

import graphicalInterface.HomePage
import main.healthTracker.CaloricMaps

class HealthTrackerInterface {

  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }
}
