package Commands.Get;

import java.util.LinkedHashMap;
import java.util.Map;

import Commands.Command;
import Data.AppData;
import Data.Cycle;

public class GetCycleRented implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<>();

    public void execute(AppData data) {

        System.out.println("\nAll cycles currently rented: ");

        for (Cycle cycle : data.getCycles()) {
            if (cycle.getIsRented()) {
                System.out.println(cycle.toString() + "\n\n");
            }
        }

    }

    int inModuleId = 6;
    String commandName = "get cycle rented";
    String commandShort = "g c r";
    String commandInfo = "returns all rented cycles";

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
