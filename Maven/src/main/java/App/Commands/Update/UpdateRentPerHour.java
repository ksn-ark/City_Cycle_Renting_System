package App.Commands.Update;

import java.util.LinkedHashMap;
import java.util.Map;

import App.Commands.Command;
import App.Data.AppData;
import App.InputHandler.*;
import App.InputHandler.InvalidInputException;

public class UpdateRentPerHour implements Command {

    static Map<String, Object[]> commandArgs = new LinkedHashMap<String, Object[]>() {
        {
            put("new rent per hour", new Object[] { (Float) 0.5f, new RangeCheck(0f) });
        }
    };

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

    int inModuleId = 5;
    String commandName = "update rentPerHour";
    String commandShort = "u r";
    String commandInfo = "updates rentPerHour in euro";

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
