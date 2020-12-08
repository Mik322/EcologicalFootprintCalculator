package graphicalInterface

import javafx.fxml.FXML
import javafx.scene.control.{Button, TextField}

class Login {
  @FXML
  private var loginButton: Button = _
  @FXML
  private var userName: TextField = _

  def getUserName(): Unit = println(userName.getText)
}
