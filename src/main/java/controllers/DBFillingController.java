package main.java.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    @FXML
    private RadioButton Radio5A;
    @FXML
    private RadioButton Radio5AB;
    @FXML
    private RadioButton Radio5ABV;
    @FXML
    private RadioButton Radio5ABVG;
    @FXML
    private RadioButton Radio5ABVGD;

    @FXML
    private RadioButton Radio6A;
    @FXML
    private RadioButton Radio6AB;
    @FXML
    private RadioButton Radio6ABV;
    @FXML
    private RadioButton Radio6ABVG;
    @FXML
    private RadioButton Radio6ABVGD;

    @FXML
    private RadioButton Radio7A;
    @FXML
    private RadioButton Radio7AB;
    @FXML
    private RadioButton Radio7ABV;
    @FXML
    private RadioButton Radio7ABVG;
    @FXML
    private RadioButton Radio7ABVGD;

    @FXML
    private RadioButton Radio8A;
    @FXML
    private RadioButton Radio8AB;
    @FXML
    private RadioButton Radio8ABV;
    @FXML
    private RadioButton Radio8ABVG;
    @FXML
    private RadioButton Radio8ABVGD;

    @FXML
    private RadioButton Radio9A;
    @FXML
    private RadioButton Radio9AB;
    @FXML
    private RadioButton Radio9ABV;
    @FXML
    private RadioButton Radio9ABVG;
    @FXML
    private RadioButton Radio9ABVGD;

    @FXML
    private RadioButton Radio10A;
    @FXML
    private RadioButton Radio10AB;
    @FXML
    private RadioButton Radio10ABV;
    @FXML
    private RadioButton Radio10ABVG;
    @FXML
    private RadioButton Radio10ABVGD;

    @FXML
    private RadioButton Radio11A;
    @FXML
    private RadioButton Radio11AB;
    @FXML
    private RadioButton Radio11ABV;
    @FXML
    private RadioButton Radio11ABVG;
    @FXML
    private RadioButton Radio11ABVGD;

    private ToggleGroup group5;
    private ToggleGroup group6;
    private ToggleGroup group7;
    private ToggleGroup group8;
    private ToggleGroup group9;
    private ToggleGroup group10;
    private ToggleGroup group11;

    public void initialize(URL location, ResourceBundle resources){
//        Main.wordsList.forEach(System.out::println);
        this.groupList.setItems(Main.list);
        Main.subjects.forEach(System.out::println);

        TableColumn<Subject, String> nameColumn = new TableColumn<>("Предметы");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Subject, String> loadColumn = new TableColumn<>("Часы в неделю");
        loadColumn.setCellValueFactory(new PropertyValueFactory<>("load"));

        this.tableViewSubjects.setItems(Main.subjects);
        this.tableViewSubjects.getColumns().setAll(nameColumn, loadColumn);

        toggleRadio();
    }

    public void toggleRadio(){
        group5 = new ToggleGroup();
        group6 = new ToggleGroup();
        group7 = new ToggleGroup();
        group8 = new ToggleGroup();
        group9 = new ToggleGroup();
        group10 = new ToggleGroup();
        group11 = new ToggleGroup();

        Radio5A.setToggleGroup(group5);
        Radio5AB.setToggleGroup(group5);
        Radio5ABV.setToggleGroup(group5);
        Radio5ABVG.setToggleGroup(group5);
        Radio5ABVGD.setToggleGroup(group5);

        Radio6A.setToggleGroup(group6);
        Radio6AB.setToggleGroup(group6);
        Radio6ABV.setToggleGroup(group6);
        Radio6ABVG.setToggleGroup(group6);
        Radio6ABVGD.setToggleGroup(group6);

        Radio7A.setToggleGroup(group7);
        Radio7AB.setToggleGroup(group7);
        Radio7ABV.setToggleGroup(group7);
        Radio7ABVG.setToggleGroup(group7);
        Radio7ABVGD.setToggleGroup(group7);

        Radio8A.setToggleGroup(group8);
        Radio8AB.setToggleGroup(group8);
        Radio8ABV.setToggleGroup(group8);
        Radio8ABVG.setToggleGroup(group8);
        Radio8ABVGD.setToggleGroup(group8);

        Radio9A.setToggleGroup(group9);
        Radio9AB.setToggleGroup(group9);
        Radio9ABV.setToggleGroup(group9);
        Radio9ABVG.setToggleGroup(group9);
        Radio9ABVGD.setToggleGroup(group9);

        Radio10A.setToggleGroup(group10);
        Radio10AB.setToggleGroup(group10);
        Radio10ABV.setToggleGroup(group10);
        Radio10ABVG.setToggleGroup(group10);
        Radio10ABVGD.setToggleGroup(group10);

        Radio11A.setToggleGroup(group11);
        Radio11AB.setToggleGroup(group11);
        Radio11ABV.setToggleGroup(group11);
        Radio11ABVG.setToggleGroup(group11);
        Radio11ABVGD.setToggleGroup(group11);

    }


}
