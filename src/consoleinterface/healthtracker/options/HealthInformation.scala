package consoleinterface.healthtracker.options

import consoleinterface.UserChoice
import main.Date

trait HealthInformation extends UserChoice

object HealthInformation {
  case object GetListCaloricActivities extends HealthInformation
  case class GetCaloriesInDay(date: Date) extends HealthInformation
  case class GetLastDaysCalories(days: Int) extends HealthInformation
  case class GetListCaloricActivitiesInDays(startDate: Date,endDate: Date) extends HealthInformation
  case object GetGoalInformation extends HealthInformation
  case object GetWeightHistory  extends HealthInformation
  case object GetWeightTrack extends HealthInformation
  case class GetWaterNeeds(date: Date) extends HealthInformation
  case object GetNecessarySleep extends HealthInformation
  case class GetSleepInDay(date: Date) extends HealthInformation
  case class GetAverageSleepInDays(date1: Date,date2:Date) extends HealthInformation
}
