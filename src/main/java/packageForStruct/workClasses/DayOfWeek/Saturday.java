package packageForStruct.workClasses.DayOfWeek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.Knowledge.Knowledge;
import packageForStruct.workClasses.Day;
import packageForStruct.workClasses.Lesson;
import packageForStruct.workClasses.Teacher;

import java.util.concurrent.atomic.AtomicReference;

public class Saturday extends Day {

    private static ObservableList<Teacher> teachers = FXCollections.observableArrayList();

    public Integer load;

    public Saturday(ObservableList<Lesson> lessons) {
        super(lessons.get(0).getGroup(), "Сб");
        load = 0;
        lessons.forEach(lesson -> {
            Integer maxLoad = Knowledge.getMaxLoadForDay();
            Integer subjectLoad = lesson.getSubject().getLoad();
            if (load < maxLoad)
                if (subjectLoad > 0) {
                    for (Teacher teacher : lesson.getTeacher()) {
                        if ((countOfTeachers(teacher) < maxLoad)) {
                            addLessons(lesson);
                            lesson.getSubject().setLoad(subjectLoad - 1);
                            load++;
                        }
                        break;
                    }
                }
        });
    }


    public void update(ObservableList<Lesson> lessons){
        lessons.forEach(lesson -> {
            Integer maxLoad = Knowledge.getMaxLoadForDay();
            Integer subjectLoad = lesson.getSubject().getLoad();
            if (load < maxLoad)
                if (subjectLoad > 0) {
                    for (Teacher teacher : lesson.getTeacher()) {
                        if ((countOfTeachers(teacher) < maxLoad)) {
                            addLessons(lesson);
                            lesson.getSubject().setLoad(subjectLoad - 1);
                            load++;
                        }
                        break;
                    }
                }
        });
    }

    @Override
    public void addLessons(Lesson lesson) {
        super.addLessons(lesson);
        lesson.getTeacher().forEach(teacher -> {
            teachers.add(teacher);
        });
        load++;
    }

    public static Integer countOfTeachers(Teacher teacher){
        AtomicReference<Integer> result = new AtomicReference<>(0);
        teachers.forEach(t ->{
            if(t.getName().equals(teacher.getName())) result.getAndSet(result.get() + 1);
        });
        return result.get();
    }
}
