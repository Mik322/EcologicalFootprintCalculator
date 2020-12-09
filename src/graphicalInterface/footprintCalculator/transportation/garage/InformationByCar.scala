package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.transportation.garage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.{Pane, VBox}
import main.States.FootPrintState
import main.footprint.transport.Car

class InformationByCar {

  @FXML
  var elements: VBox = _
  @FXML
  var total_emissions: Label = _

  private var homePage: HomePage = _

  def initialize(homePage: HomePage) = {
    this.homePage = homePage
    addInformation(homePage.getFootPrint)
    total_emissions.setText(Car.getTotalEmissions(homePage.getFootPrint.transportTrips).toString)
  }

  def addInformation(footPrint: FootPrintState): Unit = {
    val cars = footPrint.cars
    cars match {
      case ::(head, next) => {
        addElement(head, footPrint)
        val new_cars = footPrint.copy(cars = next)
        addInformation(new_cars)
      }
      case Nil =>
    }
  }

  def addElement(car: Car, footPrint: FootPrintState) = {
    val emissions = Car.getEmissionByCar(footPrint.transportTrips, car.name)
    val kms = Car.getKmByCar(footPrint.transportTrips, car.name)
    val loader = new FXMLLoader(getClass.getResource("CarTemplate.fxml"))
    elements.getChildren.add(loader.load())
    loader.getController[garage.CarTemplate].initialize(car, emissions, kms)
  }
}
