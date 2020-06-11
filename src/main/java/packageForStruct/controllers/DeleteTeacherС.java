package packageForStruct.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DeleteTeacherС {

    private Stage deleteDialogStage;
    private int id;
    private DBFillingC parent;

    public void setId(int id) {
        this.id = id;
    }
    public void setParent(DBFillingC parent) {
        this.parent = parent;
    }
    public void setDeleteDialogStage(Stage deleteDialogStage) {
        this.deleteDialogStage = deleteDialogStage;
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
        SQLiteC.deleteRow("Преподаватели", "id",id);
        SQLiteC.disconnect();
        deleteDialogStage.close();
        parent.listUpdate();
        parent.updateTVFromSubject();
    }
    @FXML
    private void cancel(){
        deleteDialogStage.close();
    }
}
