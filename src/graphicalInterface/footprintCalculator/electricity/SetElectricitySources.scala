package graphicalInterface.footprintCalculator.electricity

import consoleinterface.UserChoice.SetElectricitySources
import graphicalInterface.HomePage
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import javafx.scene.paint.Color
import main.footprint.energy.{Electricity, TypeOfElectricitySource}
import main.footprint.energy.TypeOfElectricitySource.{Biomass, Coal, Gas, Hydro, Nuclear, Oil, Solar, Wind}

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

  private var homePage: HomePage = _

  @FXML
  def initialize(): Unit = {
    val changeListener = new ChangeListener[String] {
      override def changed(observableValue: ObservableValue[_ <: String], t: String, t1: String): Unit = percentageLabel.setText(getTotalPercentage.toString)
    }
    gas.textProperty().addListener(changeListener)
    oil.textProperty().addListener(changeListener)
    coal.textProperty().addListener(changeListener)
    biomass.textProperty().addListener(changeListener)
    hydro.textProperty().addListener(changeListener)
    solar.textProperty().addListener(changeListener)
    wind.textProperty().addListener(changeListener)
    nuclear.textProperty().addListener(changeListener)
  }

  def setHomePage(homePage: HomePage): Unit = this.homePage = homePage

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
      val footPrint = homePage.getFootPrint
      val newElectricity = Electricity.setElectricitySources(footPrint.electricity, sources)
      homePage.updateFootPrint(footPrint.copy(electricity = newElectricity))
      infoLabel.setText("Updated")
      infoLabel.setTextFill(Color.web("#000000"))
    } else {
      infoLabel.setText("You dont have 100% of your electricity set")
      infoLabel.setTextFill(Color.web("#FF0000"))
    }
  }

}
