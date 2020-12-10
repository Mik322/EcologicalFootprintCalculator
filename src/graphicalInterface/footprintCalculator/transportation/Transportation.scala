package graphicalInterface.footprintCalculator.transportation

import graphicalInterface.FxApp.loadPage
import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.transportation.garage.Garage
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class Transportation {
  @FXML
  var transportationDisplay: Pane = _

  @FXML
  var transportationLabel: Label = _

  @FXML
  def initialize(): Unit = {
    loadPage[TransportInformations](transportationDisplay)
  }

  def garageMenu(): Unit ={
    loadPage[Garage](transportationDisplay)
  }

  def addTransportationTripDisplay(): Unit ={
    loadPage[TransportTrip](transportationDisplay)
  }
}
