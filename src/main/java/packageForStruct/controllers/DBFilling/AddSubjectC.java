package packageForStruct.controllers.DBFilling;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    public void setParent(DBFillingC parent) {
        this.parent = parent;
    }
    public void setSelectedItem(Object selectedItem) {
        this.selectedItem = selectedItem;
    }
    public void setAddDialogStage(Stage addDialogStage) {
        this.addDialogStage = addDialogStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                                  События                                         |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML
    private void ok(){
        SQLiteC SQLiteC = new SQLiteC();
        SQLiteC.connect();
        ObservableList ids = SQLiteC.queryColumnValue("Предметы", "id");
        ids.sort((o1, o2) -> Math.max(Integer.parseInt(o1.toString()), Integer.parseInt(o2.toString())));
        int loadForGroup = Variables.subjects.stream().filter(subject -> subject.getGroupName().equals(selectedItem)).mapToInt(Subject::getLoad).sum();
        if(name != null && load != null)
            if(Variables.listOfSubjects.contains(name.getText()))
                Variables.groups.forEach(group ->{
                    if(group.getName().equals(selectedItem))
                        if(group.getMaxLoad() >= (loadForGroup+Integer.parseInt(load.getText())))
                        {
                            ObservableList<SubjectK> subjects = Knowledge.getSubjectsKn();
                            subjects.forEach(sub -> {
                                if(sub.getName().equals(name.getText())) {
                                    if ((sub.getMinLoadWeek() <= Integer.parseInt(load.getText()))
                                            && (sub.getMaxLoadWeek() >= Integer.parseInt(load.getText()))) {
                                        SQLiteC.addRow("Предметы", selectedItem, name.getText(),
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

    @FXML private TextField name;
    @FXML private TextField load;
}
