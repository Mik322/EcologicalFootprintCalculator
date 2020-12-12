package graphicalInterface.footprintCalculator.electricity

import consoleinterface.UserChoice.SetElectricitySources
import graphicalInterface.FxApp
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import javafx.scene.paint.Color
import main.footprint.energy.TypeOfElectricitySource._
import main.footprint.energy.{Electricity, ElectricitySource, TypeOfElectricitySource}

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
    val fields = getFields
    setListener(fields, changeListener)
    setCurrentSources(FxApp.getFootPrint.electricity.sources)
  }

  private def getFields: List[TextField] = List(gas, oil, coal, biomass, hydro, solar, wind, nuclear)

  @tailrec
  private def setListener(list: List[TextField], listener: ChangeListener[String]): Unit = list match {
    case ::(head, next) => head.textProperty().addListener(listener)
      setListener(next, listener)
    case Nil =>
  }

  @scala.annotation.tailrec
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

  private def setFieldValue(field: TextField, value: Double): Unit = field.setText(roundTwoDecimal(value*100).toString)

  private def roundTwoDecimal(num: Double): Double = (num * 100).round / 100

  def getTotalPercentage: Double = {
    val list = getValuesPerSource
    list.foldRight(0.0)((source, total) => total + source._2)
  }

  def getValue(field: TextField): Double = {
    try {
      field.getText.toDouble
    } catch {
      case _: NumberFormatException => 0
    }
  }

  def getValuesPerSource: List[(TypeOfElectricitySource, Double)] = {
    List((Gas, getValue(gas)), (Oil, getValue(oil)), (Coal, getValue(coal)), (Biomass, getValue(biomass)), (Hydro, getValue(hydro)), (Solar, getValue(solar)), (Wind, getValue(wind)), (Nuclear, getValue(nuclear)))
  }

  def setElectricitySources(): Unit = {
    if (!(areFieldsValid && validateAllPercentage)) return

    val sources = getValuesPerSource
      .filter(source => source._2 > 0)
      .map(source => (source._1, source._2.toDouble / 100))

    val footPrint = FxApp.getFootPrint
    val newElectricity = Electricity.setElectricitySources(footPrint.electricity, SetElectricitySources(sources))
    FxApp.updateFootPrint(footPrint.copy(electricity = newElectricity))

    infoLabel.setText("Updated")
    infoLabel.setTextFill(Color.BLACK)
  }

  private def areFieldsValid: Boolean = {
    @tailrec
    def validateFields(fields: List[TextField]): Boolean = fields match {
      case ::(head, next) =>
        if ("^[0-9]*\\.?[0-9]{0,2}".r.matches(head.getText)) validateFields(next)
        else false
      case Nil => true
    }

    if (!validateFields(getFields)) {
      infoLabel.setText("Only numbers with up to two decimal points are valid")
      infoLabel.setTextFill(Color.RED)
      false
    } else true
  }

  private def validateAllPercentage: Boolean = {
    if (getTotalPercentage == 100) return true

    infoLabel.setText("You dont have 100% of your electricity set")
    infoLabel.setTextFill(Color.RED)
    false
  }

}
