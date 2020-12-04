package main.healthTracker.sleepTracker

import consoleinterface.AddSleep
import main.Date

object SleepTracker {
  def getNecessarySleep(age: Int): (Int, Int) = age match {
    case a if (a<=17) => (8,10)
    case a if ((18 to 64).contains(a)) => (7,9)
    case _ => (7, 8)
  }

  def addSleep(sleepHistory: Map[Date, Int], sleep: AddSleep): Map[Date, Int] = {
    sleepHistory get sleep.date match {
      case None => sleepHistory + (sleep.date -> sleep.hours)
      case Some(hours) => sleepHistory updated (sleep.date, sleep.hours + hours)
    }
  }
}
