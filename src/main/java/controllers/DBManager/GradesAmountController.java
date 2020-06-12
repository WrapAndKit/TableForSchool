package main.java.controllers.DBManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.java.controllers.DBController;

import java.net.URL;
import java.util.ResourceBundle;

public class GradesAmountController implements Initializable {

    @FXML
    private TextField max;

    private final DBController db = new DBController("src/main/sqlite/knowledge.sqlite3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.connect();
        ObservableList<String> firstRaw = db.queryFirstRaw("grades_amount");
        max.setText(firstRaw.get(0));
        db.disconnect();
    }

    public void handleOnAction() {
        db.connect();
        db.updateRow("grades_amount", "max", "true", max.getText());
        db.disconnect();
    }
}
