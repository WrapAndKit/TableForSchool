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

    private final DBController db = new DBController("src/main/sqlite/knowledge.sqlite3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.connect();
        ObservableList subjectsNames = db.queryColumnValue("subjects_hoursPerWeek", "name");
        subjects.setItems(subjectsNames);

        subjects.getSelectionModel().selectedIndexProperty().addListener((ov, value, newValue) -> {
            int subjectNumber = Integer.parseInt(ov.getValue().toString());
            String subject = subjects.getItems().get(subjectNumber);
            setMinAndMax(subject);
        });

        if (!subjectsNames.isEmpty()) {
            subjects.setValue((String) subjectsNames.get(0));
        }

        db.disconnect();
    }

    private void setMinAndMax(String subject) {
        final DBController db = new DBController("src/main/sqlite/knowledge.sqlite3");
        db.connect();
        String condition = "name = '" + subject + "'";
        ObservableList<String> row = db.queryRow("subjects_hoursPerWeek", condition);
        min.setText(row.get(1));
        max.setText(row.get(2));
        db.disconnect();
    }

    public void handleOnAction() {
        db.connect();
        String selectedSubject = subjects.getSelectionModel().getSelectedItem();
        String condition = "name = '" + selectedSubject + "'";
        db.updateRow("subjects_hoursPerWeek", "min", condition, min.getText());
        db.updateRow("subjects_hoursPerWeek", "max", condition, max.getText());
        db.disconnect();
    }
}
