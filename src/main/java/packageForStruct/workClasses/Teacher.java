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

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLoad() {
        return load.get();
    }

    public StringProperty loadProperty() {
        return load;
    }

    public void setLoad(String load) {
        this.load.set(load);
    }

    public String getClassroom() {
        return classroom.get();
    }

    public StringProperty classroomProperty() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom.set(classroom);
    }
}
