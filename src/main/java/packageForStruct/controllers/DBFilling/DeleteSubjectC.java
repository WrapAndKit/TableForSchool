package packageForStruct.controllers.DBFilling;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import packageForStruct.controllers.SQLiteC;

public class DeleteSubjectC {

    private Stage deleteDialogStage;
    private int id;
    private DBFillingC parent;

    public void setId(int id) { this.id = id; }
    public void setDeleteDialogStage(Stage deleteDialogStage) {
        this.deleteDialogStage = deleteDialogStage;
    }
    public void setParent(DBFillingC parent) {
        this.parent = parent;
    }

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                                  События                                         |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML
    private void delete(){
        SQLiteC SQLiteC = new SQLiteC();
        SQLiteC.connect();
        SQLiteC.deleteRow("Предметы", "id",id);
        SQLiteC.disconnect();
        deleteDialogStage.close();
        parent.listUpdate();
        parent.updateTVFromGroup();
    }
    @FXML
    private void cancel(){
        deleteDialogStage.close();
    }
}
