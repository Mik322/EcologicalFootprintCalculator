package main.calorieCounter.caloricstructures

object Goal extends Enumeration {
  protected case class Val(caloricChange: Int, kgChanged: Double) extends super.Val

  import scala.language.implicitConversions
  implicit def valueToGoalVal(x: Value): Val = x.asInstanceOf[Val]

  val LoseALotOfWeight = Val(-1000, -1)
  val LoseWeight = Val(-500, -0.5)
  val KeepWeight = Val(0, 0)
  val GainWeight = Val(500, 0.5)
  val GainALotOfWeight = Val(1000, 1)
}
