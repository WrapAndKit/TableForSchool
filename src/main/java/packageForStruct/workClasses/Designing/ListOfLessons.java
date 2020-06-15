package packageForStruct.workClasses.Designing;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.workClasses.Subject;
import packageForStruct.workClasses.Teacher;
import packageForStruct.workClasses.Variables;

public class ListOfLessons {

    ObservableList<Lesson> lessons = FXCollections.observableArrayList();
    private ObservableList<Teacher> busyTeachers = FXCollections.observableArrayList();

    public ListOfLessons(){
        construct();
    }

    private void construct(){
        Variables.groups.forEach(group -> {
            for (int i = 0; i < group.getCount(); i++) {
                String groupName = "" + group.getName() + "-" + i;
                subjectsForGroup(group.getName()).forEach(subject -> {
                    lessons.add(new Lesson(groupName,subject.getName(),subject.getLoad()));
                });
            }
        });
        lessons.forEach(lesson -> {
            teachersForSubject(lesson.getSubject()).forEach(teacher -> {
                Integer time = Integer.parseInt(teacher.getLoad()) - teacher.getBusy();
                if(!busyTeachers.contains(teacher))
                    if((lesson.getLoadOfSubject() <= time)&&(!lesson.isIsTeacher())){
                        teacher.setBusy(teacher.getBusy() + lesson.getLoadOfSubject());
                        lesson.addTeacher(teacher, lesson.getLoadOfSubject());
                        if(teacher.getBusy().equals(Integer.parseInt(teacher.getLoad())))
                            busyTeachers.add(teacher);
                        lesson.setIsTeacher(true);
                    }
            });
        });

        lessons.forEach(lesson -> {
            if(!lesson.isIsTeacher()){
                teachersForSubject(lesson.getSubject()).forEach(teacher -> {
                    Integer time = Integer.parseInt(teacher.getLoad()) - teacher.getBusy();
                    if (!busyTeachers.contains(teacher))
                        if(time <= lessonLoad(lesson)){
                            teacher.setBusy(teacher.getBusy()+time);
                            busyTeachers.add(teacher);
                            lesson.addTeacher(teacher,time);
                            if(lessonLoad(lesson).equals(0)) lesson.setIsTeacher(true);
                        }
                });
            }
        });
    }

    private Integer lessonLoad(Lesson lesson){
        Integer load = 0;
        for (IntegerProperty l : lesson.getLoadOfTeachers()) {
            load += l.getValue();
        }
        return lesson.getLoadOfSubject()-load;
    }

    private ObservableList<Subject> subjectsForGroup(String group){
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        Variables.subjects.forEach(subject -> {
            if(subject.getGroupName().equals(group)) subjects.add(new Subject(subject));
        });
        return subjects;
    }

    private ObservableList<Teacher> teachersForSubject(String subject){
        ObservableList<Teacher> teachers = FXCollections.observableArrayList();
        Variables.teachers.forEach(teacher -> {
            if(teacher.getSubject().equals(subject)) teachers.add(teacher);
        });
        return teachers;
    }

}
