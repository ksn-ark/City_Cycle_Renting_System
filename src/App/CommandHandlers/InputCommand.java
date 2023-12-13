package CommandHandlers;

import java.util.*;
import java.util.List;

import Commands.*;
import Data.AppData;

public class InputCommand {

    static List<Map<String, String>> shortHandMaps = AppData.getShortHandMaps();

    static Map<String, Command> commandMap = AppData.getCommandMap();

    Scanner scnr = new Scanner(System.in);

    public void handler(AppData data) {
        while (true) {

            System.out.print("\nEnter Command: ");

            String userInput = scnr.nextLine().toLowerCase();

            if (userInput.equals("exit")) {
                System.exit(0);
            }

            String[] splitInput = userInput.split(" ");

            if (splitInput.length > 3) {
                error404();
                continue;
            }

            String commandName = "";

            for (int i = 0; i < splitInput.length; i++) {

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

            command.execute(data);

        }
    }

    static void error404() {
        System.out.println("Error 404: Command Not Found.");
    }
}
