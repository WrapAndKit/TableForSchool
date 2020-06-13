package packageForStruct.workClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher {

    private StringProperty name;
    private StringProperty load;
    private StringProperty classroom;
    private IntegerProperty minLoadWeek;
    private IntegerProperty maxLoadWeek;
    private static IntegerProperty minCount;
    private static IntegerProperty maxCount;

    private static int count = 0;

    public Teacher(String name, String load, String classroom) {
        this.name = new SimpleStringProperty(name);
        this.load = new SimpleStringProperty(load);
        this.classroom = new SimpleStringProperty(classroom);
        this.minLoadWeek = new SimpleIntegerProperty();
        this.maxLoadWeek = new SimpleIntegerProperty();
        this.minCount = new SimpleIntegerProperty();
        this.maxCount = new SimpleIntegerProperty();
        this.count++;
    }

    public Teacher(){
        this.name = new SimpleStringProperty();
        this.load = new SimpleStringProperty();
        this.classroom = new SimpleStringProperty();
        this.minLoadWeek = new SimpleIntegerProperty();
        this.maxLoadWeek = new SimpleIntegerProperty();
        this.minCount = new SimpleIntegerProperty();
        this.maxCount = new SimpleIntegerProperty();
        this.count++;
    }

    public String toString(){
        return "Teacher's name is " + name.getValue() + " he/she has " + load.getValue() + " hours load in " + classroom.getValue() + " classroom!";
    }

    public String getName() {
        return name.get();
    }

    public String getLoad() { return load.get(); }

    public String getClassroom() { return classroom.get(); }

    public int getMinLoadWeek() {
        return minLoadWeek.get();
    }

    public void setMinLoadWeek(int minLoadWeek) {
        this.minLoadWeek.set(minLoadWeek);
    }

    public int getMaxLoadWeek() {
        return maxLoadWeek.get();
    }

    public void setMaxLoadWeek(int maxLoadWeek) {
        this.maxLoadWeek.set(maxLoadWeek);
    }

    public static int getMinCount() {
        return minCount.get();
    }

    public static void setMinCount(int minCount) {
        Teacher.minCount.set(minCount);
    }

    public static int getMaxCount() {
        return maxCount.get();
    }

    public static void setMaxCount(int maxCount) {
        Teacher.maxCount.set(maxCount);
    }

    public static int getCount() {
        return count;
    }

    protected void finalize() throws Throwable{
        this.count--;
    }
}
