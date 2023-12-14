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
                "       5. help command\n\n");

        System.out.print("Enter selection :");
        String userInput = scnr.nextLine();
        System.out.print("\n");
        Map<String[], Runnable> CommandModules = new HashMap<String[], Runnable>() {
            {
                put(new String[] { "1", "add+delete", "add", "delete" }, () -> command("Add"));
                put(new String[] { "2", "update" }, () -> command("Update"));
                put(new String[] { "3", "get" }, () -> command("Get"));
                put(new String[] { "4", "rent" }, () -> command("Rent"));
                put(new String[] { "5", "help" }, () -> help());
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

    void command(String moduleName) {

        Map<String, Command> commandMap = AppData.getCommandMap();

        boolean flag = true;

        for (Map.Entry<String, Command> commandEntry : commandMap.entrySet()) { // prints commands in module
            if (moduleName.equals("Add")) {
                flag = commandEntry.getKey().startsWith("Add") || commandEntry.getKey().startsWith("Delete");
            } else {
                flag = commandEntry.getKey().startsWith(moduleName);
            }
            if (flag) {
                Command cmd = commandEntry.getValue();
                System.out.println(
                        cmd.getCommandId() + ". " + cmd.getCommandName() + " (" + cmd.getCommandShort() + ")\n");
            }
        }

        System.out.print("Enter selection: "); // select command from module

        String userInput = scnr.nextLine();

        System.out.println("\n");

        for (Map.Entry<String, Command> commandEntry : commandMap.entrySet()) { // prints details of command

            if (moduleName.equals("Add")) {
                flag = commandEntry.getKey().startsWith("Add") || commandEntry.getKey().startsWith("Delete");
            } else {
                flag = commandEntry.getKey().startsWith(moduleName);
            }
            if (flag) {
                Command cmd = commandEntry.getValue();
                if (userInput.equals(cmd.getCommandName()) || userInput.equals(cmd.getCommandShort())
                        || userInput.equals(cmd.getCommandIdString())) {

                    System.out.println(cmd.getCommandName() + " (" + cmd.getCommandShort() + ")\n");

                    System.out.println(cmd.getCommandInfo() + "\n");

                    System.out.println(AppData.getCommandArgDetails(cmd));

                }
            }
        }

    }

    void help() {
        System.out.println("\nThis command, displays list of modules allows you to see further details");
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
