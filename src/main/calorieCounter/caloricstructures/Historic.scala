package main.calorieCounter.caloricstructures

case class Historic(food: List[CaloricAction], drink: List[CaloricAction], sport: List[CaloricAction])
case class CaloricAction(name: String, quantity: Int, caloricChange: Int)
