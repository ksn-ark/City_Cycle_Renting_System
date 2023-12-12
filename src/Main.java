import java.io.*;
import java.util.*;
import com.opencsv.*;

import CommandHandlers.InputCommand;
import Data.Cycle;

public class Main {
    static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {

        String filePath = "C:\\Files\\Github\\Repositories\\City_Cycle_Renting_System\\src\\Data\\Cycles.csv";

        while (true) {
            try {

                CSVReader reader = new CSVReader(new FileReader(filePath));

                List<String[]> rawRecords = reader.readAll();

                int topId = Integer.parseInt(rawRecords.get(0)[0]);

                List<Cycle> Cycles = readCycles(rawRecords);

                System.out.print("\nEnter Command :");

                List<String> commandInputList = new ArrayList<>();

                String[] userInput = scnr.nextLine().split(" ");

                for (String string : userInput) {
                    commandInputList.add(string);
                }

                while (commandInputList.size() < 3) {
                    commandInputList.add("");
                }

                String[] commandInputs = new String[3];

                for (int i = 0; i < commandInputs.length; i++) {
                    commandInputs[i] = commandInputList.get(i);
                }

                InputCommand.handler(commandInputs, topId, Cycles, filePath);

                continue;

            } catch (Exception error) {
                System.out.println("Invalid file");
                System.out.print("Enter valid csv file path or enter 'exit' to exit : ");
                if (scnr.nextLine() != "") {
                    filePath = scnr.nextLine();
                }
                if (filePath.equals("exit")) {
                    break;
                }
                continue;
            }
        }
    }

    static List<Cycle> readCycles(List<String[]> rawRecords) {

        boolean headerFlag = true;
        List<Cycle> Cycles = new ArrayList<>();

        for (String[] rawRecord : rawRecords) {
            if (headerFlag) {
                headerFlag = false;
                continue;
            }
            int id = Integer.parseInt(rawRecord[0]);
            int x = Integer.parseInt(rawRecord[1]);
            int y = Integer.parseInt(rawRecord[2]);
            int hoursRented = Integer.parseInt(rawRecord[3]);
            boolean isRented = Boolean.parseBoolean(rawRecord[4]);
            Cycles.add(new Cycle(id, x, y, hoursRented, isRented));
        }

        return Cycles;
    }

}
