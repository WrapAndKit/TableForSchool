package packageForStruct.workClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.Knowledge.Knowledge;
import packageForStruct.workClasses.DayOfWeek.*;

public class Week {

    private StringProperty group;
    private ObservableList<Lesson> lessons = FXCollections.observableArrayList();

    public Monday monday;
    public Tuesday tuesday;
    public Wednesday wednesday;
    public Thursday thursday;
    public Friday friday;
    public Saturday saturday;

    public Week(String group) {
        TableOfLessons table = new TableOfLessons();
        this.group = new SimpleStringProperty(group);
        table.lessons.forEach(lesson -> {
            System.out.println(lesson);
            if (lesson.getGroup().equals(group)) {
                this.lessons.add(lesson);
                System.out.println(lesson);
            }
        });
        createDays();
    }

    public void createDays() {
        monday = new Monday(lessons);
        tuesday = new Tuesday(lessons);
        wednesday = new Wednesday(lessons);
        thursday = new Thursday(lessons);
        friday = new Friday(lessons);
        saturday = new Saturday(lessons);
        lessons.forEach(lesson -> {
            while(lesson.getSubject().getLoad()!=0){
                monday.update(lessons);
                tuesday.update(lessons);
                wednesday.update(lessons);
                thursday.update(lessons);
                friday.update(lessons);
                saturday.update(lessons);
            }
        });
    }

    public String getGroup() {
        return group.get();
    }

}