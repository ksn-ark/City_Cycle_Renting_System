package App.Commands.Adddel;

import java.util.LinkedHashMap;

import App.Commands.Command;
import App.Commands.CommandAbstract;
import App.Data.AppData;
import App.Data.Cycle;
import App.Data.ModifyTextData;
import App.InputHandler.*;

public class AddCycle extends CommandAbstract {

    public AddCycle() {

        this.inModuleId = 1;
        this.commandName = "add cycle";
        this.commandShort = "a c";
        this.commandInfo = "adds record with given values.";

        this.commandArgs = new LinkedHashMap<String, Object[]>() {

            {
                put("x-value", new Object[] { (Integer) 0, new RangeCheck(0) });
                put("y-value", new Object[] { (Integer) 0, new RangeCheck(0) });
                put("hoursRented", new Object[] { (Integer) 0, new RangeCheck(0) });
                put("isRented", new Object[] { false });
            }
        };
    }

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

            ModifyTextData.add(filePath, cycle); // cycle added to csv file
            data.addCycle(cycle); // cycle added to cycles list in the AppData object
            ModifyTextData.addTopId(filePath); // id val in header is +1'd

        } catch (InvalidInputException e) {
            System.out.println("\nFailure, Invalid inputs\n");
            Command cmd = new AddCycle();
            System.out.println(AppData.getCommandArgDetails(cmd));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\nCycle Added");
    }
}
