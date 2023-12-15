package App.Commands.Rent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import App.Commands.Command;
import App.Commands.Update.UpdateCycleRented;
import App.Data.AppData;
import App.Data.Cycle;
import App.Data.ModifyTextData;
import App.Data.User;
import App.InputHandler.Input;
import App.InputHandler.InvalidInputException;
import App.InputHandler.RangeCheck;

public class RentCycleLocation extends Command {

    public RentCycleLocation() {
        this.inModuleId = 2;
        this.commandName = "rent cycle location";
        this.commandShort = "r c l";
        this.commandInfo = "rents available cycles by location, if enough cycles, calculates bill and asks for confirmation, if not enough cycles presents invoice for max cycles available & asks for confirmation, if more cycles than needed at location lowest id's are rented.";
        this.commandArgs = new LinkedHashMap<String, Object[]>() {
            {
                put("Number of cycles", new Object[] { (Integer) 1, new RangeCheck(1) });
                put("Hours to rent", new Object[] { (Integer) 1, new RangeCheck(1, 24) });
                put("x-value", new Object[] { 0, new RangeCheck(0) });
                put("y-value", new Object[] { 0, new RangeCheck(0) });
            }
        };
    }

    public void execute(AppData data) {

        String filePath = data.getfilePath();

        float rentPerHour = data.getRentPerHour();

        List<Cycle> cycles = data.getCycles();

        User user = data.getUser();

        List<Cycle> rentedCycles = new ArrayList<>();

        int rentedCount = 0;

        try {
            commandArgs = Input.getCommandArgs(commandArgs);

            int x = (Integer) commandArgs.get("x-value")[0];
            int y = (Integer) commandArgs.get("y-value")[0];
            int requiredRentCount = (Integer) commandArgs.get("Number of cycles")[0];
            int hoursToRent = (Integer) commandArgs.get("Hours to rent")[0];

            for (Cycle cycle : cycles) {

                if (x == cycle.getX() && rentedCount < requiredRentCount) {
                    if (y == cycle.getY() && !cycle.getIsRented()) {

                        cycle.setHoursRented(cycle.getHoursRented() + hoursToRent);
                        cycle.setIsRented(true);
                        rentedCycles.add(cycle);

                        user.setHoursRented(user.getHoursRented() + hoursToRent);

                        rentedCount++;
                    }
                }
            }

            System.out.println("Total cycles being rented " + rentedCount);
            System.out.println("Number of hours each cycle is being rented for " + hoursToRent);

            if (rentedCount < requiredRentCount) {
                System.out.println("\nTotal bill = " + rentedCount * hoursToRent * rentPerHour + " Euro");
                System.out.println("\nOnly " + rentedCount + " cycles available under current filters.\n");
                if (!Input.confirmAction()) {
                    return;
                }
            }

            System.out.println("\nFinal bill = " + rentedCount * hoursToRent * rentPerHour + " Euro\n");

            if (!Input.confirmAction()) {
                return;
            }

            System.out.println("\nNewly rented cycles: ");

            for (Cycle cycle : rentedCycles) {
                System.out.println(cycle.toString() + "\n");
            }

            user.setUserRentedCycles(rentedCycles);
            data.setUser(user);
            data.updateCycles(cycles);
            ModifyTextData.replace(cycles, filePath);

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
}
