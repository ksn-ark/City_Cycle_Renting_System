package App.Commands.Update;

import java.util.LinkedHashMap;

import App.Commands.Command;
import App.Data.AppData;
import App.Data.User;
import App.InputHandler.Input;
import App.InputHandler.InvalidInputException;
import App.InputHandler.RangeCheck;

public class UpdateUserLocation extends Command {

    public UpdateUserLocation() {
        this.inModuleId = 4;
        this.commandName = "update user location";
        this.commandShort = "u u l";
        this.commandInfo = "updates user location.";
        this.commandArgs = new LinkedHashMap<String, Object[]>() {
            {
                put("new x-value", new Object[] { (Integer) 0, new RangeCheck(0) });
                put("new y-value", new Object[] { (Integer) 0, new RangeCheck(0) });
            }
        };
    }

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

}
