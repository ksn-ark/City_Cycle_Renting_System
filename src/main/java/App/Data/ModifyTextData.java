package App.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ModifyTextData {

    static public void replace(List<Cycle> modifiedCycles, String filePath) throws Exception {
        // replaces all of the text file except header with modified data
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String header = reader.readLine();

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write(header + "\n");

            writer.close();

            for (Cycle cycle : modifiedCycles) {
                add(filePath, cycle);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    static public void add(String filePath, Cycle cycle) throws Exception {

        // adds the Cycle object as an entry to the txt file

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            String newcycletxt = cycle.toTextString();

            writer.write(newcycletxt);

            writer.close();

        } catch (Exception e) {
            System.out.println("add crash");
            throw e;
        }
    }

    static public void addTopId(String filePath) throws Exception {

        // adds 1 to the id value in the header of the txt, it will be used as the id
        // for a new cycle when it is added

        try {

            List<String> data = new ArrayList<>();

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;

            while ((line = reader.readLine()) != null) {
                data.add(line);
            }

            reader.close();

            String[] modifiedHeader = data.get(0).split(",");

            int topId = Integer.parseInt(modifiedHeader[0]);

            modifiedHeader[0] = Integer.toString(topId + 1);

            String modifiedHeaderString = "";

            for (String string : modifiedHeader) {
                modifiedHeaderString += string + ",";
            }

            modifiedHeaderString = modifiedHeaderString.substring(0, modifiedHeaderString.length() - 1);

            data.set(0, modifiedHeaderString);

            String newData = "";

            for (String lines : data) {
                newData += lines + "\n";
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write(newData);

            writer.close();

        } catch (Exception e) {
            System.out.println("addTopId crash");
            throw e;
        }
    }

}
