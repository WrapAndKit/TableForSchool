package packageForStruct.workClasses.Designing.DayOfWeek;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.Knowledge.Knowledge;
import packageForStruct.workClasses.Designing.Lesson;
import packageForStruct.workClasses.Designing.TableForLessons;
import packageForStruct.workClasses.Variables;

import java.util.concurrent.atomic.AtomicReference;

public class Friday extends Day {

    public static ObservableList<String> busyTeachers = FXCollections.observableArrayList();
    public static ObservableList<TableForLessons> tableForLessons = FXCollections.observableArrayList();

    public Friday(){
        if(tableForLessons.size() == 0)
        Variables.groups.forEach(group -> {
            for (int i = 0; i < group.getCount(); i++) {
                tableForLessons.add(new TableForLessons("" + group.getName() + "-" + i));
            }
        });
    }

    public static void addLesson(Lesson lesson){
        TableForLessons table = search(lesson.getGroup());
        for (StringProperty teacher : lesson.getTeachers()) {
            if (containsTeacher(teacher.getValue())) {
                table.addLesson(lesson, teacher.getValue());
                busyTeachers.add(teacher.getName());
                break;
            }
        }
    }

    public static Integer loadOfTeacher(String teacher, String group){
        return search(group).loadOfTeacher(teacher);
    }

    public static boolean containsTeacher(String teacher){
        Integer hoursPerDay = Knowledge.getMaxLoadForDay();
        AtomicReference<Integer> teachersLoad = new AtomicReference<>(0);
        busyTeachers.forEach(busy->{
            if(busy.equals(teacher)) teachersLoad.getAndSet(teachersLoad.get() + 1);
        });
        return teachersLoad.get()<hoursPerDay;
    }

    public static Integer countOfLessons(String group){
        return search(group).countOfLessons();
    }

    private static TableForLessons search(String group){
        final TableForLessons[] result = new TableForLessons[1];
        tableForLessons.forEach(table ->{
            if(table.groupName.equals(group)) result[0] = table;
        });
        return result[0];
    }

    public String toString(){
        final String[] result = {"Пятница \n"};
        tableForLessons.forEach(table ->{
            result[0] += "Группа: " + table.groupName + "\n";
            table.lessons.forEach(lesson -> {
                result[0]+=lesson.toString();
            });
            if(result[0].equals("Группа: " + table.groupName + "\n")) result[0]="";
        });
        return result[0];
    }
}
