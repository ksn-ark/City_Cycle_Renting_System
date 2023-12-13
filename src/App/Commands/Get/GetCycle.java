package Commands.Get;

import Data.AppData;
import Data.Cycle;

import java.util.List;

import Commands.Command;

public class GetCycle implements Command {

    public void execute(AppData data) {

        List<Cycle> cycles = data.getCycles();

        for (Cycle cycle : cycles) {
            System.out.println(cycle.toString() + "\n\n");
        }
    }
}
