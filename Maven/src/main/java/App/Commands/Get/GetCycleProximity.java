package App.Commands.Get;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import App.Commands.Command;
import App.Data.AppData;
import App.Data.Cycle;
import App.Data.User;
import App.InputHandler.Input;
import App.InputHandler.InvalidInputException;
import App.InputHandler.RangeCheck;

public class GetCycleProximity implements Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<String, Object[]>() {
        {
            put("Range-x", new Object[] { 5, new RangeCheck(0) });
            put("Range-y", new Object[] { "Range-x", new RangeCheck(0) });
            put("Include rented?", new Object[] { false });
        }
    };

    public void execute(AppData data) {

        List<Cycle> cycles = data.getCycles();

        User user = data.getUser();

        try {

            commandArgs = Input.getCommandArgs(commandArgs);

            int userx = user.getX();

            int usery = user.getY();

            int rangex = (Integer) commandArgs.get("Range-x")[0];
            int rangey = (Integer) commandArgs.get("Range-y")[0];
            boolean includeRented = (Boolean) commandArgs.get("Include rented?")[0];

            System.out.println("\nAll cycles matching filters: ");

            for (Cycle cycle : cycles) {
                if (!includeRented && cycle.getIsRented()) {
                    continue;
                }
                if (userx - rangex <= cycle.getX() && cycle.getX() <= userx + rangex) {
                    if (usery - rangey <= cycle.getY() && cycle.getY() <= usery + rangey) {
                        System.out.println(cycle.toString() + "\n\n");
                    }
                }
            }

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new GetCycleProximity();
            System.out.println(AppData.getCommandArgDetails(cmd));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    int inModuleId = 9;
    String commandName = "get cycle proximity";
    String commandShort = "g c p";
    String commandInfo = "returns records by distance from user.";

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
