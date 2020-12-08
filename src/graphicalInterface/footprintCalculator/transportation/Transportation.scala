package graphicalInterface.footprintCalculator.transportation

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class Transportation {
  @FXML
  var transportationDisplay: Pane = _

  @FXML
  var transportationLabel: Label = _

  def garageMenu()={
    val loader = new FXMLLoader(getClass.getResource("Garage.fxml"))
    transportationDisplay.getChildren.clear()
    transportationDisplay.getChildren.add(loader.load())
  }

  def addTransportationTripDisplay()={
    transportationLabel.setText("addTransportationTripDisplay")
  }

  def seeLastTransportationTripsDisplay()={
    transportationLabel.setText("seeLastTransportationTripsDisplay")
  }

  def seeTotalTransportationEmissionsDisplay()={
    transportationLabel.setText("seeTotalTransportationEmissionsDisplay")
  }
}
