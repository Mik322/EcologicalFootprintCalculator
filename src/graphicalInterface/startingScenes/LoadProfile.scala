package graphicalInterface.startingScenes

import graphicalInterface.HomePage
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{Label, TextField}
import main.States
import main.fileOperations.FileOperations

class LoadProfile {
  @FXML
  private var errorLabel: Label = _
  @FXML
  private var userName: TextField = _

  def getProfile(): Unit = {
    FileOperations.loadStates(userName.getText) match {
      case None => errorLabel.setText("There is no profile with that username")
      case Some(states) => loadHomepage(states)
    }
  }

  def loadHomepage(states: States): Unit = {
    val loader = new FXMLLoader(getClass.getResource("../HomePage.fxml"))
    val root: Parent = loader.load()
    loader.getController.asInstanceOf[HomePage].setStates(states)
    userName.getScene.setRoot(root)
  }

  def goBack: Unit = {
    val loader = new FXMLLoader(getClass.getResource("ProfileMenu.fxml"))
    val root: Parent = loader.load()

    userName.getScene.setRoot(root)
  }
}
