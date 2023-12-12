package Data;

import com.opencsv.*;

import java.io.*;
import java.util.List;

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

    public void add(String filePath) throws Exception {

        try {

            CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));

            String[] newcyclecsv = { Integer.toString(id), Integer.toString(x), Integer.toString(y),
                    Integer.toString(hoursRented), Boolean.toString(isRented) };

            writer.writeNext(newcyclecsv);

            writer.close();

        } catch (Exception e) {
            System.out.println("add crash");
            throw e;
        }
    }

    public void addTopId(String filePath, int topId) throws Exception {
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));

            List<String[]> data = reader.readAll();

            String[] modifiedHeader = data.get(0);

            modifiedHeader[0] = Integer.toString(topId + 1);

            data.set(0, modifiedHeader);

            CSVWriter writer = new CSVWriter(new FileWriter(filePath));

            writer.writeAll(data);

            writer.close();
        } catch (Exception e) {
            System.out.println("addTopId crash");
            throw e;
        }
    }

    public void replace(List<Cycle> cycles, String filePath) throws Exception {
        try {

            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] header = reader.readNext();

            System.out.println(header);

            CSVWriter writer = new CSVWriter(new FileWriter(filePath));

            writer.writeNext(header);
            writer.close();

            for (Cycle cycle : cycles) {
                cycle.add(filePath);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public int[] getIntValues() {

        int intValues[] = { id, x, y, hoursRented };

        return intValues;
    }

    public boolean[] getBoolValues() {
        boolean booleanValues[] = { isRented };
        return booleanValues;
    }
}
