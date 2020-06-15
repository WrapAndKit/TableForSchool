package packageForStruct.workClasses.Designing;

import javafx.collections.ObservableList;
import packageForStruct.Knowledge.Knowledge;
import packageForStruct.workClasses.Designing.DayOfWeek.*;

public class Week {

    public Monday monday;
    public Tuesday tuesday;
    public Wednesday wednesday;
    public Thursday thursday;
    public Friday friday;
    public Saturday saturday;

    public Week(String group, ObservableList<Lesson> lessons){

        this.monday = new Monday();
        this.tuesday = new Tuesday();
        this.wednesday = new Wednesday();
        this.thursday = new Thursday();
        this.friday = new Friday();
        this.saturday = new Saturday();
        Integer maxHours = Knowledge.getMaxLoadForDay();

        lessons.forEach(lesson -> {
            final Integer[] load = {0};
            Boolean[] day = {true,true,true,true,true,true};
            while(lesson.getLoadOfSubject() != load[0]){
                lesson.getTeachers().forEach(teacher ->{
                    Integer i = lesson.getTeachers().indexOf(teacher);
                    if(monday.containsTeacher(teacher.getValue()) && day[0]){
                        if(monday.countOfLessons(group) < maxHours){
                            if(monday.loadOfTeacher(teacher.getName(), group) < lesson.getLoadOfTeachers().get(i).getValue()){
                                monday.addLesson(lesson);
                                load[0]++;
                                day[0]=false;
                            }else day[0]=false;
                        }else day[0]=false;
                    }
                    else if(tuesday.containsTeacher(teacher.getValue())&& day[1]){
                        if(tuesday.countOfLessons(group) < maxHours){
                            if(tuesday.loadOfTeacher(teacher.getName(), group) < lesson.getLoadOfTeachers().get(i).getValue()){
                                tuesday.addLesson(lesson);
                                load[0]++;
                                day[1]=false;
                            }else day[1]=false;
                        }else day[1]=false;
                    }
                    else if(wednesday.containsTeacher(teacher.getValue())&& day[2]){
                        if(wednesday.countOfLessons(group) < maxHours){
                            if(wednesday.loadOfTeacher(teacher.getName(), group) < lesson.getLoadOfTeachers().get(i).getValue()){
                                wednesday.addLesson(lesson);
                                load[0]++;
                                day[2]=false;
                            }else day[2]=false;
                        }else day[2]=false;
                    }
                    else if(thursday.containsTeacher(teacher.getValue())&& day[3]){
                        if(thursday.countOfLessons(group) < maxHours){
                            if(thursday.loadOfTeacher(teacher.getName(), group) < lesson.getLoadOfTeachers().get(i).getValue()){
                                thursday.addLesson(lesson);
                                load[0]++;
                                day[3]=false;
                            }else day[3]=false;
                        }else day[3]=false;
                    }
                    else if(friday.containsTeacher(teacher.getValue())&& day[4]){
                        if(friday.countOfLessons(group) < maxHours){
                            if(friday.loadOfTeacher(teacher.getName(), group) < lesson.getLoadOfTeachers().get(i).getValue()){
                                friday.addLesson(lesson);
                                load[0]++;
                                day[4]=false;
                            }else day[4]=false;
                        }else day[4]=false;
                    }
                    else if(saturday.containsTeacher(teacher.getValue())&& day[5]){
                        if(saturday.countOfLessons(group) < maxHours){
                            if(saturday.loadOfTeacher(teacher.getName(), group) < lesson.getLoadOfTeachers().get(i).getValue()){
                                saturday.addLesson(lesson);
                                load[0]++;
                                day[0] = true;
                                day[1] = true;
                                day[2] = true;
                                day[3] = true;
                                day[4] = true;
                            }else {
                                day[0] = true;
                                day[1] = true;
                                day[2] = true;
                                day[3] = true;
                                day[4] = true;
                            }
                        }else {
                            day[0] = true;
                            day[1] = true;
                            day[2] = true;
                            day[3] = true;
                            day[4] = true;
                        }
                    }
                });
            }
        });

        this.monday.refactor();


    }


}
