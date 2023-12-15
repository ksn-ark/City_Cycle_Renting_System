package App.Commands.Get;

import App.Commands.CommandAbstract;
import App.Data.AppData;
import App.Data.Cycle;

public class GetCycleRented extends CommandAbstract {

    public GetCycleRented() {
        this.inModuleId = 6;
        this.commandName = "get cycle rented";
        this.commandShort = "g c r";
        this.commandInfo = "returns all rented cycles";
    }

    public void execute(AppData data) {

        System.out.println("\nAll cycles currently rented: ");

        for (Cycle cycle : data.getCycles()) {
            if (cycle.getIsRented()) {
                System.out.println(cycle.toString() + "\n\n");
            }
        }

    }

}
