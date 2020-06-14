package packageForStruct.workClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Day {
    private StringProperty name;
    private ObservableList<Lesson> lessons = FXCollections.observableArrayList();


    public Day(String group, String name){
        this.name = new SimpleStringProperty(name);
    }

    public Day(String name){
        this.name = new SimpleStringProperty(name);
    }

    public void addLessons(Lesson lesson){
        this.lessons.add(lesson);
    }

    public String getName() {
        return name.get();
    }

    public ObservableList<Lesson> getLessons() {
        return lessons;
    }

    public String toString(){
        String lesson = "";
        for (int i = 0; i < lessons.size(); i++) {
            String teachers = "";
            for (int j = 0; j < lessons.get(i).getTeacher().size(); j++) {
                teachers += " " + lessons.get(i).getTeacher().get(j).getName() + ";";
            }
            lesson+= "Lesson " + lessons.get(i).getSubject().getName() + " with" + teachers + "\n";
        }
        return "Day - " + name.getValue() + " \n Lesson: \n" + lesson;
    }
}
