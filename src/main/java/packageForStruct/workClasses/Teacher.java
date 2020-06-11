package packageForStruct.workClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher {

    private StringProperty name;
    private StringProperty load;
    private StringProperty classroom;

    public Teacher(String name, String load, String classroom) {
        this.name = new SimpleStringProperty(name);
        this.load = new SimpleStringProperty(load);
        this.classroom = new SimpleStringProperty(classroom);
    }

    public Teacher(){
        this.name = new SimpleStringProperty();
        this.load = new SimpleStringProperty();
        this.classroom = new SimpleStringProperty();
    }

    public String toString(){
        return "Teacher's name is " + name.getValue() + " he/she has " + load.getValue() + " hours load in " + classroom.getValue() + " classroom!";
    }

    public String getName() {
        return name.get();
    }

    public String getLoad() { return load.get(); }

    public String getClassroom() { return classroom.get(); }

}
