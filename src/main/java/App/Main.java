package App;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import App.CommandHandlers.InputCommand;
import App.Data.AppData;
import App.Data.Cycle;
import App.Data.User;
import App.Commands.OnStart.SetRentPerHour;
import App.Commands.OnStart.InitialUserLocation;

public class Main {

    static Scanner scnr = new Scanner(System.in);

    static int topId;

    static String filePath;

    static User user;

    static float rentPerHour;

    static List<Cycle> cycles;

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_CYAN = " \u001B[36m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_BLUE = " \u001B[34m";

    public static void main(String[] args) throws FileNotFoundException {

        filePath = "./Storage/Cycles.txt";

        System.out.println(ANSI_CYAN + "\n\n\tInitializing....,\n\t\t" + ANSI_RESET + ANSI_GREEN
                + "Enter user & renting rate details.\n" + ANSI_RESET);

        int[] userPos = InitialUserLocation.execute();

        user = new User(userPos[0], userPos[1]);

        System.out.println(user.toString());

        rentPerHour = SetRentPerHour.execute();

        while (true) {
            try {

                List<String[]> rawRecords = readFile(filePath);

                topId = Integer.parseInt(rawRecords.get(0)[0]);

                cycles = readCycles(rawRecords);

                AppData data = new AppData(topId, filePath, user, rentPerHour, cycles);

                InputCommand inputCommand = new InputCommand();

                System.out
                        .println(ANSI_CYAN + "\n\tJava based Cycle Renting & DataBase System (J-CRDS)\n" + ANSI_RESET);
                System.out.println(ANSI_GREEN + "P.S type 'h' or 'help' for help." + ANSI_RESET);
                inputCommand.handler(data);

            } catch (Exception error) {
                error.printStackTrace();
                System.out.println("Invalid file");
                System.out.print("Enter valid txt file path or enter 'exit' to exit : ");
                String userInput = scnr.nextLine();
                if (userInput.equals("")) {
                    continue;
                }
                if (userInput.equals("exit")) {
                    break;
                } else {
                    filePath = userInput;
                }
                continue;
            }
        }

    }

    static List<Cycle> readCycles(List<String[]> rawRecords) { // helper function creates cycle object list from String
                                                               // list

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

    public static List<String[]> readFile(String filePath) throws Exception { // helper funciton, reads file, returns
                                                                              // List of String[]
        // records
        List<String[]> rawRecords = new ArrayList<>();

        try {

            String line;

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) {
                rawRecords.add(line.split(","));
            }

            br.close();

            return rawRecords;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
