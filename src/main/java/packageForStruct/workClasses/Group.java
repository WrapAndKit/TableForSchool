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

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public int getCount() {
        return count.get();
    }

    public IntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public String toString(){
        return "There are " + getCount() + " of " + getNumber() + " groups.";
    }
}

