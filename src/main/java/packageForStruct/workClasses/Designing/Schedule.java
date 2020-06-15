package packageForStruct.workClasses.Designing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.workClasses.Variables;

public class Schedule {

    public ObservableList<String> groupNames = FXCollections.observableArrayList();
    private ListOfLessons list;
    public ObservableList<Week> weeks = FXCollections.observableArrayList();

    public Schedule(){
        this.list = new ListOfLessons();
        fillNames();
        groupNames.forEach(group ->{
            weeks.add(new Week(group, listForGroup(group)));
        });

        System.out.println(weeks.get(0).monday + "\n");
        System.out.println(weeks.get(0).tuesday + "\n");
        System.out.println(weeks.get(0).wednesday + "\n");
        System.out.println(weeks.get(0).thursday + "\n");
        System.out.println(weeks.get(0).friday + "\n");
        System.out.println(weeks.get(0).saturday + "\n");
    }

    private void fillNames(){
        Variables.groups.forEach(group -> {
            for (int i = 0; i < group.getCount(); i++) {
                groupNames.add("" + group.getName() + "-" + i);
            }
        });
    }

    private ObservableList<Lesson> listForGroup(String group){
        ObservableList<Lesson> result = FXCollections.observableArrayList();

        list.lessons.forEach(lesson -> {
            if(lesson.getGroup().equals(group)) result.add(new Lesson(lesson));
        });

        return result;
    }
}
