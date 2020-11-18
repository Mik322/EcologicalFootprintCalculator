package main.calorieCounter.caloricstructures

object Goal extends Enumeration {
  type Goal = Value

  val LoseALotOfWeight = Value(-1000)
  val LoseWeight = Value(-500)
  val KeepWeight = Value(0)
  val GainWeight = Value(500)
  val GainALotOfWeight = Value(1000)
}
