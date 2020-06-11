package packageForStruct;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import packageForStruct.controllers.*;
import packageForStruct.workClasses.Group;

public class Main extends Application {

    private final SQLiteC controller = new SQLiteC();

    private static Stage primaryStage;

    public static ObservableList<String> list;

    public static ObservableList<String> listOfSubjects;

    public static ObservableList<Group> groups= FXCollections.observableArrayList();

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
        controller.connect();
        List<String> tableNames = controller.queryTables();
        tableNames.forEach(tableName ->{
            System.out.println(tableName);
            Map<String, String> columns = controller.queryColumns(tableName);
            columns.forEach((name, type) -> System.out.println("Column " + name + " has type - " + type));
            ObservableList<ObservableList> rows = controller.queryRows(tableName);
            rows.forEach(row -> {
                row.forEach(System.out::println);
            });
        });

        list = controller.queryColumnValue("Классы","Класс");
        listOfSubjects = controller.queryColumnValue("Предметы", "Предмет");
        ObservableList<String> result = FXCollections.observableArrayList();
        listOfSubjects.forEach(subject -> {
            if(!result.contains(subject)) result.add(subject);
        });
        listOfSubjects = result;

        ObservableList<ObservableList> groupRows = controller.queryRows("Классы");
        groupRows.forEach(row -> groups.add(new Group(Integer.parseInt(row.get(0).toString()), Integer.parseInt(row.get(1).toString()))));

        controller.disconnect();

        showDBFillingWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
