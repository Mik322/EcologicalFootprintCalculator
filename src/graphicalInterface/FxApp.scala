package graphicalInterface

import java.net.URL

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.layout.Pane

object FxApp {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[Main], args: _*)
  }

  def loadPage[A](path: URL, pane: Pane): A = {
    val loader = new FXMLLoader(path)
    pane.getChildren.clear()
    pane.getChildren.add(loader.load())
    loader.getController[A]
  }
}
