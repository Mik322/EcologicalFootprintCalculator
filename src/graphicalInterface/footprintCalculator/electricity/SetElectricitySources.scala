package graphicalInterface.footprintCalculator.electricity

import consoleinterface.UserChoice.SetElectricitySources
import graphicalInterface.{FxApp, HomePage}
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import javafx.scene.paint.Color
import main.footprint.energy.{Electricity, ElectricitySource, TypeOfElectricitySource}
import main.footprint.energy.TypeOfElectricitySource.{Biomass, Coal, Gas, Hydro, Nuclear, Oil, Solar, Wind}

import scala.annotation.tailrec

class SetElectricitySources {

  @FXML
  var gas: TextField = _
  @FXML
  var oil: TextField = _
  @FXML
  var coal: TextField = _
  @FXML
  var biomass: TextField = _
  @FXML
  var hydro: TextField = _
  @FXML
  var solar: TextField = _
  @FXML
  var wind: TextField = _
  @FXML
  var nuclear: TextField = _
  @FXML
  var percentageLabel: Label = _
  @FXML
  private var infoLabel: Label = _

  @FXML
  def initialize(): Unit = {
    val changeListener = new ChangeListener[String] {
      override def changed(observableValue: ObservableValue[_ <: String], t: String, t1: String): Unit = percentageLabel.setText(getTotalPercentage.toString)
    }
    val labels = List(gas, oil, coal, biomass, hydro, solar, wind, nuclear)
    setListener(labels, changeListener)
    setCurrentSources(FxApp.getFootPrint.electricity.sources)
  }
  @tailrec
  private def setListener(list: List[TextField], listener: ChangeListener[String]): Unit = list match {
    case ::(head, next) => head.textProperty().addListener(listener)
      setListener(next, listener)
    case Nil =>
  }

  private def setCurrentSources(sources: List[ElectricitySource]): Unit = sources match {
    case ::(head, next) => head.source match {
      case TypeOfElectricitySource.Gas => setFieldValue(gas, head.percentage)
      case TypeOfElectricitySource.Oil => setFieldValue(oil, head.percentage)
      case TypeOfElectricitySource.Coal => setFieldValue(coal, head.percentage)
      case TypeOfElectricitySource.Wind => setFieldValue(wind, head.percentage)
      case TypeOfElectricitySource.Hydro => setFieldValue(hydro, head.percentage)
      case TypeOfElectricitySource.Solar => setFieldValue(solar, head.percentage)
      case TypeOfElectricitySource.Biomass => setFieldValue(biomass, head.percentage)
      case TypeOfElectricitySource.Nuclear => setFieldValue(nuclear, head.percentage)
    }
      setCurrentSources(next)
    case Nil =>
  }

  private def setFieldValue(field: TextField, value: Double): Unit = field.setText((value * 100).toInt.toString)

  def getTotalPercentage: Int = {
    val list = getValuesPerSource
    list.foldRight(0)((source, total) => total + source._2)
  }

  def getValue(field: TextField): Int = {
    try {
      field.getText.toInt
    } catch {
      case _: NumberFormatException => 0
    }
  }

  def getValuesPerSource: List[(TypeOfElectricitySource, Int)] = {
    List((Gas, getValue(gas)), (Oil, getValue(oil)), (Coal, getValue(coal)), (Biomass, getValue(biomass)), (Hydro, getValue(hydro)), (Solar, getValue(solar)), (Wind, getValue(wind)), (Nuclear, getValue(nuclear)))
  }

  def setElectricitySources(): Unit = {
    if (getTotalPercentage == 100) {
      val list = getValuesPerSource
        .filter(source => source._2 > 0)
        .map(source => (source._1, source._2.toDouble/100))
      val sources = SetElectricitySources(list)
      val footPrint = FxApp.getFootPrint
      val newElectricity = Electricity.setElectricitySources(footPrint.electricity, sources)
      FxApp.updateFootPrint(footPrint.copy(electricity = newElectricity))
      infoLabel.setText("Updated")
      infoLabel.setTextFill(Color.web("#000000"))
    } else {
      infoLabel.setText("You dont have 100% of your electricity set")
      infoLabel.setTextFill(Color.web("#FF0000"))
    }
  }

}
