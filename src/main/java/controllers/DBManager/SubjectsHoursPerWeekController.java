package main.java.controllers.DBManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import main.java.controllers.DBController;

import java.net.URL;
import java.util.ResourceBundle;

public class SubjectsHoursPerWeekController implements Initializable {

    @FXML
    private TextField min;

    @FXML
    private TextField max;

    @FXML
    private ChoiceBox<String> subjects;

    private final DBController db = new DBController("src/main/sqlite/knowledges.sqlite3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.connect();
        ObservableList subjectsNames = db.queryColumnValue("subjects_hoursPerWeek", "name");
        subjects.setItems(subjectsNames);

        subjects.getSelectionModel().selectedIndexProperty().addListener((ov, value, newValue) -> {
            int subjectNumber = Integer.parseInt(ov.getValue().toString());
            setMinAndMax(subjectNumber);
        });

        subjects.setValue((String) subjectsNames.get(0));
        db.disconnect();
    }

    private void setMinAndMax(int subjectNumber) {
        DBController db = new DBController("src/main/sqlite/knowledges.sqlite3");
        db.connect();
        ObservableList<ObservableList> rows = db.queryRows("subjects_hoursPerWeek");
        ObservableList row = rows.get(subjectNumber);
        min.setText((String) row.get(1));
        max.setText((String) row.get(2));
        db.disconnect();
    }
}
