package App.Commands.Rent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import App.Commands.Command;
import App.Commands.Update.UpdateCycleRented;
import App.Data.AppData;
import App.Data.Cycle;
import App.Data.ReplaceData;
import App.InputHandler.Input;
import App.InputHandler.InvalidInputException;
import App.InputHandler.RangeCheck;

public class RentCycle implements Command {

    static Map<String, Object[]> commandArgs = new LinkedHashMap<String, Object[]>() {
        {
            put("Cycle Id", new Object[] { "intRequired", new RangeCheck(0) });
            put("Number of cycles", new Object[] { (Integer) 1, new RangeCheck(1) });
            put("Hours to rent", new Object[] { (Integer) 1, new RangeCheck(1, 24) });
        }
    };

    public void execute(AppData data) {

        String filePath = data.getfilePath();

        float rentPerHour = data.getRentPerHour();

        List<Cycle> cycles = data.getCycles();

        List<Cycle> rentedCycles = new ArrayList<>();

        int rentedCount = 0;

        try {
            commandArgs = Input.getCommandArgs(commandArgs);

            int id = (Integer) commandArgs.get("Cycle Id")[0];
            int requiredRentCount = (Integer) commandArgs.get("Number of cycles")[0];
            int hoursToRent = (Integer) commandArgs.get("Hours to rent")[0];

            for (Cycle cycle : cycles) {

                if (id <= cycle.getId() && rentedCount < requiredRentCount && !cycle.getIsRented()) {

                    cycle.setIsRented(true);
                    rentedCycles.add(cycle);
                    rentedCount++;
                }
            }

            if (rentedCount == 0) {
                System.out.println("\n" + rentedCount + " Matching Cycles.");
                return;
            }

            System.out.println("\nTotal cycles being rented " + rentedCount);
            System.out.println("\nNumber of hours each cycle is being rented for " + hoursToRent);

            if (rentedCount < requiredRentCount) {
                System.out.println("\nTotal bill = " + rentedCount * hoursToRent * rentPerHour);
                System.out.println("\nOnly " + rentedCount + " cycles available under current filters.\n");
                if (!Input.confirmAction()) {
                    return;
                }
            }

            System.out.println("\nFinal bill = " + rentedCount * hoursToRent * rentPerHour + "\n");

            if (!Input.confirmAction()) {
                return;
            }

            System.out.println("\nNewly rented cycles: ");

            for (Cycle cycle : rentedCycles) {
                System.out.println(cycle.toString() + "\n");
            }

            data.updateCycles(cycles);
            ReplaceData.replace(cycles, filePath);

            System.out.println("\n" + rentedCount + " Matching Cycles successfully rented");

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

    int inModuleId = 1;
    String commandName = "rent cycle";
    String commandShort = "r c";
    String commandInfo = "rents every available cycle with cycleId >= given id till required number of cycles are marked for rent. if not enough, available cycles are marked for rent. bill is calculated, confirmation is asked.";

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
