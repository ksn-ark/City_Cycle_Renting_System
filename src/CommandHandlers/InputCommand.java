package CommandHandlers;

import Commands.Help;
import Commands.Adddel.*;
import Commands.Get.*;
import Data.Cycle;
import java.util.*;
import org.apache.commons.*;

public class InputCommand {

    public static void handler(String[] inpuStrings, int topId, List<Cycle> cycles, String filePath) {
        switch (inpuStrings[0]) {
            case "a":
            case "add":
                switch (inpuStrings[1]) {
                    case "cycle":
                    case "c":
                        AddCycle.execute(topId + 1, filePath);
                        break;
                    default:
                        error404();
                        break;
                }
                break;
            case "g":
            case "get":
                switch (inpuStrings[1]) {
                    case "cycle":
                    case "c":
                        GetCycle.execute(cycles);
                        break;
                    default:
                        error404();
                        break;
                }
                break;
            case "e":
            case "exit":
                System.exit(0);
                break;
            case "h":
            case "help":
                Help.execute();
                break;
            default:
                error404();
                break;
        }
    }

    static void error404() {
        System.out.println("Error 404: Command Not Found.");
    }
}
