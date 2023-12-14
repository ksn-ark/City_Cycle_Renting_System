package Commands.Adddel;

import Data.*;
import InputHandler.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Commands.Command;

public class DeleteCycleArea implements Command {

    static Map<String, Object[]> commandArgs = new LinkedHashMap<>() {
        {
            put("x-value", new Object[] { (Integer) 0, new RangeCheck(0) });
            put("y-value", new Object[] { (Integer) 0, new RangeCheck(0) });
            put("arSide-Value", new Object[] { (Integer) 1, new RangeCheck(1) });
            put("arSide2-Value", new Object[] { "arSide-Value", new RangeCheck(1) });
        }
    };

    public void execute(AppData data) {

        List<Cycle> cycles = data.getCycles();
        String filePath = data.getfilePath();

        int deletedCount = 0;

        try {
            commandArgs = Input.getCommandArgs(commandArgs);

            int x = (Integer) commandArgs.get("x-value")[0];
            int y = (Integer) commandArgs.get("y-value")[0];
            int arSide1 = (Integer) commandArgs.get("arSide-Value")[0];
            int arSide2 = (Integer) commandArgs.get("arSide2-Value")[0];

            List<Cycle> modifiedCycles = new ArrayList<>();

            for (int i = 0; i < (cycles.size()); i++) {

                Cycle cycle = cycles.get(i);
                int cyclex = cycle.getIntValues()[1];
                int cycley = cycle.getIntValues()[2];

                if (x <= cyclex && cyclex <= x + arSide1 - 1 && y <= cycley && cycley <= y + arSide2 - 1) {
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
            Command cmd = new DeleteCycleArea();
            AppData.getCommandArgDetails(cmd);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    int inModuleId = 3;
    String commandName = "delete cycle area";
    String commandShort = "d c a";
    String commandInfo = "delete records in rectangular area of length = arSide, breadth = arSide2 & bottom-left-corner= x,y.";

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
