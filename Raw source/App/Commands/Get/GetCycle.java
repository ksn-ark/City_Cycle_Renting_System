package Commands.Get;

import Data.AppData;
import Data.Cycle;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Commands.Command;

public class GetCycle implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<>();

    public void execute(AppData data) {

        List<Cycle> cycles = data.getCycles();

        System.out.println("\nAll cycles: ");

        for (Cycle cycle : cycles) {
            System.out.println(cycle.toString() + "\n\n");
        }
    }

    int inModuleId = 5;
    String commandName = "get cycle";
    String commandShort = "g c";
    String commandInfo = "returns all records.";

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
