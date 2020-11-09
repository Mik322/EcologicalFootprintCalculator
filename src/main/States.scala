package main

import caloriecounter.body.Body

case class FootPrintState(carbonFootPrint: Int)
case class CalorieCounter(caloriesConsumed: Int, caloriesBurned: Int, body: Option[Body])

