package main

import java.time.LocalDate

case class Date(localDate: LocalDate) {
  def getDay() = Date.getDay(this)

  def getMonth() = Date.getMonth(this)

  def getYear() = Date.getYear(this)

  override def equals(obj: Any): Boolean = {
    obj match {
      case date: Date => this.localDate.equals(date.localDate)
      case _ => false
    }
  }

  def minusDays(days: Int): Date = Date.minusDays(this, days)

  def <(date: Date): Boolean = this.localDate.compareTo(date.localDate) < 0

  def >(date: Date): Boolean = this.localDate.compareTo(date.localDate) > 0

  override def toString: String = s"${this.getDay()}/${this.getMonth()}/${this.getYear()}"

  def >=(date: Date) = this > date || this == date

  def <=(date: Date) = this < date || this == date

  override def hashCode(): Int = ((this.localDate.toEpochDay % 197) + this.getDay() * 31).toInt
}

object Date {
  def minusDays(date: Date, days: Int): Date = Date(date.localDate.minusDays(days.toLong))

  def createDate(day: Int, month: Int, year: Int): Date = Date(LocalDate.of(year, month, day))

  def today(): Date = Date(LocalDate.now())

  def getDay(date: Date) = date.localDate.getDayOfMonth

  def getMonth(date: Date) = date.localDate.getMonthValue

  def getYear(date: Date) = date.localDate.getYear
}
