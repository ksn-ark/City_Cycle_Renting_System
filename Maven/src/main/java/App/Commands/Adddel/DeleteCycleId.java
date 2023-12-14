package App.Commands.Adddel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import App.Commands.Command;
import App.Data.*;
import App.InputHandler.*;

public class DeleteCycleId implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<String, Object[]>() {
        {
            put("Cycle Id", new Object[] { "intRequired", new RangeCheck(0) });
            put("Range", new Object[] { (Integer) 0, new RangeCheck(0) });
        }
    };

    int deletedCount = 0;

    public void execute(AppData data) {

        List<Cycle> cycles = data.getCycles();
        String filePath = data.getfilePath();

        try {

            commandArgs = Input.getCommandArgs(commandArgs);

            int id = (Integer) commandArgs.get("Cycle Id")[0];
            int range = (Integer) commandArgs.get("Range")[0];

            List<Cycle> modifiedCycles = new ArrayList<>();

            for (int i = 0; i < (cycles.size()); i++) {

                Cycle cycle = cycles.get(i);
                int cycleId = cycle.getIntValues()[0];

                if (id <= cycleId && cycleId <= id + range) {
                    deletedCount++;
                    continue;
                }
                modifiedCycles.add(cycle);
            }

            ReplaceData.replace(modifiedCycles, filePath);
            data.updateCycles(modifiedCycles);

            System.out.println("\n" + deletedCount + " Matching Records Successfully deleted");

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new DeleteCycleId();
            System.out.println(AppData.getCommandArgDetails(cmd));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\nSuccess");
    }

    int inModuleId = 2;
    String commandName = "delete cycle id";
    String commandShort = "d c i";
    String commandInfo = "deletes cycles in inclusive range(Id, Id+Range), confirms number of successfully deleted records.";

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
