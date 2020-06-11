package packageForStruct.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ASController implements Initializable{

    private Stage addDialogStage;

    public void setParent(DBFillingController parent) {
        this.parent = parent;
    }

    private DBFillingController parent;

    public void setSelectedItem(Object selectedItem) {
        this.selectedItem = selectedItem;
    }

    private Object selectedItem;

    @FXML
    private TextField name;

    @FXML
    private TextField load;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void ok(){
        DBController dbController = new DBController();
        dbController.connect();
        ObservableList ids = dbController.queryColumnValue("Предметы", "id");
        ids.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.parseInt(o1.toString()) > Integer.parseInt(o2.toString())?Integer.parseInt(o1.toString()):Integer.parseInt(o2.toString());
            }
        });
        if(name != null && !name.getText().isEmpty())
            if(load != null && !load.getText().isEmpty()){
                dbController.addRow("Предметы",selectedItem,name.getText(),
                        load.getText(), Integer.parseInt(ids.get(0).toString())+1);
                dbController.disconnect();
                addDialogStage.close();
                parent.updateTVFromGroup();
            }
    }

    @FXML
    private void cancel(){
        addDialogStage.close();
    }

    public void setAddDialogStage(Stage addDialogStage) {
        this.addDialogStage = addDialogStage;
    }

}
