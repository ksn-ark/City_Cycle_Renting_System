package App.Data;

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

    public void setX(int newx) {
        x = newx;
    }

    public int getY() {
        return y;
    }

    public void setY(int newy) {
        y = newy;
    }

    public int getHoursRented() {
        return hoursRented;
    }

    public void setHoursRented(int newHoursRented) {
        hoursRented = newHoursRented;
    }

    public List<Cycle> getUserRentedCycles() {
        return userRentedCycles;
    }

    public void setUserRentedCycles(List<Cycle> newUserRentedCycles) {
        userRentedCycles = newUserRentedCycles;
    }

}
