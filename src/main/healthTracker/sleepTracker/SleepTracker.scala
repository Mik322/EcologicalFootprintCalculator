package main.healthTracker.sleepTracker

import consoleinterface.UserChoice.AddSleep
import main.Date

import scala.annotation.tailrec

object SleepTracker {
  def getNecessarySleep(age: Int): (Int, Int) = age match {
    case a if a<=17 => (8,10)
    case a if (18 to 64).contains(a) => (7,9)
    case _ => (7, 8)
  }

  def addSleep(sleepHistory: Map[Date, Int], sleep: AddSleep): Map[Date, Int] = {
    sleepHistory get sleep.date match {
      case None => sleepHistory + (sleep.date -> sleep.hours)
      case Some(hours) => sleepHistory updated (sleep.date, sleep.hours + hours)
    }
  }

  def getSleepInDay(sleepHistory: Map[Date, Int], date: Date): Int = sleepHistory get date match {
    case None => 0
    case Some(value) => value
  }

  def getAverageSleep(sleepHistory: Map[Date, Int], startDate: Date, endDate: Date): Double = {
    val dates = sleepHistory.keys.toList.filter(p => p >= startDate && p <= endDate);
    @tailrec
    def loop(dates: List[Date], totalSleep: Int, totalDays: Int): (Int, Int) = {
      dates match {
        case ::(date, next) =>
          val sleepTime = sleepHistory(date)
          loop(next, totalSleep + sleepTime, totalDays + 1)
        case Nil =>(totalSleep, totalDays)
      }
    }
    val (totalSleep, totalDates) = loop(dates, 0, 0)
    totalSleep/totalDates
  }
}
