package packageForStruct.controllers.DBFilling;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageForStruct.Knowledge.Knowledge;
import packageForStruct.Knowledge.SubjectK;
import packageForStruct.controllers.SQLiteC;
import packageForStruct.workClasses.Subject;
import packageForStruct.workClasses.Variables;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSubjectC implements Initializable{

    private Stage addDialogStage;
    private Object selectedItem;
    private DBFillingC parent;
    private final SQLiteC db = new SQLiteC("C:\\Users\\Progy\\IdeaProjects\\TableForSchool\\src\\main\\sqlite\\knowledge.sqlite3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.connect();
        ObservableList subjectsNames = db.queryColumnValue("subjects_hoursPerWeek", "name");
        subjects.setItems(subjectsNames);

        if (!subjectsNames.isEmpty()) {
            subjects.setValue((String) subjectsNames.get(0));
        }
        db.disconnect();
    }

    public void setParent(DBFillingC parent) {
        this.parent = parent;
    }
    public void setSelectedItem(Object selectedItem) {
        this.selectedItem = selectedItem;
    }
    public void setAddDialogStage(Stage addDialogStage) {
        this.addDialogStage = addDialogStage;
    }



    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                                  События                                         |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    //Убрать ненужные условия

    @FXML
    private void ok(){
        SQLiteC SQLiteC = new SQLiteC();
        SQLiteC.connect();
        String name = subjects.getSelectionModel().getSelectedItem();
        ObservableList ids = SQLiteC.queryColumnValue("Предметы", "id");
        ids.sort((o1, o2) -> Math.max(Integer.parseInt(o1.toString()), Integer.parseInt(o2.toString())));
        int loadForGroup = Variables.subjects.stream().filter(subject -> subject.getGroupName().equals(selectedItem)).mapToInt(Subject::getLoad).sum();
        if(name != null && load != null)
            if(Variables.listOfSubjects.contains(name))
                Variables.groups.forEach(group ->{
                    if(group.getName().equals(selectedItem))
                        if(group.getMaxLoad() >= (loadForGroup+Integer.parseInt(load.getText())))
                        {
                            ObservableList<SubjectK> subjects = Knowledge.getSubjectsKn();
                            subjects.forEach(sub -> {
                                if(sub.getName().equals(name)) {
                                    if ((sub.getMinLoadWeek() <= Integer.parseInt(load.getText()))
                                            && (sub.getMaxLoadWeek() >= Integer.parseInt(load.getText()))) {
                                        SQLiteC.addRow("Предметы", selectedItem, name,
                                                load.getText(), Integer.parseInt(ids.get(0).toString()) + 1);
                                        SQLiteC.disconnect();
                                        addDialogStage.close();
                                        parent.listUpdate();
                                        parent.updateTVFromGroup();
                                    }
                                    else System.err.println("Такая нагрузка для предмета невозможна");
                                }
                            });
                        } else System.err.println("Слишком большая нагрузка для класса");
                });
            else System.err.println("Такого предмета нет");
    }
    @FXML
    private void cancel(){
        addDialogStage.close();
    }

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                            Объявляем объекты FXML                                |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML private TextField load;
    @FXML private ChoiceBox<String> subjects;

}
