package App.Commands.Rent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import App.Commands.Command;
import App.Commands.Update.UpdateCycleRented;
import App.Data.AppData;
import App.Data.Cycle;
import App.Data.ModifyTextData;
import App.Data.User;
import App.InputHandler.Input;
import App.InputHandler.InvalidInputException;
import App.InputHandler.RangeCheck;

public class RentCycleProximity extends Command {

    public RentCycleProximity() {

        this.inModuleId = 3;
        this.commandName = "rent cycle proximity";
        this.commandShort = "r c p";
        this.commandInfo = "rents the next available cycle of the lowest id, in given proximity to user location, till required number of cycles are marked for rent. if not enough, available cycles are marked for rent. bill is calculated, confirmation is asked.";
        this.commandArgs = new LinkedHashMap<String, Object[]>() {
            {
                put("Number of cycles", new Object[] { (Integer) 1, new RangeCheck(1) });
                put("Hours to rent", new Object[] { (Integer) 1, new RangeCheck(1, 24) });
                put("Range-x", new Object[] { 5, new RangeCheck(0) });
                put("Range-y", new Object[] { "Range-x", new RangeCheck(0) });
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

            int rangex = (Integer) commandArgs.get("Range-x")[0];
            int rangey = (Integer) commandArgs.get("Range-y")[0];
            int requiredRentCount = (Integer) commandArgs.get("Number of cycles")[0];
            int hoursToRent = (Integer) commandArgs.get("Hours to rent")[0];

            for (Cycle cycle : cycles) {

                if (user.getX() - rangex <= cycle.getX() && cycle.getX() <= user.getX() + rangex
                        && rentedCount < requiredRentCount) {
                    if (user.getY() - rangey <= cycle.getY() && cycle.getY() <= user.getY() + rangey
                            && !cycle.getIsRented()) {

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

    static Scanner confirmScanner = new Scanner(System.in);

}
