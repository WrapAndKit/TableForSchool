package packageForStruct.controllers;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CTDController {

    public void setDeleteDialogStage(Stage deleteDialogStage) {
        this.deleteDialogStage = deleteDialogStage;
    }

    private Stage deleteDialogStage;

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setParent(DBFillingController parent) {
        this.parent = parent;
    }

    private DBFillingController parent;

    @FXML
    private void delete(){
        DBController dbController = new DBController();
        dbController.connect();
        dbController.deleteRow("Предметы", "id",id);
        dbController.disconnect();
        deleteDialogStage.close();
        parent.updateTVFromGroup();
    }
    @FXML
    private void cancel(){
        deleteDialogStage.close();
    }
}
