package graphicalInterface

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import main.States
import main.fileOperations.FileOperations.loadCaloriesMap
import main.healthTracker.CaloricMaps

class HomePage {
  @FXML
  private var label: Label = _
  @FXML
  private var homePage: Pane = _

  var states: States = _
  var caloricMaps: CaloricMaps = _

  def footprintCalculator() = {
    val loader = new FXMLLoader(getClass.getResource("FootprintCalculator.fxml"))
    setHomePane(loader.load())
  }

  def healthTracker() = {
    label.setText("HealthTracker")
  }

  def saveStates() = {
    label.setText(states.profileName)
  }

  def quit() = {
    label.setText("Quit")
  }

  def setStates(states: States) = {
    this.states = states
    val int = converter(s => s.toInt) _
    this.caloricMaps = CaloricMaps(loadCaloriesMap("Food.txt", int), loadCaloriesMap("Drinks.txt", int), loadCaloriesMap("Exercises.txt", s => s.toDouble))
  }

  private def converter[A](func: String => A)(value: String): A = func(value)

  private def setHomePane(node: Node): Unit = {
    homePage.getChildren.clear()
    homePage.getChildren.add(node)
  }

}
