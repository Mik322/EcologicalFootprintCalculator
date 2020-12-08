package graphicalInterface

import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class OpeningScene {

  var stage: Stage = _

  def newProfile: Unit = {stage.close()}

  def loadProfile: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("Login.fxml"))
    val root: Parent = fxmlLoader.load()

    stage.setScene(new Scene(root))
  }

  def setStage(stage: Stage): Unit = this.stage = stage
}
