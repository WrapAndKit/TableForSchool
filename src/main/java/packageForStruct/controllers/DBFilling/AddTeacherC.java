package packageForStruct.controllers.DBFilling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageForStruct.Knowledge.Knowledge;
import packageForStruct.controllers.SQLiteC;
import packageForStruct.workClasses.Variables;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTeacherC implements Initializable {

    private Stage addDialogStage;
    private DBFillingC parent;
    private Object selectedItem;

    public void setSelectedItem(Object selectedItem) {
        this.selectedItem = selectedItem;
    }
    public void setAddDialogStage(Stage addDialogStage) { this.addDialogStage = addDialogStage; }
    public void setParent(DBFillingC parent) { this.parent = parent; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                                  События                                         |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML
    public void ok(){
        SQLiteC SQLiteC = new SQLiteC();
        SQLiteC.connect();
        ObservableList ids = SQLiteC.queryColumnValue("Преподаватели", "id");
        ids.sort((o1, o2) -> Math.max(Integer.parseInt(o1.toString()), Integer.parseInt(o2.toString())));
        ObservableList<String> teachers = FXCollections.observableArrayList();
        Variables.teachers.forEach(teacher -> {
            if(!teachers.contains(teacher.getName())) teachers.add(teacher.getName());
        });
        int countOfTeachers = teachers.size();
        if (countOfTeachers <= Knowledge.getMaxCountTeacher()){

            if(name != null && !name.getText().isEmpty())

                if(load != null && !load.getText().isEmpty())

                    if(classroom != null && !classroom.getText().isEmpty()){

                        int count = (int) Variables.teachers.stream().filter(teacher -> teacher.getName().equals(name.getText())).count();
                        int loadForTeacher = Variables.teachers.stream().filter(teacher -> teacher.getName().equals(name.getText())).mapToInt(teacher ->
                                Integer.parseInt(teacher.getLoad())).sum();

                        if((Knowledge.getMinLoadTeacher() <= Integer.parseInt(load.getText())) &&
                                (Knowledge.getMaxLoadTeacher() >= Integer.parseInt(load.getText())))

                            if (Knowledge.getMaxCountSubForTeacher() > count)

                                if(Knowledge.getMaxLoadTeacher() >= (Integer.parseInt(load.getText()) + loadForTeacher)){

                                    SQLiteC.addRow("Преподаватели",Integer.parseInt(ids.get(0).toString())+1,name.getText(),
                                            selectedItem, classroom.getText(),load.getText());
                                    SQLiteC.disconnect();
                                    addDialogStage.close();
                                    parent.listUpdate();
                                    parent.updateTVFromSubject();

                                } else System.err.println("Слишком большая нагрузка для преподавателя");
                            else System.err.println("Слишком много предметов для преподавателя");
                        else System.err.println("Неверная нагрузка для преподавателя");
                    }
        } else System.err.println("Слишком много преподавателей");

    }
    @FXML
    public void cancel(){ addDialogStage.close();}

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                            Объявляем объекты FXML                                |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML private TextField name;
    @FXML private TextField load;
    @FXML private TextField classroom;

}
