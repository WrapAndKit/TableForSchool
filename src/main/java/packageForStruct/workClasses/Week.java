package packageForStruct.workClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageForStruct.Knowledge.Knowledge;

public class Week {

    private StringProperty group;
    private ObservableList<Lesson> lessons = FXCollections.observableArrayList();
    public Day[] days;

    public Week(String group) {
        TableOfLessons table = new TableOfLessons();
        this.group = new SimpleStringProperty(group);
        table.lessons.forEach(lesson -> {
            if(lesson.getGroup().equals(group)){
                this.lessons.add(lesson);
            }
        });
        this.days = createDays();
    }

    public Day[] createDays(){
        Day[] day = new Day[6];
        Integer maxLoad = Knowledge.getMaxLoadForDay();
        day[0] = new Day("Пн");
        day[1] = new Day("Вт");
        day[2] = new Day("Ср");
        day[3] = new Day("Чт");
        day[4] = new Day("Пт");
        day[5] = new Day("Сб");

        lessons.forEach(lesson ->{
            Integer load = 0;
            int i = 0;
            while (load != lesson.getSubject().getLoad()){
                if(i == 6) i = 0;
                if(day[i].getLessons().size()<maxLoad){
                    day[i].addLessons(lesson);
                    i++;
                    load++;
                }else{
                    i++;
                }
            }
        });
        return day;
    }
}
