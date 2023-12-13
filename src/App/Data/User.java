package Data;

public class User {
    int x;
    int y;
    int hoursRented;

    public User(int x, int y) {
        this.x = x;
        this.y = y;
        this.hoursRented = 0;
    }

    public String toString() {
        String userString = "x-value: " + x + "\ny-value: " + y + "\nhoursRented: " + hoursRented;
        return userString;
    }

    public int[] getIntValues() {
        int[] intValues = { x, y, hoursRented };
        return intValues;
    }
}
