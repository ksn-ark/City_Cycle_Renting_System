package Commands.Get;

import Data.AppData;
import Data.Cycle;
import Data.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Commands.Command;

public class GetUser implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<>();

    public void execute(AppData data) {

        User user = data.getUser();
        int hoursRented = user.getHoursRented();
        float rentPerHour = data.getRentPerHour();

        List<Cycle> cycles = user.getUserRentedCycles();

        System.out.println("\nUser: \n");
        System.out.println(user.toString());

        System.err.println("\nRented Cycles: ");

        for (Cycle cycle : cycles) {
            if (cycle.getIsRented()) {
                System.err.println(cycle.toString() + "\n");
            }
        }

        System.out.println("\nTotal rented Hours: " + hoursRented);

        System.err.println("\nTotal Bill: " + rentPerHour * hoursRented);

    }

    int inModuleId = 1;
    String commandName = "get user";
    String commandShort = "g u";
    String commandInfo = "returns user location, rented cycles, total rented hours & total spending in euro.";

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
