package consoleinterface.caloriescouter.options

import consoleinterface.UserChoice
import main.Date

trait CaloricInformation extends UserChoice

object CaloricInformation {
  case object GetListCaloricActivities extends CaloricInformation
  case class GetCaloriesInDay(date: Date) extends CaloricInformation
  case class GetLastDaysCalories(days: Int) extends CaloricInformation
  case class GetListCaloricActivitiesInDays(startDate: Date,endDate: Date) extends CaloricInformation
  case object GetGoalInformation extends CaloricInformation
  case object GetWeightHistory  extends CaloricInformation
  case object GetWeightTrack extends CaloricInformation
  case class GetWaterNeeds(date: Date) extends CaloricInformation
  case object GetNecessarySleep extends CaloricInformation
}
