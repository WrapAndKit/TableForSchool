package main.java.controllers.DBManager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DBManagerController implements Initializable {

    @FXML private ListView<String> weeks;
    @FXML private ListView<String> grades;
    @FXML private ListView<String> subjects;
    @FXML private ListView<String> teachers;

    @FXML private TitledPane titledPane1;
    @FXML private TitledPane titledPane2;
    @FXML private TitledPane titledPane3;
    @FXML private TitledPane titledPane4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItems(weeks, new String[]{"Количество недель"});
        setItems(grades, new String[]{"Количество", "Нагрузка в неделю"});
        setItems(subjects, new String[]{"Добавить/Удалить", "Нагрузка в неделю", "Нагрузка в день"});
        setItems(teachers, new String[]{"Количество", "Нагрузка в неделю", "Количество предметов"});

        ChangeListener<Boolean> changeListener = (observable, oldValue, newValue) -> {
            if (!newValue) {
                rightPane.getChildren().clear();
            }
        };

        titledPane1.expandedProperty().addListener(changeListener);
        titledPane2.expandedProperty().addListener(changeListener);
        titledPane3.expandedProperty().addListener(changeListener);
        titledPane4.expandedProperty().addListener(changeListener);
    }

    @FXML public void handleMouseClickWeeks() {
        setRightPane("WeeksAmount");
    }

    @FXML public void handleMouseClickGrades() {
        String selectedItem = grades.getSelectionModel().getSelectedItem();

        switch (selectedItem) {
            case "Количество":
                setRightPane("GradesAmount");
                break;
            case "Нагрузка в неделю":
                setRightPane("GradesHoursPerWeek");
                break;
        }
    }

    @FXML public void handleMouseClickSubjects() {
        String selectedItem = subjects.getSelectionModel().getSelectedItem();

        switch (selectedItem) {
            case "Добавить/Удалить":
                setRightPane("SubjectsNames");
                break;
            case "Нагрузка в неделю":
                setRightPane("SubjectsHoursPerWeek");
                break;
            case "Нагрузка в день":
                setRightPane("SubjectsHoursPerDay");
                break;
        }
    }

    @FXML public void handleMouseClickTeachers() {
        String selectedItem = teachers.getSelectionModel().getSelectedItem();

        switch (selectedItem) {
            case "Количество":
                setRightPane("TeachersAmount");
                break;
            case "Нагрузка в неделю":
                setRightPane("TeachersHoursPerWeek");
                break;
            case "Количество предметов":
                setRightPane("TeachersSubjects");
                break;
        }
    }

    private void setItems(ListView<String> listView, String[] items) {
        listView.setItems(FXCollections.observableArrayList(items));
    }

    @FXML
    private AnchorPane rightPane;

    private void setRightPane(String fileName) {
        TitledPane titledPane = TitledPaneLoader.getTitledPane(fileName);
        rightPane.getChildren().clear();
        AnchorPane.setLeftAnchor(titledPane,10.0);
        AnchorPane.setRightAnchor(titledPane, 10.0);
        AnchorPane.setTopAnchor(titledPane, 10.0);
        rightPane.getChildren().addAll(titledPane);
    }
}