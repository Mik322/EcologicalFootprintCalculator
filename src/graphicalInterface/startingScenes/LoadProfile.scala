package graphicalInterface.startingScenes

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{Label, TextField}
import main.fileOperations.FileOperations

class LoadProfile {
  @FXML
  private var errorLabel: Label = _
  @FXML
  private var userName: TextField = _

  def getProfile(): Unit = {
    FileOperations.loadStates(userName.getText) match {
      case None => errorLabel.setText("There is no profile with that username")
      case Some(value) =>
    }
  }

  def goBack: Unit = {
    val loader = new FXMLLoader(getClass.getResource("ProfileMenu.fxml"))
    val root: Parent = loader.load()

    userName.getScene.setRoot(root)
  }
}
