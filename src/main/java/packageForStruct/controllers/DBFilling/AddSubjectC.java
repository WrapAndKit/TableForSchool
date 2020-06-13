package packageForStruct.controllers.DBFilling;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packageForStruct.controllers.SQLiteC;

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
        if(name != null && !name.getText().isEmpty())
            if(load != null && !load.getText().isEmpty()){
                SQLiteC.addRow("Предметы",selectedItem,name.getText(),
                        load.getText(), Integer.parseInt(ids.get(0).toString())+1);
                SQLiteC.disconnect();
                addDialogStage.close();
                parent.listUpdate();
                parent.updateTVFromGroup();
            }
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

    @FXML
    private TextField name;
    @FXML
    private TextField load;
}
