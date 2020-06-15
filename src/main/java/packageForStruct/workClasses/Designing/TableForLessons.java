package packageForStruct.workClasses.Designing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableForLessons {

    public String groupName;
    public ObservableList<Lesson> lessons;

    public TableForLessons(String groupName){
        this.groupName = groupName;
        lessons = FXCollections.observableArrayList();
    }

    public Integer countOfLessons(){
        return this.lessons.size();
    }

    public void addLesson(Lesson lesson, String teacher){
        this.lessons.add(new Lesson(lesson, teacher));
    }

    public Integer loadOfTeacher(String teacher){
        final Integer[] load = {0};
        lessons.forEach(lesson -> {
            if(lesson != null)
            if(lesson.getTeachers().size()>0)
            if(lesson.getTeachers().get(0).getValue().equals(teacher)) load[0]++;
        });
        return load[0];
    }
}
