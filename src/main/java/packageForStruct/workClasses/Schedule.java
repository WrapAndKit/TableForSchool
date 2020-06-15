package packageForStruct.workClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Schedule {
    public ObservableList<Week> schedule = FXCollections.observableArrayList();

    public Schedule(){
        ObservableList<String> nameOfGroups = FXCollections.observableArrayList();
        Variables.groups.forEach(group -> {
            for (int i = 0; i < group.getCount(); i++) {
                nameOfGroups.add(group.getName()+"-"+i);
            }
        });
        nameOfGroups.forEach(group ->{
            schedule.add(new Week(group));
        });
    }

    public void soutSchedule(){
        schedule.forEach(week -> {
            System.out.println(week.getGroup());
            System.out.println(week.monday);
            System.out.println(week.tuesday);
            System.out.println(week.wednesday);
            System.out.println(week.thursday);
            System.out.println(week.friday);
            System.out.println(week.saturday);
        });
    }
}
