package graphicalInterface.footprintCalculator.transportation.garage

import graphicalInterface.FxApp
import graphicalInterface.footprintCalculator.transportation.Template
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import main.States.FootPrintState
import main.footprint.transport.Car

class InformationByCar {

  @FXML
  var elements: VBox = _
  @FXML
  var total_emissions: Label = _

  @FXML
  def initialize(): Unit = {
    addInformation(FxApp.getFootPrint)
    total_emissions.setText(Car.getTotalEmissions(FxApp.getFootPrint.transportTrips).toString + " g CO2")
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
    val loader = new FXMLLoader(getClass.getResource("../Template.fxml"))
    elements.getChildren.add(loader.load())
    loader.getController[Template].initialize(car.name, emissions.toString, kms.toInt.toString)
  }
}
