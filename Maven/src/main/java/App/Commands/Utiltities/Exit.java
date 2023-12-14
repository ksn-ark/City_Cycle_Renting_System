package App.Commands.Utiltities;

import java.util.Map;

import App.Commands.Command;
import App.Data.AppData;

public class Exit implements Command {

    public void execute(AppData data) {
        System.err.println("\nExiting\n");
        System.exit(0);
    }

    int inModuleId = 2;
    String commandName = "exit";
    String commandShort = "e";
    String commandInfo = "Exits Program";

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
