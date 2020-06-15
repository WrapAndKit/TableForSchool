package packageForStruct.workClasses;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subject {

    private StringProperty groupName;
    private StringProperty name;
    private IntegerProperty load;
    private IntegerProperty minLoadWeek;
    private IntegerProperty maxLoadWeek;
    private static IntegerProperty maxLoadDay;

    public Subject(String name, Integer load, String group){
        this.minLoadWeek = new SimpleIntegerProperty(0);
        this.maxLoadWeek = new SimpleIntegerProperty(10);
        this.maxLoadDay = new SimpleIntegerProperty(10);
        this.groupName = new SimpleStringProperty(group);
        this.name = new SimpleStringProperty(name);
        if((load >= minLoadWeek.getValue())&&(load <= maxLoadWeek.getValue()))
            this.load = new SimpleIntegerProperty(load);
        else System.err.println("Неверная нагрузка");
    }

    public Subject(String name, Integer load){

        this.minLoadWeek = new SimpleIntegerProperty(0);
        this.maxLoadWeek = new SimpleIntegerProperty(10);
        this.maxLoadDay = new SimpleIntegerProperty(10);
        this.groupName = new SimpleStringProperty();
        this.name = new SimpleStringProperty(name);
        if((load >= minLoadWeek.getValue())&&(load <= maxLoadWeek.getValue()))
            this.load = new SimpleIntegerProperty(load);
        else System.err.println("Неверная нагрузка");
    }

    public Subject(String name){
        this.name = new SimpleStringProperty(name);
        this.minLoadWeek = new SimpleIntegerProperty(0);
        this.maxLoadWeek = new SimpleIntegerProperty(10);
        this.load = new SimpleIntegerProperty(0);
        this.maxLoadDay = new SimpleIntegerProperty(0);
        this.groupName = new SimpleStringProperty();
    }

    public Subject(Subject subject){
        this.name = new SimpleStringProperty(subject.getName());
        this.minLoadWeek = new SimpleIntegerProperty(subject.getMinLoadWeek());
        this.maxLoadWeek = new SimpleIntegerProperty(subject.getMaxLoadWeek());
        this.load = new SimpleIntegerProperty(subject.getLoad());
        this.maxLoadDay = new SimpleIntegerProperty(subject.getMaxLoadDay());
        this.groupName = new SimpleStringProperty(subject.getGroupName());
    }

    public Subject(){
        this.name = new SimpleStringProperty();
        this.load = new SimpleIntegerProperty();
        this.minLoadWeek = new SimpleIntegerProperty(0);
        this.maxLoadWeek = new SimpleIntegerProperty(10);
        this.maxLoadDay = new SimpleIntegerProperty(0);
    }

    public String getName(){ return name.get();}

    public Integer getLoad(){ return load.get();}

    public Integer getMinLoadWeek() {
        return minLoadWeek.get();
    }

    public void setMinLoadWeek(Integer minLoadWeek) {
        this.minLoadWeek.set(minLoadWeek);
    }

    public Integer getMaxLoadWeek() {
        return maxLoadWeek.get();
    }

    public void setMaxLoadWeek(Integer maxLoadWeek) {
        this.maxLoadWeek.set(maxLoadWeek);
    }

    public Integer getMaxLoadDay() {
        return maxLoadDay.get();
    }

    public void setMaxLoadDay(Integer maxLoadDay) {
        this.maxLoadDay.set(maxLoadDay);
    }

    public String getGroupName() {
        return groupName.get();
    }

    public void setLoad(int load) {
        this.load.set(load);
    }

    public String toString(){
        return "Name: " + name.getValue() + ", load: " + load.getValue();
    }

}
