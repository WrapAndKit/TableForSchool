package packageForStruct.workClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class TableOfLessons {

    static ObservableList<Teacher> busyTeachers = FXCollections.observableArrayList();
    ObservableList<Lesson> lessons;

    public TableOfLessons(){
        this.lessons = construct();
    }

    public ObservableList<Lesson> construct(){

        ObservableList<Lesson> result;
        ObservableList<Lesson> lessons = FXCollections.observableArrayList();
        //Перебираем все классы 5-11
        Variables.groups.forEach(group -> {
            //Находим предметы для каждого класса
            ObservableList<Subject> subjects = subjectsForGroup(group.getName());
            //Перебираем каждый класс(А, Б, ...)
            for (int i = 0; i < group.getCount(); i++) {
                String groupName = group.getName() + "-" + i;
                //Перебираем предметы и находим для них преподавателя
                subjects.forEach(subject -> {
                    //
                    lessons.add(new Lesson(groupName,subject));
                    //Назначен ли преподаватель предмету
                    AtomicBoolean isTeacher = new AtomicBoolean(false);
                    //Все преподаватели для предмета
                    ObservableList<Teacher> teachers = teachersForSubject(subject.getName());
                    //Пробегает всех преподавателей
                    teachers.forEach(teacher -> {
                        //Проверяем назначен ли преподаватель для предмета и занят ли данный преподаватель
                        if(!isTeacher.get() && !busyTeachers.contains(teacher)){
                            Integer time = Integer.parseInt(teacher.getLoad())-teacher.getBusy();
                            if(time >= subject.getLoad()){
                                isTeacher.set(true);
                                teacher.setBusy(teacher.getBusy()+subject.getLoad());
                                if(teacher.getBusy().equals(Integer.parseInt(teacher.getLoad())))
                                    busyTeachers.add(teacher);
                                lessons.get(lessons.size()-1).setTeacher(teacher);
                            }
                        }
                    });
                });
            }
        });
        lessons.forEach(lesson -> {
            if(lesson.getTeacher().size() == 0) {
                ObservableList<Teacher> teachers = teachersForSubject(lesson.getSubject().getName());
                AtomicReference<Integer> load = new AtomicReference<>(lesson.getSubject().getLoad());
                teachers.forEach(teacher -> {
                    if(load.get() != 0){
                        if(!busyTeachers.contains(teacher)){
                            Integer teacherTime = Integer.parseInt(teacher.getLoad()) - teacher.getBusy();
                            if(load.get() >= teacherTime){
                                load.updateAndGet(v -> v - (Integer.parseInt(teacher.getLoad()) - teacher.getBusy()));
                                teacher.setBusy(teacher.getBusy()+teacherTime);
                                lesson.setTeacher(teacher);
                                busyTeachers.add(teacher);
                            }else{
                                teacher.setBusy(teacher.getBusy()+load.get());
                                lesson.setTeacher(teacher);
                                load.set(0);
                            }
                        }
                    }
                });
            }
        });

        result = lessons;
        return result;
    }

    public ObservableList<Subject> subjectsForGroup(String group){
        ObservableList<Subject> result = FXCollections.observableArrayList();
        Variables.subjects.forEach(subject -> {
            if(subject.getGroupName().equals(group)) result.add(subject);
        });
        return result;
    }

    public ObservableList<Teacher> teachersForSubject(String subject){
        ObservableList<Teacher> result = FXCollections.observableArrayList();
        Variables.teachers.forEach(teacher -> {
            if(teacher.getSubject().equals(subject)) result.add(teacher);
        });
        return result;
    }
}
