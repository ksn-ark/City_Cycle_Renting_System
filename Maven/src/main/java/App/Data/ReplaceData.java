package App.Data;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import com.opencsv.*;

public class ReplaceData {
    public static void replace(List<Cycle> modifiedCycles, String filePath) throws Exception {
        try {

            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] header = reader.readNext();

            CSVWriter writer = new CSVWriter(new FileWriter(filePath));

            writer.writeNext(header);
            writer.close();

            for (Cycle cycle : modifiedCycles) {
                cycle.add(filePath);
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
