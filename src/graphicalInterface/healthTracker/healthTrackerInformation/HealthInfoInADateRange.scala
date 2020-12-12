package graphicalInterface.healthTracker.healthTrackerInformation

import java.time.LocalDate

import graphicalInterface.FxApp.loadPage
import javafx.fxml.FXML
import javafx.scene.control.{DatePicker, Label}
import javafx.scene.layout.VBox
import main.Date

class HealthInfoInADateRange {
  @FXML
  var startDate, endDate: DatePicker = _
  @FXML
  var startErrorLabel, endErrorLabel: Label = _

  @FXML
  private var dateRangeInfo: VBox = _



  def applyDateRange(): Unit = {
    if (!areDatesValid) return

    val start = Date(startDate.getValue)
    val end = Date(endDate.getValue)

    loadPage[CaloricActivitiesDateRangeGrid](dateRangeInfo).setDates(start, end)
  }

  private def areDatesSelected(selectedStart: LocalDate, selectedEnd: LocalDate): Boolean = {
    if(selectedStart==null){
      startErrorLabel.setText("Please choose a date!")
      startErrorLabel.setVisible(true)
      return false
    }
    if(selectedEnd==null){
      endErrorLabel.setText("Please choose a date!")
      endErrorLabel.setVisible(true)
      return false
    }
    true
  }

  //noinspection DuplicatedCode
  private def areDatesFuture(start: Date, end: Date): Boolean = {
    if(start > Date.today()){
      startErrorLabel.setText("You can't choose a future date!")
      startErrorLabel.setVisible(true)
      return true
    }
    if(end > Date.today()){
      endErrorLabel.setText("You can't choose a future date!")
      endErrorLabel.setVisible(true)
      return true
    }
    false
  }

  private def areDatesValid: Boolean = {
    val selectedStart = startDate.getValue
    val selectedEnd = endDate.getValue

    if (!areDatesSelected(selectedStart, selectedEnd)) return false

    val start = Date(selectedStart)
    val end = Date(selectedEnd)
    if (areDatesFuture(start, end)) return false

    if(end < start) {
      endErrorLabel.setText("The end date can't be a day before the start day!")
      endErrorLabel.setVisible(true)
      return false
    }

    true
  }
}
