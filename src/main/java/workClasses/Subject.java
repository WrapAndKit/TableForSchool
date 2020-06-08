package main.java.workClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Subject {
    private SimpleObjectProperty name;
    private SimpleObjectProperty load;

    public Subject(Object name, Object load){
        this.name = new SimpleObjectProperty(name);
        this.load = new SimpleObjectProperty(load);
    }

    public String getName(){ return name.get().toString();}
    public void setName(Object value){ name.set(value);}

    public String getLoad(){ return load.get().toString();}
    public void setLoad(Object value){ load.set(value);}

    public String toString(){
        return "Name: " + name.getValue().toString() + ", load: " + load.getValue().toString();
    }
}
