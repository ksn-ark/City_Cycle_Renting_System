package Commands.Get;

import Data.Cycle;

import java.util.List;
import org.apache.commons.*;

public class GetCycle {

    public static void execute(List<Cycle> cycles) {
        for (Cycle cycle : cycles) {
            System.out.println(cycle.toString() + "\n\n");
        }
    }
}
