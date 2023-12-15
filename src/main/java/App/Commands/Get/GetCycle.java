package App.Commands.Get;

import App.Data.AppData;
import App.Data.Cycle;

import java.util.List;

import App.Commands.CommandAbstract;

public class GetCycle extends CommandAbstract {

    public GetCycle() {

        this.inModuleId = 5;
        this.commandName = "get cycle";
        this.commandShort = "g c";
        this.commandInfo = "returns all records.";
    }

    public void execute(AppData data) {

        List<Cycle> cycles = data.getCycles();

        System.out.println("\nAll cycles: ");

        for (Cycle cycle : cycles) {
            System.out.println(cycle.toString() + "\n");
        }
    }

}
