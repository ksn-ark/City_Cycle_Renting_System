package App.Commands.Get;

import java.util.LinkedHashMap;
import java.util.Map;

import App.Commands.Command;
import App.Data.AppData;
import App.Data.User;

public class GetUserLocation implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<>();

    public void execute(AppData data) {
        User user = data.getUser();

        System.out.println("\nUser location is at x: " + user.getX() + " and y: " + user.getY());
    }

    int inModuleId = 2;
    String commandName = "get user location";
    String commandShort = "g u l";
    String commandInfo = "returns user location.";

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