package graphicalInterface

import java.net.URL

import scala.reflect.runtime.universe.{TypeTag, typeOf}
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.layout.Pane
import main.States
import main.States.{FootPrintState, HealthTracker}
import main.fileOperations.FileOperations
import main.fileOperations.FileOperations.loadCaloriesMap
import main.healthTracker.CaloricMaps

object FxApp {


  val int = converter(s => s.toInt) _
  val caloricMaps = CaloricMaps(loadCaloriesMap("Food.txt", int), loadCaloriesMap("Drinks.txt", int), loadCaloriesMap("Exercises.txt", s => s.toDouble))

  var states: States = _

  def setStates(states: States): Unit = this.states = states
  def getStates: States = states

  def updateFootPrint(footPrintState: FootPrintState): Unit = states = states.copy(footPrintState = footPrintState)
  def updateHealthTracker(healthTracker: HealthTracker): Unit = states = states.copy(healthTracker = healthTracker)

  def getFootPrint: FootPrintState = states.footPrintState
  def getHealthTracker: HealthTracker = states.healthTracker

  def saveStates(): Unit = FileOperations.saveStates(states)

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[Main], args: _*)
  }
  private def converter[A](func: String => A)(value: String): A = func(value)

  def loadPage[A: TypeTag](pane: Pane): A = {
    val loader = new FXMLLoader(getFxmlPath[A])
    pane.getChildren.clear()
    pane.getChildren.add(loader.load())
    loader.getController[A]
  }

  private def getFxmlPath[A: TypeTag]: URL = {
    val fxmlPath = typeOf[A].typeSymbol.fullName.toString.split("\\.").tail.mkString("/").concat(".fxml")
    val projectPath = getClass.getResource("").toString
    new URL(projectPath + fxmlPath)
  }
}
