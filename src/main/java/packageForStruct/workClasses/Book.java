package packageForStruct.workClasses;

import javafx.scene.control.TableView;

public class Book{
    private TableView<Subject> subjects;
    private String group;

    public Book(TableView<Subject> subjects, String group) {
        this.subjects = subjects;
        this.group = group;
    }

    public TableView<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(TableView<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String toString(){
        return this.group;
    }
}