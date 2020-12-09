package graphicalInterface.footprintCalculator.electricity

import graphicalInterface.HomePage
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.chart.PieChart
import javafx.scene.layout.Pane
import main.footprint.energy.{ElectricitySource, TypeOfElectricitySource}


class SeeElectricitySources {

  @FXML
  private var pane: Pane = _


  def initialize(homePage: HomePage): Unit = {
    val sources = ElectricitySource.getEnergySourcesList(homePage.getFootPrint.electricity)
    pane.getChildren.add(createChart(sources.map(source => (source._1, source._3)), "g CO2"))
    pane.getChildren.add(createChart(sources.map(source => (source._1, source._2)), "kWh"))
  }

  def createChart(sources: List[(TypeOfElectricitySource, Double)], title: String): PieChart = {
    val data: ObservableList[PieChart.Data] = FXCollections.observableArrayList()
    sources.map(source => data.add(new PieChart.Data(source._1.toString, source._2.toInt)))
    val chart = new PieChart(data)
    chart.setTitle(title)
    chart.setLegendVisible(false)

    //TODO: Fix eventHandler
    /*val caption = new Label()
    caption.setTextFill(Color.BLACK)
    pane.getChildren.add(caption)

    chart.getData.forEach(data => data.getNode.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler[MouseEvent] {
      override def handle(t: MouseEvent): Unit = {
        println(s"${data.getPieValue.toString} ${title}")
        caption.setTranslateX(t.getSceneX)
        caption.setTranslateY(t.getSceneY)
      }
    }))*/
    chart
  }

}
