package main.java.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class DBManagerController implements Initializable {

    @FXML
    private ListView<String> weeks;

    @FXML
    private ListView<String> grades;

    @FXML
    private ListView<String> subjects;

    @FXML
    private ListView<String> teachers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItems(weeks, new String[]{"Количество недель"});
        setItems(grades, new String[]{"Количество", "Нагрузка в неделю"});
        setItems(subjects, new String[]{"Название", "Нагрузка в неделю", "Нагрузка в день"});
        setItems(teachers, new String[]{"Количество", "Нагрузка в неделю", "Количество предметов"});

        weeks.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val) {
                        //
                    }
                });
    }

    private void setItems(ListView<String> listView, String[] items) {
        listView.setItems(FXCollections.observableArrayList(items));
    }
}