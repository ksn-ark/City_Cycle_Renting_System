
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.*;

import CommandHandlers.InputCommand;
import Data.AppData;
import Data.Cycle;
import Data.User;
import Commands.OnStart.SetRentPerHour;
import Commands.OnStart.InitialUserLocation;

public class Main {

    static Scanner scnr = new Scanner(System.in);

    static int topId;

    static String filePath;

    static User user;

    static float rentPerHour;

    static List<Cycle> cycles;

    public static void main(String[] args) throws FileNotFoundException {

        filePath = "C:\\Files\\Github\\Repositories\\City_Cycle_Renting_System\\src\\App\\Data\\Cycles.csv";

        int[] userPos = InitialUserLocation.execute();

        user = new User(userPos[0], userPos[1]);

        System.out.println(user.toString());

        rentPerHour = SetRentPerHour.execute();

        while (true) {
            try {

                CSVReader reader = new CSVReader(new FileReader(filePath));

                List<String[]> rawRecords = reader.readAll();

                topId = Integer.parseInt(rawRecords.get(0)[0]);

                cycles = readCycles(rawRecords);

                AppData data = new AppData(topId, filePath, user, rentPerHour, cycles);

                InputCommand inputCommand = new InputCommand();

                inputCommand.handler(data);

            } catch (Exception error) {
                System.out.println("Invalid file");
                System.out.print("Enter valid csv file path or enter 'exit' to exit : ");
                if (scnr.nextLine() != "") {
                    continue;
                }
                if (filePath.equals("exit")) {
                    System.exit(0);
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

    public int getTopID() {
        return topId;
    }

    public String getfilePath() {
        return filePath;
    }

    public User getUser() {
        return user;
    }

    public float getRentPerHour() {
        return rentPerHour;
    }

    public List<Cycle> cycles() {
        return cycles;
    }
}
