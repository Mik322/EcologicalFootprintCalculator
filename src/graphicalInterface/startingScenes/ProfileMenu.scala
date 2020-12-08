package graphicalInterface.startingScenes

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.stage.Stage

class ProfileMenu {

  @FXML
  var newProfileButton, loadProfileButton: Button = _

  def newProfile: Unit = {newProfileButton.getScene.getWindow.asInstanceOf[Stage].close()}

  def loadProfile: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("../HomePage.fxml"))
    val root: Parent = fxmlLoader.load()

    loadProfileButton.getScene.setRoot(root)
  }
}
