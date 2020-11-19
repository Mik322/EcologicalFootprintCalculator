package consoleinterface.caloriescouter

import consoleinterface.CaloricInformation
import main.Date

object CaloricInformation {
  case object GetListCaloricActivities extends CaloricInformation
  case class GetCaloriesInDay(date: Date) extends CaloricInformation
  case class GetLastDaysCalories(days: Int) extends CaloricInformation
  case class GetListCaloricActivitiesInDays(startDate: Date,endDate: Date) extends CaloricInformation
  case object GetGoalInformation extends CaloricInformation
  case class GetWaterNeeds(date: Date) extends CaloricInformation
}
