package App.Data;

public class Cycle {

    int id;
    int x;
    int y;
    int hoursRented;
    boolean isRented;

    static String[] properties = { "id", "x", "y", "hoursRented" };

    public Cycle(int id, int x, int y, int hoursRented, boolean isRented) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.hoursRented = hoursRented;
        this.isRented = isRented;
    }

    public String toString() {
        String output = "\nid : " + id + "\nx-value : " + x + "\ny-value : " + y + "\nHours Rented : " + hoursRented
                + "\nRented Status : " + isRented;
        return output;
    }

    public String toTextString() {
        String output = Integer.toString(id) + "," + Integer.toString(x) + "," + Integer.toString(y) + "," +
                Integer.toString(hoursRented) + "," + Boolean.toString(isRented) + "\n";
        return output;
    }

    public int[] getIntValues() {

        int intValues[] = { id, x, y, hoursRented };

        return intValues;
    }

    public boolean[] getBoolValues() {
        boolean booleanValues[] = { isRented };
        return booleanValues;
    }

    public int getId() {
        return id;
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

    public boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(boolean newIsRented) {
        isRented = newIsRented;
    }
}
