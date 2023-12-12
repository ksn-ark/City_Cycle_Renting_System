package Data;

public class Cycle {

    int id;
    int x;
    int y;
    int hoursRented;
    boolean isRented;

    static String[] properties = { "id", "x", "y", "hoursRented" };

    public Cycle(int valueArgs[], boolean booleanArgs[], int id) {
        this.id = id;
        this.x = valueArgs[0];
        this.y = valueArgs[1];
        this.hoursRented = valueArgs[2];
        this.isRented = booleanArgs[0];
    }

    public String toString() {
        String output = "id : " + id + "\nx-value : " + x + "\ny-value : " + y + "\nHoursRented : " + hoursRented
                + "\nRented Status : " + isRented;
        return output;
    }

}
