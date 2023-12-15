package App.Commands.Get;

import java.util.LinkedHashMap;
import java.util.List;
import App.Commands.Command;
import App.Data.AppData;
import App.Data.Cycle;
import App.InputHandler.Input;
import App.InputHandler.InvalidInputException;
import App.InputHandler.RangeCheck;

public class GetCycleHoursRented extends Command {

    public GetCycleHoursRented() {

        this.inModuleId = 8;
        this.commandName = "get cycle hoursRented";
        this.commandShort = "g c h";
        this.commandInfo = "returns records by rented hours, range inclusive.";

        this.commandArgs = new LinkedHashMap<String, Object[]>() {
            {
                put("Max hours rented", new Object[] { "intRequired", new RangeCheck(0) });
                put("Include rented?", new Object[] { false });
                put("Min hours rented", new Object[] { 0, new RangeCheck(0) });
            }
        };
    }

    @Override
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
            System.out.println(AppData.getCommandArgDetails(cmd));
            System.err.println("\n\t*NOTE: min hours has to be lesser than or equal to max hours.");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

}
