package App.CommandHandlers;

import java.util.*;

import App.Commands.Command;
import App.Data.AppData;

public class InputCommand {

    static List<Map<String, String>> shortHandMaps = AppData.getShortHandMaps();

    static Map<String, Command> commandMap = AppData.getCommandMap();

    static Scanner scnr = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLUE = " \u001B[34m";

    public void handler(AppData data) {
        while (true) {

            System.out.print(ANSI_BLUE + "\nEnter Command: " + ANSI_RESET);

            String userInput = scnr.nextLine().toLowerCase();

            if (userInput.equals("")) {
                continue;
            }

            String[] splitInput = userInput.split(" ");

            String commandName = "";

            for (int i = 0; i < shortHandMaps.size() && i < splitInput.length; i++) {

                try {
                    String nameArg = shortHandMaps.get(i).get(splitInput[i]);

                    if (nameArg == null) {
                        continue;
                    }
                    commandName += nameArg;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }

            if (commandName.equals("")) {
                for (String string : splitInput) {
                    commandName += string.substring(0, 1).toUpperCase() + string.substring(1);
                }
            }

            Command command = commandMap.get(commandName);

            if (command == null) {
                error404();
                continue;
            }
            try {
                command.execute(data);
            } catch (Exception e) {
                continue;
            }
        }
    }

    static void error404() {
        System.out.println("Error 404: Command Not Found.");
    }
}
