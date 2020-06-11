package packageForStruct.workClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.controllers.SQLiteC;

public class Variables {

    public static ObservableList<String> listOfGroups;
    public static ObservableList<String> listOfSubjects;
    public static ObservableList<Group> groups= FXCollections.observableArrayList();
    private final SQLiteC controller;

    public Variables(){
        this.controller = new SQLiteC();
        controller.connect();
        listOfGroups = listOfGroupsFilling();
        listOfSubjects = listOfSubjectsFilling();
        groups = groupsFilling();
        controller.disconnect();
    }

    public void update(){
        controller.connect();
        listOfGroups = listOfGroupsFilling();
        listOfSubjects = listOfSubjectsFilling();
        groups = groupsFilling();
        controller.disconnect();
    }

    private ObservableList<String> listOfSubjectsFilling(){
        ObservableList<String> listOfSubjects = controller.queryColumnValue("Предметы", "Предмет");
        ObservableList<String> result = FXCollections.observableArrayList();
        listOfSubjects.forEach(subject ->{
            if(!result.contains(subject)) result.add(subject);
        });
        return result;
    }

    private ObservableList<Group> groupsFilling(){
        ObservableList<Group> result= FXCollections.observableArrayList();
        ObservableList<ObservableList> groupRows = controller.queryRows("Классы");
        groupRows.forEach(row -> result.add(new Group(Integer.parseInt(row.get(0).toString()), Integer.parseInt(row.get(1).toString()))));
        return result;
    }

    private ObservableList<String> listOfGroupsFilling(){
        return controller.queryColumnValue("Классы","Класс");
    }
}
