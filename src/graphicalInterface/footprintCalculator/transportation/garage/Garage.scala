package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.FxApp.loadPage
import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.transportation.garage
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox

class Garage {
  @FXML
  var garageDisplay: VBox = _

  @FXML
  var garageLabel : Label = _

  private var homePage: HomePage = _

  def initialize(homePage: HomePage) = this.homePage = homePage

  def addCarDisplay()={
    loadPage[garage.AddCar](garageDisplay).initialize(homePage)
  }

  def editGarage() ={
    loadPage[garage.EditGarage](garageDisplay).initialize(homePage)
  }

  def displayInformationByCar() ={
    loadPage[garage.InformationByCar](garageDisplay).initialize(homePage)
  }

  def information() ={
    loadPage[garage.Informations](garageDisplay).initialize(homePage)
  }

}
