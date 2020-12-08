package graphicalInterface.startingScenes

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.Button

class ProfileMenu {

  @FXML
  var newProfileButton, loadProfileButton: Button = _

  def newProfile: Unit = {
    val loader = new FXMLLoader(getClass.getResource("Questionary.fxml"))
    val root: Parent = loader.load()

    newProfileButton.getScene.setRoot(root)
  }

  def loadProfile: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("../HomePage.fxml"))
    val root: Parent = fxmlLoader.load()

    loadProfileButton.getScene.setRoot(root)
  }
}
