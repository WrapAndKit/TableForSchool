package main.java.workClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Subject {
    private SimpleStringProperty name;
    private SimpleStringProperty load;

    public Subject(String name, String load){
        this.name = new SimpleStringProperty(name);
        this.load = new SimpleStringProperty(load);
    }

    public String getName(){ return name.get();}
    public void setName(String value){ name.set(value);}

    public String getLoad(){ return load.get();}
    public void setLoad(String value){ load.set(value);}

    public String toString(){
        return "Name: " + name.getValue() + ", load: " + load.getValue();
    }
}
