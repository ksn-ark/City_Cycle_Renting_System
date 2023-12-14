package Commands.Update;

import java.util.LinkedHashMap;
import java.util.Map;

import Commands.Command;
import Data.AppData;
import Data.User;
import InputHandler.Input;
import InputHandler.InvalidInputException;
import InputHandler.RangeCheck;

public class UpdateUserLocation implements Command {

    static Map<String, Object[]> commandArgs = new LinkedHashMap<>() {
        {
            put("new x-value", new Object[] { (Integer) 0, new RangeCheck(0) });
            put("new y-value", new Object[] { (Integer) 0, new RangeCheck(0) });
        }
    };

    public void execute(AppData data) {

        User user = data.getUser();

        try {
            commandArgs = Input.getCommandArgs(commandArgs);

            int newx = (Integer) commandArgs.get("new x-value")[0];
            int newy = (Integer) commandArgs.get("new y-value")[0];

            System.out.println("\nUser location was at x: " + user.getX() + " and y: " + user.getY());

            user.setX(newx);
            user.setY(newy);

            System.out.println();

            System.out.println("\nUser location is now at x: " + user.getX() + " and y: " + user.getY());

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new UpdateUserLocation();
            AppData.getCommandArgDetails(cmd);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    int inModuleId = 4;
    String commandName = "update user location";
    String commandShort = "u u l";
    String commandInfo = "updates user location.";

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
