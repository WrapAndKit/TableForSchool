package packageForStruct;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import packageForStruct.controllers.*;

public class Main extends Application {

    private static Stage primaryStage;

    public void showDBFillingWindow(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/DBFilling.fxml"));
            primaryStage.setTitle("Ввод исходных данных");
            primaryStage.setScene(new Scene(root, 800, 650));
            primaryStage.show();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void showAddSubjectWindow(Object item, DBFillingC filCon){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/AddSubject.fxml"));
            AnchorPane page = loader.load();
            Stage addDialogStage = new Stage();
            addDialogStage.setTitle("Добавление предмета");
            addDialogStage.initModality(Modality.WINDOW_MODAL);
            addDialogStage.initOwner(primaryStage);
            addDialogStage.setScene(new Scene(page));
            AddSubjectC controller = loader.getController();
            controller.setAddDialogStage(addDialogStage);
            controller.setSelectedItem(item);
            controller.setParent(filCon);
            addDialogStage.show();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void showDeleteSubjectWindow(DBFillingC filCon, int id){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/DeleteSubject.fxml"));
            AnchorPane page = loader.load();
            Stage addDialogStage = new Stage();
            addDialogStage.setTitle("Удаление предмета");
            addDialogStage.initModality(Modality.WINDOW_MODAL);
            addDialogStage.initOwner(primaryStage);
            addDialogStage.setScene(new Scene(page));
            DeleteSubjectC controller = loader.getController();
            controller.setDeleteDialogStage(addDialogStage);
            controller.setId(id);
            controller.setParent(filCon);
            addDialogStage.show();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void showAddTeacherWindow(Object item, DBFillingC filCon){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/AddTeacher.fxml"));
            AnchorPane page = loader.load();
            Stage addDialogStage = new Stage();
            addDialogStage.setTitle("Добавление преподавателя");
            addDialogStage.initModality(Modality.WINDOW_MODAL);
            addDialogStage.initOwner(primaryStage);
            addDialogStage.setScene(new Scene(page));
            AddTeacherC controller = loader.getController();
            controller.setAddDialogStage(addDialogStage);
            controller.setSelectedItem(item);
            controller.setParent(filCon);
            addDialogStage.show();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void showDeleteTeacherWindow(DBFillingC filCon, int id){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/DeleteTeacher.fxml"));
            AnchorPane page = loader.load();
            Stage addDialogStage = new Stage();
            addDialogStage.setTitle("Удаление преподавателя");
            addDialogStage.initModality(Modality.WINDOW_MODAL);
            addDialogStage.initOwner(primaryStage);
            addDialogStage.setScene(new Scene(page));
            DeleteTeacherС controller = loader.getController();
            controller.setDeleteDialogStage(addDialogStage);
            controller.setId(id);
            controller.setParent(filCon);
            addDialogStage.show();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage){
        Main.primaryStage = primaryStage;
        showDBFillingWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
