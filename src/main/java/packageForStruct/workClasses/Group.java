package packageForStruct.workClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Group {

    private StringProperty name;
    private IntegerProperty count;
    private IntegerProperty minLoad;
    private IntegerProperty maxLoad;

    public Group(String name, int count){
        this.name = new SimpleStringProperty(name);
        this.count = new SimpleIntegerProperty(count);
        this.minLoad = new SimpleIntegerProperty();
        this.maxLoad = new SimpleIntegerProperty();
    }

    public String getNumber() {
        return name.get();
    }

    public int getCount() {
        return count.get();
    }

    public int getMinLoad() {
        return minLoad.get();
    }

    public void setMinLoad(int minLoad) {
        this.minLoad.set(minLoad);
    }

    public int getMaxLoad() {
        return maxLoad.get();
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad.set(maxLoad);
    }

    public String toString(){
        return "There are " + getCount() + " of " + getNumber() + " groups.";
    }
}

