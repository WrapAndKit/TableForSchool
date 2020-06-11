package packageForStruct.workClasses;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subject {
    private StringProperty name;
    private StringProperty load;

    public Subject(String name, String load){
        this.name = new SimpleStringProperty(name);
        this.load = new SimpleStringProperty(load);
    }

    public Subject(){
        this.name = new SimpleStringProperty();
        this.load = new SimpleStringProperty();
    }

    public String getName(){ return name.get();}

    public String getLoad(){ return load.get();}

    public String toString(){
        return "Name: " + name.getValue() + ", load: " + load.getValue();
    }
}
