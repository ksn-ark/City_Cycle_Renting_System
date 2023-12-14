package App.Commands.Update;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import App.Commands.Command;
import App.Data.AppData;
import App.Data.Cycle;
import App.Data.ReplaceData;
import App.InputHandler.Input;
import App.InputHandler.InvalidInputException;
import App.InputHandler.RangeCheck;

public class UpdateCycleRented implements Command {

    static Map<String, Object[]> commandArgs = new LinkedHashMap<String, Object[]>() {
        {
            put("Cycle Id", new Object[] { "intRequired", new RangeCheck(0) });
            put("Range", new Object[] { (Integer) 0, new RangeCheck(0) });
        }
    };

    public void execute(AppData data) {

        List<Cycle> cycles = data.getCycles();

        List<Cycle> modifiedCycles = new ArrayList<>();

        String filePath = data.getfilePath();

        int updatedCount = 0;

        try {
            commandArgs = Input.getCommandArgs(commandArgs);

            int id = (Integer) commandArgs.get("Cycle Id")[0];
            int range = (Integer) commandArgs.get("Range")[0];

            for (Cycle cycle : cycles) {

                if (id <= cycle.getId() && cycle.getId() <= id + range) {

                    if (cycle.getIsRented()) {

                        cycle.setIsRented(false);
                        modifiedCycles.add(cycle);
                        updatedCount++;
                    }
                }
            }

            System.out.println("\nModified Cycles :");

            for (Cycle cycle : modifiedCycles) {
                System.out.println(cycle.toString() + "\n");
            }

            data.updateCycles(cycles);
            ReplaceData.replace(cycles, filePath);

            System.out.println("\n" + updatedCount + " Matching Records successfully updated");

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new UpdateCycleRented();
            System.out.println(AppData.getCommandArgDetails(cmd));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    int inModuleId = 3;
    String commandName = "update cycle rented";
    String commandShort = "u c r";
    String commandInfo = "updates the rented status of all rented cycles in inclusive range to false";

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