package packageForStruct.workClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.Knowledge.GroupK;
import packageForStruct.Knowledge.Knowledge;
import packageForStruct.Knowledge.SubjectK;
import packageForStruct.controllers.SQLiteC;

public class Variables {

    private Knowledge knowledge;
    private final SQLiteC controllerDB;

    public static ObservableList<String> listOfGroups;
    public static ObservableList<String> listOfSubjects;
    public static ObservableList<Group> groups;
    public static ObservableList<Subject> subjects = FXCollections.observableArrayList();
    public static ObservableList<Teacher> teachers = FXCollections.observableArrayList();


    public Variables(){
        this.controllerDB = new SQLiteC();
        this.knowledge = new Knowledge();
        this.groups = groupsFilling();
        this.subjects = subjectsFilling();
        controllerDB.connect();
        this.listOfGroups = listOfGroupsFilling();
        controllerDB.disconnect();
        this.listOfSubjects = listOfSubjectsFilling();
        this.teachers = teachersFilling();
    }

    public void update(){
        this.knowledge = new Knowledge();
        this.groups = groupsFilling();
        this.subjects = subjectsFilling();
        controllerDB.connect();
        this.listOfGroups = listOfGroupsFilling();
        controllerDB.disconnect();
        this.listOfSubjects = listOfSubjectsFilling();
        this.teachers = teachersFilling();
    }

    private ObservableList<Group> groupsFilling(){
        ObservableList<Group> result= FXCollections.observableArrayList();

        controllerDB.connect();
        ObservableList<ObservableList> groupRows = controllerDB.queryRows("Классы");
        controllerDB.disconnect();

        groupRows.forEach(row -> result.add(new Group(row.get(0).toString(), Integer.parseInt(row.get(1).toString()))));

        ObservableList<GroupK> groupsKn = knowledge.getGroupsKn();

        result.forEach(group -> groupsKn.forEach(kn ->{
            if(kn.getName().equals(group.getName())) {
                group.setMinLoad(kn.getMinLoadWeek());
                group.setMaxLoad(kn.getMaxLoadWeek());
            }
        }));

        return result;
    }

    private ObservableList<Subject> subjectsFilling(){
        ObservableList<Subject> result= FXCollections.observableArrayList();

        controllerDB.connect();
        ObservableList<ObservableList> subjectRows = controllerDB.queryRows("Предметы");
        controllerDB.disconnect();

        subjectRows.forEach(row -> result.add(new Subject(row.get(1).toString(),Integer.parseInt(row.get(2).toString()), row.get(0).toString())));

        ObservableList<SubjectK> subjectsKn = knowledge.getSubjectsKn();

        result.forEach(subject -> subjectsKn.forEach(sb ->{
            if(subject.getName().equals(sb.getName())){
                subject.setMaxLoadWeek(sb.getMaxLoadWeek());
                subject.setMinLoadWeek(sb.getMinLoadWeek());
                subject.setMaxLoadDay(knowledge.getMaxLoadForDay());
            }
        }));

        return result;
    }

    private ObservableList<Teacher> teachersFilling(){
        ObservableList<Teacher> result= FXCollections.observableArrayList();

        controllerDB.connect();
        ObservableList<ObservableList> teacherRows = controllerDB.queryRows("Преподаватели");
        controllerDB.disconnect();

        teacherRows.forEach(row -> result.add(new Teacher(row.get(1).toString(),row.get(4).toString(), row.get(3).toString())));

        result.forEach(teacher -> {
            teacher.setMinLoadWeek(knowledge.getMinLoadTeacher());
            teacher.setMaxLoadWeek(knowledge.getMaxLoadTeacher());
            teacher.setMinCount(knowledge.getMinCountTeacher());
            teacher.setMaxCount(knowledge.getMaxCountTeacher());
        });

        return result;
    }

    private ObservableList<String> listOfSubjectsFilling(){
        return knowledge.getListOfSubjectKn();
    }

    private ObservableList<String> listOfGroupsFilling(){
        return controllerDB.queryColumnValue("Классы","Класс");
    }

    public static void deleteSubject(String name){
        subjects.forEach(subject -> {
            if(subject.getName().equals(name)) subject = null;
        });
    }
}
