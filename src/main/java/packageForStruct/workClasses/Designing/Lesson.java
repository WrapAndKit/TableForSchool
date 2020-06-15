package packageForStruct.workClasses.Designing;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.workClasses.Teacher;

public class Lesson {

    private StringProperty subject;
    private IntegerProperty loadOfSubject;

    private StringProperty group;

    private BooleanProperty isTeacher;

    private ObservableList<StringProperty> teachers = FXCollections.observableArrayList();
    private ObservableList<IntegerProperty> loadOfTeachers = FXCollections.observableArrayList();
    private ObservableList<StringProperty> teachersClassrooms = FXCollections.observableArrayList();

    public Lesson(){
        this.group = new SimpleStringProperty();
        this.subject = new SimpleStringProperty();
        this.loadOfSubject = new SimpleIntegerProperty();
        this.isTeacher = new SimpleBooleanProperty(false);
    }

    public Lesson(String group, String subject, Integer loadOfSubject){
        this.group = new SimpleStringProperty(group);
        this.subject = new SimpleStringProperty(subject);
        this.loadOfSubject = new SimpleIntegerProperty(loadOfSubject);
        this.isTeacher = new SimpleBooleanProperty(false);
    }

    public Lesson(Lesson lesson){
        this.group = new SimpleStringProperty(lesson.getGroup());
        this.subject = new SimpleStringProperty(lesson.getSubject());
        this.loadOfSubject = new SimpleIntegerProperty(lesson.getLoadOfSubject());
        this.isTeacher = new SimpleBooleanProperty(lesson.isIsTeacher());
        this.teachers = lesson.getTeachers();
        this.loadOfTeachers = lesson.getLoadOfTeachers();
        this.teachersClassrooms = lesson.getTeachersClassrooms();
    }

    public Lesson(Lesson lesson,String teacher){
        this.group = new SimpleStringProperty(lesson.getGroup());
        this.subject = new SimpleStringProperty(lesson.getSubject());
        this.loadOfSubject = new SimpleIntegerProperty(lesson.getLoadOfSubject());
        this.isTeacher = new SimpleBooleanProperty(lesson.isIsTeacher());
        final int[] i = {-1};
        lesson.getTeachers().forEach(t ->{
            i[0]++;
            if(t.getValue().equals(teacher)){
                this.teachers.add(lesson.teachers.get(i[0]));
                this.loadOfTeachers.add(lesson.loadOfTeachers.get(i[0]));
                this.teachersClassrooms.add(lesson.teachersClassrooms.get(i[0]));
            }
        });

    }

    public void addTeacher(Teacher teacher, Integer load){
        teachers.add(new SimpleStringProperty(teacher.getName()));
        loadOfTeachers.add(new SimpleIntegerProperty(load));
        teachersClassrooms.add(new SimpleStringProperty(teacher.getClassroom()));
    }

    public String getSubject() {
        return subject.get();
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public int getLoadOfSubject() {
        return loadOfSubject.get();
    }

    public IntegerProperty loadOfSubjectProperty() {
        return loadOfSubject;
    }

    public void setLoadOfSubject(int loadOfSubject) {
        this.loadOfSubject.set(loadOfSubject);
    }

    public String getGroup() {
        return group.get();
    }

    public StringProperty groupProperty() {
        return group;
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public ObservableList<StringProperty> getTeachers() {
        return teachers;
    }

    public void setTeachers(ObservableList<StringProperty> teachers) {
        this.teachers = teachers;
    }

    public ObservableList<IntegerProperty> getLoadOfTeachers() {
        return loadOfTeachers;
    }

    public void setLoadOfTeachers(ObservableList<IntegerProperty> loadOfTeachers) {
        this.loadOfTeachers = loadOfTeachers;
    }

    public ObservableList<StringProperty> getTeachersClassrooms() {
        return teachersClassrooms;
    }

    public void setTeachersClassrooms(ObservableList<StringProperty> teachersClassrooms) {
        this.teachersClassrooms = teachersClassrooms;
    }

    public boolean isIsTeacher() {
        return isTeacher.get();
    }

    public BooleanProperty isTeacherProperty() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher.set(isTeacher);
    }

    public String toString(){
        String teacherString = "";
        for (int i = 0; i < teachers.size(); i++) {
            teacherString += "преподаватель - " + teachers.get(i).getValue() + " аудитория -" +teachersClassrooms.get(i).getValue() +
                    " нагрузка - " + loadOfTeachers.get(i).getValue() + "\n";
        }
        return "Предмет - " + subject.getValue() + " группа - " + group.getValue() +
                " нагрузка для предмета - " + loadOfSubject.getValue() + ", преподаватели \n" + teacherString;
    }
}
