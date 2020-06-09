package main.java.controllers.DBManager;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;

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

    @FXML
    private SplitPane mainPane;

    @FXML public void handleMouseClickWeeks(MouseEvent event) {
             setRightPane("WeeksAmount");
    }

    @FXML public void handleMouseClickGrades(MouseEvent event) {
        String selectedItem = grades.getSelectionModel().getSelectedItem();

        if (selectedItem == "Количество") {
            setRightPane("GradesAmount");
        } else if (selectedItem == "Нагрузка в неделю") {
            setRightPane("GradesHoursPerWeek");
        }
    }

    @FXML public void handleMouseClickSubjects(MouseEvent event) {
        String selectedItem = subjects.getSelectionModel().getSelectedItem();

        System.out.println("clicked on " + selectedItem);

        if (selectedItem == "Название") {
            setRightPane("SubjectsNames");
        } else if (selectedItem == "Нагрузка в неделю") {
            setRightPane("SubjectsHoursPerWeek");
        } else if (selectedItem == "Нагрузка в день") {
            setRightPane("SubjectsHoursPerDay");
        }
    }

    @FXML public void handleMouseClickTeachers(MouseEvent event) {
        String selectedItem = teachers.getSelectionModel().getSelectedItem();

        if (selectedItem == "Количество") {
            setRightPane("TeachersAmount");
        } else if (selectedItem == "Нагрузка в неделю") {
            setRightPane("TeachersHoursPerWeek");
        } else if (selectedItem == "Количество предметов") {
            setRightPane("TeachersSubjects");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItems(weeks, new String[]{"Количество недель"});
        setItems(grades, new String[]{"Количество", "Нагрузка в неделю"});
        setItems(subjects, new String[]{"Название", "Нагрузка в неделю", "Нагрузка в день"});
        setItems(teachers, new String[]{"Количество", "Нагрузка в неделю", "Количество предметов"});
        setRightPane("WeeksAmount");
    }

    private void setItems(ListView<String> listView, String[] items) {
        listView.setItems(FXCollections.observableArrayList(items));
    }

    private void setRightPane(String fileName) {
        TitledPane titledPane = TitledPaneLoader.getTitledPane(fileName);
        mainPane.getItems().set(1, titledPane);
    }
}