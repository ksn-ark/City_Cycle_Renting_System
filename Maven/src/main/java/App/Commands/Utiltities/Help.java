package App.Commands.Utiltities;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import App.Commands.Command;
import App.Data.AppData;

public class Help implements Command {

    static Scanner scnr = new Scanner(System.in);

    public void execute(AppData data) {
        System.out.print("\nSelect an option by entering the number or module name [enter anything else to exit] :\n" +
                "   Command Modules:\n" +
                "       1. add+delete commands\n" +
                "       2. update commands\n" +
                "       3. get commands\n" +
                "       4. rent commands\n" +
                "       5. utility commands\n\n");

        System.out.print("Enter selection :");
        String userInput = scnr.nextLine().toLowerCase();
        System.out.print("\n");

        Map<String[], Runnable> CommandModules = new HashMap<String[], Runnable>() {
            {
                put(new String[] { "1", "add+delete", "add", "delete" }, () -> commands("Add"));
                put(new String[] { "2", "update" }, () -> commands("Update"));
                put(new String[] { "3", "get" }, () -> commands("Get"));
                put(new String[] { "4", "rent" }, () -> commands("Rent"));
                put(new String[] { "5", "utility" }, () -> commands("Util"));
            }
        };

        for (Map.Entry<String[], Runnable> module : CommandModules.entrySet()) { // selects module
            for (String checkString : module.getKey()) {
                if (checkString.equals(userInput)) {
                    module.getValue().run();
                    return;
                }
            }
        }

        System.out.println("\nno such module, returning to command.\n");
    }

    void commands(String moduleName) {

        Map<String, Command> commandMap = AppData.getCommandMap();

        Map<String, Command> moduleMap = new HashMap<>();

        boolean flag = true;

        for (Map.Entry<String, Command> commandEntry : commandMap.entrySet()) { // prints commands in module and adds
                                                                                // them to moduleMap

            flag = setFlag(moduleName, commandEntry.getKey());

            if (flag) {
                Command cmd = commandEntry.getValue();
                System.out.println(
                        cmd.getCommandId() + ". " + cmd.getCommandName() + " (" + cmd.getCommandShort() + ")\n");
                moduleMap.put(commandEntry.getKey(), commandEntry.getValue());
            }
        }

        System.out.print("Enter selection: "); // select command from module

        String userInput = scnr.nextLine().toLowerCase();

        System.out.println("\n");

        int matchCheck = 0;

        for (Map.Entry<String, Command> commandEntry : moduleMap.entrySet()) { // prints details of command

            Command cmd = commandEntry.getValue();

            if (userInput.equals(cmd.getCommandName().toLowerCase())
                    || userInput.equals(cmd.getCommandShort().toLowerCase())
                    || userInput.equals(cmd.getCommandIdString())) {

                System.out.println(cmd.getCommandName() + " (" + cmd.getCommandShort() + ")\n");

                System.out.println(cmd.getCommandInfo() + "\n");

                System.out.println(AppData.getCommandArgDetails(cmd));

                matchCheck++;
            }
        }

        if (matchCheck == 0) {
            System.out.println("Invalid selection, returning to command.\n");
        }
    }

    boolean setFlag(String moduleName, String commandEntryKey) {
        boolean flag = false;

        if (moduleName.equals("Add")) {
            flag = commandEntryKey.startsWith("Add") || commandEntryKey.startsWith("Delete");
        }
        if (moduleName.equals("Util")) {
            flag = commandEntryKey.startsWith("Help") || commandEntryKey.startsWith("Exit");
        }
        if (!moduleName.equals("Add") && !moduleName.equals("Util")) {
            flag = commandEntryKey.startsWith(moduleName);
        }
        return flag;
    }

    int inModuleId = 1;
    String commandName = "help";
    String commandShort = "h";
    String commandInfo = "This command, displays list of modules allows you to see further details";

    public String getCommandName() {
        return commandName;
    }

    public String getCommandShort() {
        return commandShort;
    }

    public String getCommandInfo() {
        return commandInfo;
    }

    public Map<String, Object[]> getCommandArgs() {
        return commandArgs;
    }

    public int getCommandId() {
        return inModuleId;
    }

    public String getCommandIdString() {
        return Integer.toString(inModuleId);
    }
}
