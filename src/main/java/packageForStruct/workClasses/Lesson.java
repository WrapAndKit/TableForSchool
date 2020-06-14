package packageForStruct.workClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Lesson {
    private StringProperty group;
    private Subject subject;
    private ObservableList<Teacher> teacher = FXCollections.observableArrayList();
    private StringProperty classroom;
    private IntegerProperty number;

    public Lesson(String group, Subject subject, Teacher teacher, String classroom, Integer number) {
        this.group = new SimpleStringProperty(group);;
        this.subject = subject;
        this.teacher.add(teacher);
        this.classroom = new SimpleStringProperty(classroom);
        this.number = new SimpleIntegerProperty(number);
    }

    public Lesson(String group, Subject subject, Teacher teacher) {
        this.group = new SimpleStringProperty(group);;
        this.subject = subject;
        this.teacher.add(teacher);
        this.classroom = new SimpleStringProperty();
        this.number = new SimpleIntegerProperty();
    }

    public Lesson(String group, Subject subject) {
        this.group = new SimpleStringProperty(group);;
        this.subject = subject;
        this.classroom = new SimpleStringProperty();
        this.number = new SimpleIntegerProperty();
    }

    public ObservableList<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher.add(teacher);
    }

    public String getClassroom() {
        return classroom.get();
    }

    public void setClassroom(String classroom) {
        this.classroom.set(classroom);
    }

    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public Subject getSubject() {
        return subject;
    }

    public String getGroup() {
        return group.get();
    }

    public String toString(){
        if(teacher.size() != 0)
            if (teacher.size() == 1)
                return "Group " + group.getValue() + " has " + subject.getName() + " with " + teacher.get(0).getName();
            else {
                String teachers = "";
                for (int i = 0; i < teacher.size(); i++) {
                    teachers+= teacher.get(i).getName() + " ;";
                }
                return "Group " + group.getValue() + " has " + subject.getName() + " with: " + teachers;
            }
        else return "Group " + group.getValue() + " has " + subject.getName();
    }

}
