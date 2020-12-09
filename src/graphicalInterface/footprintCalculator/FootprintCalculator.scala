package graphicalInterface.footprintCalculator

import graphicalInterface.HomePage
import graphicalInterface.footprintCalculator.transportation.Transportation
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import graphicalInterface.FxApp.loadPage
import graphicalInterface.footprintCalculator.electricity.ElectricityMenu
import graphicalInterface.footprintCalculator.waste.AddWaste
import main.footprint.{FootPrintOps, energy}
import main.footprint.transport.TransportTrip
import main.footprint.waste.Waste

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


  private var homePage: HomePage = _

  def transportationMenu(): Unit = {
    loadPage[Transportation](getClass.getResource("transportation/Transportation.fxml"), footprintDisplay).initialize(homePage)
  }

  def wasteMenu(): Unit = {
    loadPage[AddWaste](getClass.getResource("waste/AddWaste.fxml"), footprintDisplay).setHomePage(homePage)
  }

  def electricityMenu(): Unit={
    loadPage[ElectricityMenu](getClass.getResource("electricity/ElectricityMenu.fxml"), footprintDisplay).setHomePage(homePage)
  }

  private def setElectricityEmissions(): Unit = {
    val emissions = energy.Electricity.getElectricityEmissions(homePage.getFootPrint.electricity)
    electricity.setText(s"${emissions}g")
  }

  private def setTransportEmissions(): Unit = {
    val emissions = TransportTrip.getTotalEmissions(homePage.getFootPrint.transportTrips)
    transport.setText(s"${emissions}g")
  }

  private def setWasteEmissions(): Unit = {
    val emissions = Waste.getTotalEmissions(homePage.getFootPrint.waste)
    waste.setText(s"${emissions}g")
  }

  private def setTotalEmissions(): Unit = {
    val emissions = FootPrintOps.getTotalEmissions(homePage.getFootPrint)
    total.setText(s"${emissions}g")
  }

  private def setEmissions(): Unit = {
    setElectricityEmissions()
    setTotalEmissions()
    setTransportEmissions()
    setWasteEmissions()
  }

  private def setEarths(): Unit = {
    val numEarths = FootPrintOps.getNumEarths(homePage.getFootPrint)
    earths.setText(numEarths.toString)
  }

  def initialize(homePage: HomePage): Unit = {
    this.homePage = homePage
    setEmissions()
    setEarths()
  }

}
