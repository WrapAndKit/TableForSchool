package packageForStruct.controllers.DBManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import packageForStruct.controllers.SQLiteC;
import packageForStruct.workClasses.Subject;
import packageForStruct.workClasses.Variables;

import java.net.URL;
import java.util.ResourceBundle;

public class SubjectsNamesC implements Initializable {

    private final SQLiteC db = new SQLiteC("C:\\Users\\Progy\\IdeaProjects\\TableForSchool\\src\\main\\sqlite\\knowledge.sqlite3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.connect();
        setSubjects();
        db.disconnect();
    }

    private void setSubjects() {
        ObservableList subjectsNames = db.queryColumnValue("subjects_hoursPerWeek", "name");
        subjects.setItems(subjectsNames);
    }

    public void handleAddButtonOnAction() {
        db.connect();

        String newSubject = newSubjectName.getText();
        String defaultMin = "0";
        String defaultMax = "10";

        db.addRow("subjects_hoursPerWeek", newSubject, defaultMin, defaultMax);
        Variables.subjects.add(new Subject(newSubject));
        newSubjectName.clear();

        setSubjects();

        db.disconnect();
        DBManagerC.parent.listUpdate();
    }

    public void handleRemoveButtonOnAction() {
        db.connect();
        String selectedSubject = subjects.getSelectionModel().getSelectedItem();
        db.deleteRow("subjects_hoursPerWeek", "name", selectedSubject);
        Variables.deleteSubject(selectedSubject);
        setSubjects();
        db.disconnect();
        DBManagerC.parent.listUpdate();
    }

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                            Объявляем объекты FXML                                |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML private TextField newSubjectName;
    @FXML private ListView<String> subjects;

}
