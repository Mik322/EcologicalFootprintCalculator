package consoleinterface.caloriescouter.inputs

import main.calorieCounter.Gender
import scala.io.StdIn.readLine
import main.calorieCounter._

object BodyInput {
  def genderInput():Gender={
    println("Gender:\n0.Male\n1.Female")
    val genderNum=readLine().toInt
    genderNum match{
      case 0 => Male
      case 1 => Female
    }
  }
  def lifestyleInput():Lifestyle={
    println("Lifestyle(According to your occupation):\n1. Sedentary(People who work desk jobs and engage in very little exercise or chores.)\n2. Moderated(People who do chores and go on long walks/engage in exercise at least 1 to 3 days in a week.)\n3. Active(People who move a lot during the day and workout (moderate effort) at least 3 to 5 days in a week.)\n4. Very Active(People who play sports or engage in vigorous exercise on most days.)\n5. ExtremelyActive(People who do intense workouts 6 to 7 days a week with work that demands physical activity.)")
    val lifestyleNum=readLine().toInt
    lifestyleNum match{
      case 1=> Sedentary
      case 2=> Moderated
      case 3=> Active
      case 4=> VeryActive
      case 5=> ExtremelyActive
    }
  }
}
