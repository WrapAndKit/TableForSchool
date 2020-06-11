package main.java.controllers.DBManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.java.controllers.DBController;

import java.net.URL;
import java.util.ResourceBundle;

public class TeachersAmountController implements Initializable {

    @FXML
    private TextField min;

    @FXML
    private TextField max;

    private DBController db = new DBController("src/main/sqlite/knowledges.sqlite3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.connect();
        ObservableList<String> firstRaw = db.queryFirstRaw("teachers_amount");
        min.setText(firstRaw.get(0));
        max.setText(firstRaw.get(1));
        db.disconnect();
    }
}
