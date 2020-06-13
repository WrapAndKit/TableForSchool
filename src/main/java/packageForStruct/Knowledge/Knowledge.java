package packageForStruct.Knowledge;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.controllers.SQLiteC;

public class Knowledge {

    private final SQLiteC controllerKn;

    private static ObservableList<String> listOfSubjectKn;
    private static Integer gradesAmount;
    private static Integer maxLoadForDay;
    private static Integer minLoadTeacher;
    private static Integer maxLoadTeacher;
    private static Integer minCountTeacher;
    private static Integer maxCountTeacher;
    private static Integer maxCountSubForTeacher;
    private static Integer minCountWeeks;
    private static Integer maxCountWeeks;
    private static ObservableList<GroupK> groupsKn;
    private static ObservableList<SubjectK> subjectsKn;

    public Knowledge() {
        this.controllerKn = new SQLiteC("C:\\Users\\Progy\\IdeaProjects\\TableForSchool\\src\\main\\sqlite\\knowledge.sqlite3");
        gradesAmount = setGradesAmount();
        maxLoadForDay = setMaxLoadForDay();
        minLoadTeacher = setMinLoadTeacher();
        maxLoadTeacher = setMaxLoadTeacher();
        minCountTeacher = setMinCountTeacher();
        maxCountTeacher = setMaxCountTeacher();
        maxCountSubForTeacher = setMaxCountSubForTeacher();
        minCountWeeks = setMinCountWeeks();
        maxCountWeeks = setMaxCountWeeks();
        listOfSubjectKn = setListOfSubjectKn();
        groupsKn = setGroupsKn();
        subjectsKn = setSubjectsKn();
    }

    private Integer setGradesAmount(){
        controllerKn.connect();
        ObservableList<String> amount = controllerKn.queryFirstRaw("grades_amount");
        controllerKn.disconnect();
        int a = (int) 'А';
        return (int)amount.get(0).charAt(0) - a + 1;
    }

    private Integer setMaxLoadForDay(){
        controllerKn.connect();
        ObservableList<String> amount = controllerKn.queryFirstRaw("subjects_hoursPerDay");
        controllerKn.disconnect();
        return Integer.parseInt(amount.get(0));
    }

    private Integer setMinLoadTeacher(){
        controllerKn.connect();
        ObservableList<String> amount = controllerKn.queryFirstRaw("teachers_hoursPerWeek");
        controllerKn.disconnect();
        return Integer.parseInt(amount.get(0));
    }

    private Integer setMaxLoadTeacher(){
        controllerKn.connect();
        ObservableList<String> amount = controllerKn.queryFirstRaw("teachers_hoursPerWeek");
        controllerKn.disconnect();
        return Integer.parseInt(amount.get(1));
    }

    private Integer setMinCountTeacher(){
        controllerKn.connect();
        ObservableList<String> amount = controllerKn.queryFirstRaw("teachers_amount");
        controllerKn.disconnect();
        return Integer.parseInt(amount.get(0));
    }

    private Integer setMaxCountTeacher(){
        controllerKn.connect();
        ObservableList<String> amount = controllerKn.queryFirstRaw("teachers_amount");
        controllerKn.disconnect();
        return Integer.parseInt(amount.get(1));
    }

    private Integer setMaxCountSubForTeacher(){
        controllerKn.connect();
        ObservableList<String> amount = controllerKn.queryFirstRaw("teachers_subjectsAmount");
        controllerKn.disconnect();
        return Integer.parseInt(amount.get(0));
    }

    private Integer setMinCountWeeks(){
        controllerKn.connect();
        ObservableList<String> amount = controllerKn.queryFirstRaw("weeks");
        controllerKn.disconnect();
        return Integer.parseInt(amount.get(0));
    }

    private Integer setMaxCountWeeks(){
        controllerKn.connect();
        ObservableList<String> amount = controllerKn.queryFirstRaw("weeks");
        controllerKn.disconnect();
        return Integer.parseInt(amount.get(1));
    }

    private ObservableList<String> setListOfSubjectKn(){
        controllerKn.connect();
        ObservableList<ObservableList> subjects = controllerKn.queryRows("subjects_hoursPerWeek");
        controllerKn.disconnect();
        ObservableList<String> names = FXCollections.observableArrayList();
        subjects.forEach(subject -> names.add(subject.get(0).toString()));
        return names;
    }

    private ObservableList<SubjectK> setSubjectsKn(){
        controllerKn.connect();
        ObservableList<ObservableList> subjects = controllerKn.queryRows("subjects_hoursPerWeek");
        controllerKn.disconnect();
        ObservableList<SubjectK> result = FXCollections.observableArrayList();
        subjects.forEach(subject -> result.add(new SubjectK(subject.get(0).toString(), subject.get(1).toString(), subject.get(2).toString())));
        return result;
    }

    private ObservableList<GroupK> setGroupsKn(){
        controllerKn.connect();
        ObservableList<ObservableList> subjects = controllerKn.queryRows("grades_hoursPerWeek");
        controllerKn.disconnect();
        ObservableList<GroupK> result = FXCollections.observableArrayList();
        subjects.forEach(subject -> result.add(new GroupK(subject.get(0).toString(), subject.get(1).toString(), subject.get(2).toString())));
        return result;
    }

    public static ObservableList<String> getListOfSubjectKn() {
        return listOfSubjectKn;
    }

    public static Integer getGradesAmount() {
        return gradesAmount;
    }

    public static Integer getMaxLoadForDay() {
        return maxLoadForDay;
    }

    public static Integer getMinLoadTeacher() {
        return minLoadTeacher;
    }

    public static Integer getMaxLoadTeacher() {
        return maxLoadTeacher;
    }

    public static Integer getMinCountTeacher() {
        return minCountTeacher;
    }

    public static Integer getMaxCountTeacher() {
        return maxCountTeacher;
    }

    public static Integer getMaxCountSubForTeacher() {
        return maxCountSubForTeacher;
    }

    public static Integer getMinCountWeeks() {
        return minCountWeeks;
    }

    public static Integer getMaxCountWeeks() {
        return maxCountWeeks;
    }

    public static ObservableList<GroupK> getGroupsKn() {
        return groupsKn;
    }

    public static ObservableList<SubjectK> getSubjectsKn() {
        return subjectsKn;
    }
}
