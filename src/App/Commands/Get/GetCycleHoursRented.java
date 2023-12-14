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

public class GetCycleHoursRented implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<>() {
        {
            put("Max hours rented", new Object[] { "intRequired", new RangeCheck(0) });
            put("Include rented?", new Object[] { false });
            put("Min hours rented", new Object[] { 0, new RangeCheck(0) });
        }
    };

    public void execute(AppData data) {
        List<Cycle> cycles = data.getCycles();

        try {

            commandArgs = Input.getCommandArgs(commandArgs);

            int maxHoursRented = (Integer) commandArgs.get("Max hours rented")[0];
            boolean includeRented = (Boolean) commandArgs.get("Include rented?")[0];
            int minHoursRented = (Integer) commandArgs.get("Min hours rented")[0];

            if (minHoursRented > maxHoursRented) {
                throw new InvalidInputException("nice");
            }

            System.out.println("\nAll cycles matching filters: ");

            for (Cycle cycle : cycles) {
                if (!includeRented && cycle.getIsRented()) {
                    continue;
                }
                if (minHoursRented <= cycle.getHoursRented() && cycle.getHoursRented() <= maxHoursRented) {
                    System.out.println(cycle.toString() + "\n\n");
                }
            }

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new GetCycleHoursRented();
            AppData.getCommandArgDetails(cmd);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    int inModuleId = 8;
    String commandName = "get cycle hoursRented";
    String commandShort = "g c h";
    String commandInfo = "returns records by rented hours, range inclusive.";

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
