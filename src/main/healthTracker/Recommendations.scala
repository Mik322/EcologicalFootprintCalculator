package main.healthTracker

import main.States.HealthTracker
import main.Date

object Recommendations {

  def cupsOfWaterToDrink(tracker: HealthTracker, date: Date): Int = HealthCalculations.getRemainingWaterNeeded(tracker, date) / 250

}
