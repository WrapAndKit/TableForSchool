package packageForStruct.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DeleteSubjectC {

    public void setDeleteDialogStage(Stage deleteDialogStage) {
        this.deleteDialogStage = deleteDialogStage;
    }

    private Stage deleteDialogStage;

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setParent(DBFillingC parent) {
        this.parent = parent;
    }

    private DBFillingC parent;

    @FXML
    private void delete(){
        SQLiteC SQLiteC = new SQLiteC();
        SQLiteC.connect();
        SQLiteC.deleteRow("Предметы", "id",id);
        SQLiteC.disconnect();
        deleteDialogStage.close();
        parent.updateTVFromGroup();
    }
    @FXML
    private void cancel(){
        deleteDialogStage.close();
    }
}
