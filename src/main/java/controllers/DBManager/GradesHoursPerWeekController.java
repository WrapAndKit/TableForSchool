package main.java.controllers.DBManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.java.controllers.DBController;

import java.net.URL;
import java.util.ResourceBundle;

public class GradesHoursPerWeekController implements Initializable {

    // спойлер - много говнокода

    @FXML
    private TextField min5;
    @FXML
    private TextField max5;

    @FXML
    private TextField min6;
    @FXML
    private TextField max6;

    @FXML
    private TextField min7;
    @FXML
    private TextField max7;

    @FXML
    private TextField min8;
    @FXML
    private TextField max8;

    @FXML
    private TextField min9;
    @FXML
    private TextField max9;

    @FXML
    private TextField min10;
    @FXML
    private TextField max10;

    @FXML
    private TextField min11;
    @FXML
    private TextField max11;

    private final DBController db = new DBController("src/main/sqlite/knowledge.sqlite3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.connect();

        ObservableList<String> minColumn = db.queryColumnValue("grades_hoursPerWeek", "min");

        min5.setText(minColumn.get(0));
        min6.setText(minColumn.get(1));
        min7.setText(minColumn.get(2));
        min8.setText(minColumn.get(3));
        min9.setText(minColumn.get(4));
        min10.setText(minColumn.get(5));
        min11.setText(minColumn.get(6));

        ObservableList<String> maxColumn = db.queryColumnValue("grades_hoursPerWeek", "max");

        max5.setText(maxColumn.get(0));
        max6.setText(maxColumn.get(1));
        max7.setText(maxColumn.get(2));
        max8.setText(maxColumn.get(3));
        max9.setText(maxColumn.get(4));
        max10.setText(maxColumn.get(5));
        max11.setText(maxColumn.get(6));

        db.disconnect();
    }

    public void handleOnAction() {
        db.connect();

        db.updateRow("grades_hoursPerWeek", "min", "name = 5", min5.getText());
        db.updateRow("grades_hoursPerWeek", "max", "name = 5", max5.getText());

        db.updateRow("grades_hoursPerWeek", "min", "name = 6", min6.getText());
        db.updateRow("grades_hoursPerWeek", "max", "name = 6", max6.getText());

        db.updateRow("grades_hoursPerWeek", "min", "name = 7", min7.getText());
        db.updateRow("grades_hoursPerWeek", "max", "name = 7", max7.getText());

        db.updateRow("grades_hoursPerWeek", "min", "name = 8", min8.getText());
        db.updateRow("grades_hoursPerWeek", "max", "name = 8", max8.getText());

        db.updateRow("grades_hoursPerWeek", "min", "name = 9", min9.getText());
        db.updateRow("grades_hoursPerWeek", "max", "name = 9", max9.getText());

        db.updateRow("grades_hoursPerWeek", "min", "name = 10", min10.getText());
        db.updateRow("grades_hoursPerWeek", "max", "name = 10", max10.getText());

        db.updateRow("grades_hoursPerWeek", "min", "name = 11", min11.getText());
        db.updateRow("grades_hoursPerWeek", "max", "name = 11", max11.getText());

        db.disconnect();
    }
}
