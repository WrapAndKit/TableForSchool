package main.java.controllers.DBManager;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

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
            case "Название":
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItems(weeks, new String[]{"Количество недель"});
        setItems(grades, new String[]{"Количество", "Нагрузка в неделю"});
        setItems(subjects, new String[]{"Название", "Нагрузка в неделю", "Нагрузка в день"});
        setItems(teachers, new String[]{"Количество", "Нагрузка в неделю", "Количество предметов"});
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