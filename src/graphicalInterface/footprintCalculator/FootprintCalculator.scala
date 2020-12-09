package graphicalInterface.footprintCalculator

import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.electricity.Electricity
import graphicalInterface.footprintCalculator.transportation.Transportation
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class FootprintCalculator {
  @FXML
  private var footprintDisplay: Pane = _

  @FXML
  private var footprintLabel: Label = _

  private var homePage: HomePage = _

  def transportationMenu() = {
    val loader = new FXMLLoader(getClass.getResource("transportation/Transportation.fxml"))
    footprintDisplay.getChildren.clear()
    footprintDisplay.getChildren.add(loader.load())
    loader.getController[Transportation].initialize(homePage)
  }

  def wasteMenu() = {
    val loader = new FXMLLoader(getClass.getResource("waste/Waste.fxml"))
    footprintDisplay.getChildren.clear()
    footprintDisplay.getChildren.add(loader.load())
  }

  def electricityMenu()={
    val loader = new FXMLLoader(getClass.getResource("electricity/Electricity.fxml"))
    footprintDisplay.getChildren.clear()
    footprintDisplay.getChildren.add(loader.load())
    loader.getController[Electricity].setHomePage(homePage)
  }


  def displayEarthsNeeded() ={
    footprintDisplay.getChildren.clear()
    footprintLabel.setText("displayEarthsNeeded")
  }

  def displayTotalCO2Emissions()={
    footprintDisplay.getChildren.clear()
    footprintLabel.setText("displayTotalCO2Emissions")
  }


  def initialize(homePage: HomePage): Unit = this.homePage = homePage

}
