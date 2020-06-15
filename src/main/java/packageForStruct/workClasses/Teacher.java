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
    private StringProperty subject;
    private Integer busy;

    public Integer getBusy() {
        return busy;
    }

    public void setBusy(Integer busy) {
        this.busy = busy;
    }

    private static int count = 0;

    public Teacher(String name, String load, String classroom, String subject) {
        this.name = new SimpleStringProperty(name);
        this.load = new SimpleStringProperty(load);
        this.classroom = new SimpleStringProperty(classroom);
        this.subject = new SimpleStringProperty(subject);
        this.minLoadWeek = new SimpleIntegerProperty();
        this.maxLoadWeek = new SimpleIntegerProperty();
        this.minCount = new SimpleIntegerProperty();
        this.maxCount = new SimpleIntegerProperty();
        this.busy = 0;
        this.count++;
    }

    public Teacher(){
        this.name = new SimpleStringProperty("");
        this.load = new SimpleStringProperty();
        this.classroom = new SimpleStringProperty();
        this.minLoadWeek = new SimpleIntegerProperty();
        this.maxLoadWeek = new SimpleIntegerProperty();
        this.minCount = new SimpleIntegerProperty();
        this.maxCount = new SimpleIntegerProperty();
        this.busy = 0;
        this.count++;
    }

    public Teacher(Teacher teacher){
        this.name = new SimpleStringProperty(teacher.getName());
        this.load = new SimpleStringProperty(teacher.getLoad());
        this.classroom = new SimpleStringProperty(teacher.getClassroom());
        this.minLoadWeek = new SimpleIntegerProperty(teacher.getMinLoadWeek());
        this.maxLoadWeek = new SimpleIntegerProperty(teacher.getMaxLoadWeek());
        this.minCount = new SimpleIntegerProperty(teacher.getMinCount());
        this.maxCount = new SimpleIntegerProperty(teacher.getMaxCount());
        this.busy = teacher.getBusy();
        this.subject = new SimpleStringProperty(teacher.getSubject());
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

    public String getSubject() {
        return subject.get();
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    protected void finalize() throws Throwable{
        this.count--;
    }
}
