package main.calorieCounter.caloricstructures

object GoalType {
  trait Goal

  case object LoseALotOfWeight extends Goal
  case object LoseWeight extends Goal
  case object KeepWeight extends Goal
  case object GainWeight extends Goal
  case object GainALotOfWeight extends Goal
}
