package graphicalInterface.startingScenes

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.image.ImageView
import javafx.scene.image.Image

class ProfileMenu {

  @FXML
  var newProfileButton, loadProfileButton: Button = _
  @FXML
  var footprint: ImageView = _
  @FXML
  def initialize() = {
    footprint.setImage(new Image("file:footprint.png"))
  }

  def newProfile: Unit = {
    val loader = new FXMLLoader(getClass.getResource("createProfile/NewProfile.fxml"))
    val root: Parent = loader.load()

    newProfileButton.getScene.setRoot(root)
  }

  def loadProfile: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("LoadProfile.fxml"))
    val root: Parent = fxmlLoader.load()

    loadProfileButton.getScene.setRoot(root)
  }
}
