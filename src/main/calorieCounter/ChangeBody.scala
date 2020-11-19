package main.calorieCounter

import consoleinterface.caloriescouter.options.BodyChange
import main.CalorieCounter

object ChangeBody {

  def changeBody(newParam: BodyChange, counter: CalorieCounter): CalorieCounter = newParam match {
    case BodyChange.ChangeAge(age) => counter.copy(body = counter.body.copy(age = age))

    case BodyChange.ChangeHeight(height) => counter.copy(body = counter.body.copy(height = height))

    case BodyChange.ChangeLifestyle(lifestyle) => counter.copy(body = counter.body.copy(lifestyle = lifestyle))

    case BodyChange.ChangeWeight(weight, date) =>
      val newBody = counter.body.copy(weight = weight)
      counter.copy(body = newBody, weightHistoric = counter.weightHistoric.appended((weight, date)))
  }
}
