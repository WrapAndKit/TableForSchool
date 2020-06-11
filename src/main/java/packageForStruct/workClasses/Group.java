package packageForStruct.workClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Group {
    private IntegerProperty number;
    private IntegerProperty count;

    public Group(int number, int count){
        this.number = new SimpleIntegerProperty(number);
        this.count = new SimpleIntegerProperty(count);
    }

    public int getNumber() {
        return number.get();
    }

    public int getCount() {
        return count.get();
    }

    public String toString(){
        return "There are " + getCount() + " of " + getNumber() + " groups.";
    }
}

