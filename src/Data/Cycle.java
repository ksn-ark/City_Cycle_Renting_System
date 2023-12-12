package Data;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

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
        String output = "id : " + id + "\nx-value : " + x + "\ny-value : " + y + "\nHoursRented : " + hoursRented
                + "\nRented Status : " + isRented;
        return output;
    }

    public void add(String filePath) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
            String[] cyclecsv = { Integer.toString(id), Integer.toString(x), Integer.toString(y),
                    Integer.toString(hoursRented), Boolean.toString(isRented) };
            writer.writeNext(cyclecsv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
