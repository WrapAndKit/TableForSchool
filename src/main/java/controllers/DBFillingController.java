package main.java.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.Main;
import main.java.workClasses.Subject;

import java.net.URL;
import java.util.ResourceBundle;

public class DBFillingController implements Initializable {


    @FXML
    private ListView groupList;

    @FXML
    private TableView<Subject> tableViewSubjects;


    public void initialize(URL location, ResourceBundle resources){
//        Main.wordsList.forEach(System.out::println);
        this.groupList.setItems(Main.books);
        Main.subjects.forEach(System.out::println);

        this.tableViewSubjects = new TableView<>(Main.subjects);

        TableColumn<Subject, String> nameColumn = new TableColumn<>("Предметы");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableViewSubjects.getColumns().add(nameColumn);

        TableColumn<Subject, String> loadColumn = new TableColumn<>("Часы в неделю");
        loadColumn.setCellValueFactory(new PropertyValueFactory<>("load"));
        tableViewSubjects.getColumns().add(loadColumn);

    }






}
