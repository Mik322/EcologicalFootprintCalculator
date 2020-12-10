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

  private var homePage: HomePage = _

  def initialize(homePage: HomePage): Unit = {
    this.homePage = homePage
    loadPage[TransportInformations](transportationDisplay).initialize(homePage)
  }

  def garageMenu(): Unit ={
    loadPage[Garage](transportationDisplay).initialize(homePage)
  }

  def addTransportationTripDisplay(): Unit ={
    loadPage[TransportTrip](transportationDisplay).initialize(homePage)
  }
}
