package consoleinterface

import main.Date

import scala.io.StdIn.readLine

object DateChoice {

  private def printOptions(): Unit = {
    println("1. Today\n2. Specific Date")
  }

  private def printDateFormat(): Unit = println("Insert date as dd/mm/yyyy")

  private def getSpecificDate: Date = {
    printDateFormat()

    val pattern = "^[0-3]?[0-9](\\/)[0-1]?[0-9](\\/)[1-2][0-9]{3}$".r()
    val input = readLine()
    pattern.findFirstIn(input) match {
      case None =>
        println("Not a valid format")
        getSpecificDate
      case Some(value) =>
        val dateValues = value.split("\\/")
        val date = Date.createDate(dateValues(0).toInt, dateValues(1).toInt, dateValues(2).toInt)
        if (date > Date.today()) {
          println("You cannot insert a future date, please try again")
          getSpecificDate
        }
        date
    }
  }


  @scala.annotation.tailrec
  def getUserDate(): Date = {
    printOptions()

    val input = readLine()
    input match {
      case "1" => Date.today()
      case "2" => getSpecificDate
      case _ => getUserDate()
    }
  }

  def getMonth: Date = {
    println("Insert Month")
    try {
      readLine().toInt match {
        case m if (1 to 12).contains(m) => Date.createDate(1, m, getYear)
        case _ => println("Invalid number")
          getMonth
      }
    } catch {
      case _: NumberFormatException => println("Invalid number")
        getMonth
    }
  }

  private def getYear: Int = {
    println("Insert Year")
    try {
      readLine().toInt match {
        case y if (1990 to Date.today().getYear()).contains(y) => y
        case _ => println("Invalid number")
          getYear
      }
    } catch {
      case _: NumberFormatException => println("Invalid number")
        getYear
    }
  }
}
