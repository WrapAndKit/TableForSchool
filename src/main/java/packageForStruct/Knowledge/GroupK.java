package packageForStruct.Knowledge;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GroupK {

    private StringProperty name;
    private IntegerProperty minLoadWeek;
    private IntegerProperty maxLoadWeek;


    public GroupK(String name, String minLoad, String maxLoad){
        this.name = new SimpleStringProperty(name);
        this.minLoadWeek = new SimpleIntegerProperty(Integer.parseInt(minLoad));
        this.maxLoadWeek = new SimpleIntegerProperty(Integer.parseInt(maxLoad));
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getMinLoadWeek() {
        return minLoadWeek.get();
    }

    public void setMinLoadWeek(int minLoadWeek) {
        this.minLoadWeek.set(minLoadWeek);
    }

    public int getMaxLoadWeek() {
        return maxLoadWeek.get();
    }

    public void setMaxLoadWeek(int maxLoadWeek) {
        this.maxLoadWeek.set(maxLoadWeek);
    }

}
