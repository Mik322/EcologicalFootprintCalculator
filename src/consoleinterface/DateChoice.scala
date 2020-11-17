package consoleinterface

import main.Date

import scala.io.StdIn.readLine

object DateChoice {

  object Ops {
    def printOptions() = {
      println("1. Today\n2. Specific Date")
    }

    def printDateFormat() = println("Insert date as dd/mm/yyyy")

    def getSpecificDate(): Date = {
      printDateFormat()

      val pattern = "^[0-3]?[0-9](\\/)[0-1]?[0-9](\\/)[1-2][0-9]{3}$".r()
      val input = readLine()
      pattern.findFirstIn(input) match {
        case None => {
          println("Not a valid format")
          getSpecificDate()
        }
        case Some(value) => {
          val dateValues = value.split("\\/")
          Date.createDate(dateValues(0).toInt, dateValues(1).toInt, dateValues(2).toInt)
        }
      }
    }
  }


  import Ops._

  def getUserDate(): Date = {
    printOptions()

    val input = readLine()
    input match {
      case "1" => Date.today()
      case "2" => getSpecificDate()
      case _ => getUserDate()
    }
  }
}
