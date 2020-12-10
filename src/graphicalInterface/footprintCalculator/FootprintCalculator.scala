package graphicalInterface.footprintCalculator

import graphicalInterface.FxApp
import graphicalInterface.FxApp.loadPage
import graphicalInterface.footprintCalculator.electricity.ElectricityMenu
import graphicalInterface.footprintCalculator.transportation.Transportation
import graphicalInterface.footprintCalculator.waste.AddWaste
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import main.footprint.transport.TransportTrip
import main.footprint.waste.Waste
import main.footprint.{FootPrintOps, energy}

class FootprintCalculator {
  @FXML
  private var footprintDisplay: Pane = _
  @FXML
  private var waste: Label = _
  @FXML
  private var transport: Label = _
  @FXML
  private var electricity: Label = _
  @FXML
  private var total: Label = _
  @FXML
  private var earths: Label = _

  @FXML
  def initialize(): Unit = {
    setEmissions()
    setEarths()
  }

  def transportationMenu(): Unit = {
    loadPage[Transportation](footprintDisplay)
  }

  def wasteMenu(): Unit = {
    loadPage[AddWaste](footprintDisplay)
  }

  def electricityMenu(): Unit={
    loadPage[ElectricityMenu](footprintDisplay)
  }

  private def setElectricityEmissions(): Unit = {
    val emissions = energy.Electricity.getElectricityEmissions(FxApp.getFootPrint.electricity).toInt
    electricity.setText(s"${emissions}g")
  }

  private def setTransportEmissions(): Unit = {
    val emissions = TransportTrip.getTotalEmissions(FxApp.getFootPrint.transportTrips).toInt
    transport.setText(s"${emissions}g")
  }

  private def setWasteEmissions(): Unit = {
    val emissions = Waste.getTotalEmissions(FxApp.getFootPrint.waste)
    waste.setText(s"${emissions}g")
  }

  private def setTotalEmissions(): Unit = {
    val emissions = FootPrintOps.getTotalEmissions(FxApp.getFootPrint)
    total.setText(s"${emissions}g")
  }

  private def setEmissions(): Unit = {
    setElectricityEmissions()
    setTotalEmissions()
    setTransportEmissions()
    setWasteEmissions()
  }

  private def setEarths(): Unit = {
    val numEarths = FootPrintOps.getNumEarths(FxApp.getFootPrint)
    earths.setText(numEarths.toString)
  }
}
