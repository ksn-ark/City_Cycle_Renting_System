
package Commands.Get;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Commands.Command;
import Data.AppData;
import Data.Cycle;
import InputHandler.Input;
import InputHandler.InvalidInputException;
import InputHandler.RangeCheck;

public class GetCycleId implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<>() {
        {
            put("Cycle Id", new Object[] { "intRequired", new RangeCheck(0) });
            put("Range", new Object[] { 0, new RangeCheck(0) });
            put("Include rented?", new Object[] { false });
        }
    };

    public void execute(AppData data) {

        List<Cycle> cycles = data.getCycles();

        try {

            commandArgs = Input.getCommandArgs(commandArgs);

            int cycleId = (Integer) commandArgs.get("Cycle Id")[0];

            int range = (Integer) commandArgs.get("Range")[0];

            boolean includeRented = (Boolean) commandArgs.get("Include rented?")[0];

            System.out.println("\nAll cycles matching filters: ");

            for (Cycle cycle : cycles) {

                if (!includeRented && cycle.getIsRented()) {
                    continue;
                }

                if (cycleId <= cycle.getId() && cycle.getId() <= cycleId + range) {
                    System.out.println(cycle.toString() + "\n\n");
                }
            }

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new GetCycleId();
            AppData.getCommandArgDetails(cmd);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\nSuccess");
    }

    int inModuleId = 7;
    String commandName = "get cycle id";
    String commandShort = "g c i";
    String commandInfo = "returns records by id range, range inclusive.";

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
