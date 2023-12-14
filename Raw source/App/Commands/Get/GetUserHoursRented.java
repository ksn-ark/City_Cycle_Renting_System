package Commands.Get;

import java.util.LinkedHashMap;
import java.util.Map;

import Commands.Command;
import Data.AppData;
import Data.User;

public class GetUserHoursRented implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<>();

    public void execute(AppData data) {

        User user = data.getUser();
        int hoursRented = user.getHoursRented();

        System.out.println("\nTotal hours rented by user: " + hoursRented);

    }

    int inModuleId = 3;
    String commandName = "get user hoursRented";
    String commandShort = "g u h";
    String commandInfo = "returns users total rented hours";

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