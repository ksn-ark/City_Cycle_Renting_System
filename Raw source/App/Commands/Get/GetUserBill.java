package Commands.Get;

import java.util.LinkedHashMap;
import java.util.Map;

import Commands.Command;
import Data.AppData;
import Data.User;

public class GetUserBill implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<>();

    public void execute(AppData data) {

        User user = data.getUser();

        float rentPerHour = data.getRentPerHour();

        int hoursRented = user.getHoursRented();

        System.out.println("\nUser rent Bill: " + rentPerHour * hoursRented);

    }

    int inModuleId = 4;
    String commandName = "get user bill";
    String commandShort = "g u b";
    String commandInfo = "returns user total spending (in euro)";

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
