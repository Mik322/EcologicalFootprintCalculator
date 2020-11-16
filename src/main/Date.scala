package main

case class Date(day: Int, month: Int, year: Int)

object Date {

  val monthDays = List(31,29,31,30,31,30,31,31,30,31,30,31)

  def newDate(day: Int, month: Int, year: Int): Option[Date] = {
    if (month > 0 && month < 13 && day > 0 && day <= monthDays(month)) Some(Date(day, month, year))
    else None
  }
}
