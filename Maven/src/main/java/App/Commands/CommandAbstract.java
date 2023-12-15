package App.Commands;

import java.util.LinkedHashMap;
import java.util.Map;

import App.Data.AppData;

public abstract class CommandAbstract implements Command {

    public Map<String, Object[]> commandArgs = new LinkedHashMap<>();

    public int inModuleId;

    public String commandName;

    public String commandShort;

    public String commandInfo;

    public void execute(AppData data) {
    }// main execute method

    // get methods for all command attributes

    public int getCommandId() {
        return inModuleId;
    }

    public String getCommandIdString() {
        return Integer.toString(inModuleId);
    }

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
}
