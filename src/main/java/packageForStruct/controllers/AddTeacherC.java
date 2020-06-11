package packageForStruct.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
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
        ids.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.parseInt(o1.toString()) > Integer.parseInt(o2.toString())?Integer.parseInt(o1.toString()):Integer.parseInt(o2.toString());
            }
        });
        if(name != null && !name.getText().isEmpty())
            if(load != null && !load.getText().isEmpty())
                if(classroom != null && !classroom.getText().isEmpty()){
                    SQLiteC.addRow("Преподаватели",Integer.parseInt(ids.get(0).toString())+1,name.getText(),
                            selectedItem, classroom.getText(),load.getText());
                    SQLiteC.disconnect();
                    addDialogStage.close();
                    parent.listUpdate();
                    parent.updateTVFromSubject();
            }
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

    @FXML
    private TextField name;
    @FXML
    private TextField load;
    @FXML
    private TextField classroom;

}
