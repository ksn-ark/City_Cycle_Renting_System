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

public class UpdateCycleRented extends CommandAbstract {

    public UpdateCycleRented() {
        this.inModuleId = 3;
        this.commandName = "update cycle rented";
        this.commandShort = "u c r";
        this.commandInfo = "updates the rented status of all rented cycles in inclusive range to false";
        this.commandArgs = new LinkedHashMap<String, Object[]>() {
            {
                put("Cycle Id", new Object[] { "intRequired", new RangeCheck(0) });
                put("Range", new Object[] { (Integer) 0, new RangeCheck(0) });
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
            ModifyTextData.replace(cycles, filePath);

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

}