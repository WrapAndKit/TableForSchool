package packageForStruct.controllers.DBManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import packageForStruct.controllers.SQLiteC;

import java.net.URL;
import java.util.ResourceBundle;

public class SubjectsHoursPerDayC implements Initializable {

    @FXML
    private TextField max;

    private final SQLiteC db = new SQLiteC("C:\\Users\\Progy\\IdeaProjects\\TableForSchool\\src\\main\\sqlite\\knowledge.sqlite3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.connect();
        ObservableList<String> firstRaw = db.queryFirstRaw("subjects_hoursPerDay");
        max.setText(firstRaw.get(0));
        db.disconnect();
    }

    public void handleOnAction() {
        db.connect();
        db.updateRow("subjects_hoursPerDay", "max", "true", max.getText());
        db.disconnect();
    }
}
