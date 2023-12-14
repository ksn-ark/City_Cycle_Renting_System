package Data;

import java.util.ArrayList;
import java.util.List;

public class User {

    int x;
    int y;
    int hoursRented;
    List<Cycle> userRentedCycles;

    public User(int x, int y) {
        this.x = x;
        this.y = y;
        this.hoursRented = 0;
        this.userRentedCycles = new ArrayList<>();
    }

    public String toString() {
        String userString = "x-value: " + x + "\ny-value: " + y + "\nhoursRented: " + hoursRented;
        return userString;
    }

    public int[] getIntValues() {
        int[] intValues = { x, y, hoursRented };
        return intValues;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHoursRented() {
        return hoursRented;
    }

    public List<Cycle> getUserRentedCycles() {
        return userRentedCycles;
    }
}
