package graphicalInterface

import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class Main extends Application{
  override def start(stage: Stage): Unit = {
    stage.setTitle("App")

    val loader = new FXMLLoader(getClass.getResource("Questionary.fxml"))
    val root: Parent = loader.load()

    val scene = new Scene(root)
    stage.setScene(scene)
    stage.show()
  }
}
