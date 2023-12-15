package App.Commands.Update;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import App.Commands.Command;
import App.Commands.CommandAbstract;
import App.Data.AppData;
import App.Data.Cycle;
import App.Data.ModifyTextData;
import App.InputHandler.Input;
import App.InputHandler.InvalidInputException;
import App.InputHandler.RangeCheck;

public class UpdateCycle extends CommandAbstract {

    public UpdateCycle() {
        this.inModuleId = 1;
        this.commandName = "update cycle";
        this.commandShort = "u c";
        this.commandInfo = "updates locations of all rented cycles in inclusive range and can set rented status of all to false";
        this.commandArgs = new LinkedHashMap<String, Object[]>() {
            {
                put("Cycle Id", new Object[] { "intRequired", new RangeCheck(0) });
                put("Range", new Object[] { (Integer) 0, new RangeCheck(0) });
                put("new x-value", new Object[] { "intUnchanged", new RangeCheck(0) });
                put("new y-value", new Object[] { "intUnchanged", new RangeCheck(0) });
                put("Mark unrented?", new Object[] { false });
            }
        };
    }

    public void execute(AppData data) {

        List<Cycle> cycles = data.getCycles();

        List<Cycle> modifiedCycles = new ArrayList<>();

        String filePath = data.getfilePath();

        int updatedCount = 0;

        try {
            commandArgs = Input.getCommandArgs(commandArgs);

            int id = (Integer) commandArgs.get("Cycle Id")[0];
            int range = (Integer) commandArgs.get("Range")[0];

            int newx = (Integer) commandArgs.get("new x-value")[0];
            int newy = (Integer) commandArgs.get("new y-value")[0];

            boolean markUnrented = (Boolean) commandArgs.get("Mark unrented?")[0];

            for (Cycle cycle : cycles) {

                if (id <= cycle.getId() && cycle.getId() <= id + range) {

                    int i = 0; // checks if atleast one change was made.

                    if (markUnrented) {
                        cycle.setIsRented(false);
                        i++;
                    }
                    if (newx > 0) {
                        cycle.setX(newx);
                        i++;
                    }
                    if (newy > 0) {
                        cycle.setY(newy);
                        i++;
                    }
                    if (i > 0) {
                        updatedCount++; // keeps count of updated records
                        modifiedCycles.add(cycle);
                    }
                }
            }

            data.updateCycles(cycles);
            ModifyTextData.replace(cycles, filePath);

            System.out.println("\nModified Cycles :");

            for (Cycle cycle : modifiedCycles) {
                System.out.println(cycle.toString() + "\n");
            }
            System.out.println("\n" + updatedCount + " Matching Records successfully updated");

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new UpdateCycle();
            System.out.println(AppData.getCommandArgDetails(cmd));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

}
