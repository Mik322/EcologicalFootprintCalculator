package consoleinterface.caloriescouter

import consoleinterface.AddFood

import scala.io.StdIn.readLine

object CaloriesConsoleOps {
  def printList(list: List[String], index: Int): Unit = {
    list match {
      case ::(head, next) => {
        println(s"${index}.  ${head.split(':')(0)}")
        printList(next, index+1)
      }
      case Nil => {}
    }
  }

  def getFoodInput(foodList: List[String]): AddFood = {
    print("Select food number: ")
    val food = readLine().toInt
    print("Quantity (in grams): ")
    val quantity = readLine().toInt
    AddFood(foodList(food), quantity)
  }
}
