package main.java;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.*;
import java.util.List;
import java.util.Map;

import main.java.controllers.DBController;
import main.java.workClasses.Book;
import main.java.workClasses.Subject;

import javax.sql.RowSet;

public class Main extends Application {

    private DBController controller = new DBController();

    public TableView<Subject> tableView;

    public static ObservableList<String> wordsList;

    public static ObservableList<Book> books = FXCollections.observableArrayList();

    public static ObservableList<Subject> subjects = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
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

        wordsList = controller.queryColumnValue("Предметы","Класс");
        ObservableList<ObservableList> rows = controller.queryRows("Предметы");
        wordsList.forEach(value -> {
            rows.forEach(row -> {
                if(row.get(0).equals(value))subjects.add(new Subject(row.get(1),row.get(2)));
                System.out.println("" + row.get(2) + row.get(1));
            });
            tableView = new TableView<Subject>(subjects);

            Book book = new Book(tableView, value);
            TableColumn<Subject, String> nameColumn = new TableColumn<>("Предметы");
            nameColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
            tableView.getColumns().add(nameColumn);

            TableColumn<Subject, String> loadColumn = new TableColumn<>("Часы в неделю");
            loadColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("load"));
            tableView.getColumns().add(loadColumn);

            this.books.add(book);
        });



        //
//        controller.deleteRow(tableNames.get(1), "Класс", 5);
//        ObservableList<ObservableList> rows = controller.queryRows(tableNames.get(1));
//        rows.forEach(row -> {
//            row.forEach(System.out::println);
//        });
//
//        controller.addRow(tableNames.get(1),5,null);
//        rows = controller.queryRows(tableNames.get(1));
//        rows.forEach(row -> {
//            row.forEach(System.out::println);
//        });
//
//        controller.updateRow(tableNames.get(1), "КолКлассов","Класс=5", 2);
//        rows = controller.queryRows(tableNames.get(1));
//        rows.forEach(row -> {
//            row.forEach(System.out::println);
//        });
//
//        controller.updateRow(tableNames.get(1), "КолКлассов","Класс=5", null);
//        rows = controller.queryRows(tableNames.get(1));
//        rows.forEach(row -> {
//            row.forEach(System.out::println);
//        });

        Parent root = FXMLLoader.load(getClass().getResource("../fxml/DBFilling.fxml"));
        primaryStage.setTitle("Ввод исходных данных");
        primaryStage.setScene(new Scene(root, 800, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
