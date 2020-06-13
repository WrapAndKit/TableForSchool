package packageForStruct.controllers.DBManager;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import packageForStruct.controllers.DBFilling.DBFillingC;

import java.net.URL;
import java.util.ResourceBundle;

public class DBManagerC implements Initializable {

    protected static DBFillingC parent;

    public void setParent(DBFillingC parent) {
        this.parent = parent;
    }

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

    private void setItems(ListView<String> listView, String[] items) {
        listView.setItems(FXCollections.observableArrayList(items));
    }

    private void setRightPane(String fileName) {
        TitledPane titledPane = TitledPaneLoader.getTitledPane(fileName);
        rightPane.getChildren().clear();
        AnchorPane.setLeftAnchor(titledPane,10.0);
        AnchorPane.setRightAnchor(titledPane, 10.0);
        AnchorPane.setTopAnchor(titledPane, 10.0);
        rightPane.getChildren().addAll(titledPane);
    }

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                                  События                                         |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML public void handleMouseClickWeeks() {
        setRightPane("WeeksAmount");
    }
    @FXML public void handleMouseClickGrades() {
        String selectedItem = grades.getSelectionModel().getSelectedItem();

        switch (selectedItem) {
            case "Количество" -> setRightPane("GradesAmount");
            case "Нагрузка в неделю" -> setRightPane("GradesHoursPerWeek");
        }
    }
    @FXML public void handleMouseClickSubjects() {
        String selectedItem = subjects.getSelectionModel().getSelectedItem();

        switch (selectedItem) {
            case "Добавить/Удалить" -> setRightPane("SubjectsNames");
            case "Нагрузка в неделю" -> setRightPane("SubjectsHoursPerWeek");
            case "Нагрузка в день" -> setRightPane("SubjectsHoursPerDay");
        }
    }
    @FXML public void handleMouseClickTeachers() {
        String selectedItem = teachers.getSelectionModel().getSelectedItem();

        switch (selectedItem) {
            case "Количество" -> setRightPane("TeachersAmount");
            case "Нагрузка в неделю" -> setRightPane("TeachersHoursPerWeek");
            case "Количество предметов" -> setRightPane("TeachersSubjects");
        }
    }

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                            Объявляем объекты FXML                                |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML private AnchorPane rightPane;

    @FXML private ListView<String> weeks;
    @FXML private ListView<String> grades;
    @FXML private ListView<String> subjects;
    @FXML private ListView<String> teachers;

    @FXML private TitledPane titledPane1;
    @FXML private TitledPane titledPane2;
    @FXML private TitledPane titledPane3;
    @FXML private TitledPane titledPane4;
}