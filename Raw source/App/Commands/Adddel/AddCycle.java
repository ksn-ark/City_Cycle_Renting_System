package Commands.Adddel;

import java.util.LinkedHashMap;
import java.util.Map;

import Commands.Command;
import Data.AppData;
import Data.Cycle;
import InputHandler.*;

public class AddCycle implements Command {

    static Map<String, Object[]> commandArgs = new LinkedHashMap<>() {
        {
            put("x-value", new Object[] { (Integer) 0, new RangeCheck(0) });
            put("y-value", new Object[] { (Integer) 0, new RangeCheck(0) });
            put("hoursRented", new Object[] { (Integer) 0, new RangeCheck(0) });
            put("isRented", new Object[] { false });
        }
    };

    public void execute(AppData data) {

        int id = data.getTopID();
        String filePath = data.getfilePath();

        try {

            commandArgs = Input.getCommandArgs(commandArgs);

            // assigning command args

            // var = (type) command arg [ 0 = val, 1 = RangeCheck(non-bools) ]

            int x = (Integer) commandArgs.get("x-value")[0];
            int y = (Integer) commandArgs.get("y-value")[0];
            int hoursRented = (Integer) commandArgs.get("hoursRented")[0];
            boolean isRented = (Boolean) commandArgs.get("isRented")[0];

            Cycle cycle = new Cycle(id, x, y, hoursRented, isRented); // cycle object created

            System.out.println("\n" + cycle.toString());

            cycle.add(filePath); // cycle added to csv file
            data.addCycle(cycle); // cycle added to cycles list in the AppData object
            cycle.addTopId(filePath, id); // id val in header of cycles.csv = last added cycle id + 1

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new AddCycle();
            AppData.getCommandArgDetails(cmd);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\nCycle Added");
    }

    int inModuleId = 1;
    String commandName = "add cycle";
    String commandShort = "a c";
    String commandInfo = "adds record with given values.";

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
