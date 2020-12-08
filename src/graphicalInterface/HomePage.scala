package graphicalInterface

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.Pane

class HomePage {
  @FXML
  private var label: Label = _
  @FXML
  private var homePage: Pane = _

  def footprintCalculator() ={
    val loader = new FXMLLoader(getClass.getResource("footprintCalculator/FootprintCalculator.fxml"))
    homePage.getChildren.clear()
    homePage.getChildren.add(loader.load())
  }

  def healthTracker() ={
    label.setText("HealthTracker")
  }

  def saveStates() ={
    label.setText("SaveStates")
  }

  def quit() ={
    label.setText("Quit")
  }

}
