package App.Commands.Update;

import java.util.LinkedHashMap;

import App.Commands.Command;
import App.Commands.CommandAbstract;
import App.Data.AppData;
import App.InputHandler.*;

public class UpdateRentPerHour extends CommandAbstract {

    public UpdateRentPerHour() {
        this.inModuleId = 5;
        this.commandName = "update rentPerHour";
        this.commandShort = "u r";
        this.commandInfo = "updates rentPerHour in euro";
        this.commandArgs = new LinkedHashMap<String, Object[]>() {
            {
                put("new rent per hour", new Object[] { (Float) 0.5f, new RangeCheck(0f) });
            }
        };
    }

    public void execute(AppData data) {

        float rentPerHour = data.getRentPerHour();
        float newRentPerHour;

        try {
            commandArgs = Input.getCommandArgs(commandArgs);

            newRentPerHour = (Float) commandArgs.get("new rent per hour")[0];

            data.setRentPerHour(newRentPerHour);

            System.out.println(
                    "\nRent per hour in eur changed successfully from " + rentPerHour + " to " + newRentPerHour);

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new UpdateRentPerHour();
            AppData.getCommandArgDetails(cmd);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

}
