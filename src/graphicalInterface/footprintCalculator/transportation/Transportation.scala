package graphicalInterface.footprintCalculator.transportation

import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.transportation.garage.Garage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class Transportation {
  @FXML
  var transportationDisplay: Pane = _

  @FXML
  var transportationLabel: Label = _

  private var homePage: HomePage = _

  def initialize(homePage: HomePage) = {
    this.homePage = homePage
    val loader = new FXMLLoader(getClass.getResource("TransportInformations.fxml"))
    transportationDisplay.getChildren.clear()
    transportationDisplay.getChildren.add(loader.load())
    loader.getController[TransportInformations].initialize(homePage)
  }

  def garageMenu()={
    val loader = new FXMLLoader(getClass.getResource("garage/Garage.fxml"))
    transportationDisplay.getChildren.clear()
    transportationDisplay.getChildren.add(loader.load())
    loader.getController[Garage].initialize(homePage)
  }

  def addTransportationTripDisplay()={
    val loader = new FXMLLoader(getClass.getResource("TransportTrip.fxml"))
    transportationDisplay.getChildren.clear()
    transportationDisplay.getChildren.add(loader.load())
    loader.getController[TransportTrip].initialize(homePage)
  }
}
