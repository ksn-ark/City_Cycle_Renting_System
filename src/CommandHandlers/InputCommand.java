package CommandHandlers;

import Commands.Help;
import Commands.Adddel.*;
import Commands.Get.*;
import Data.Cycle;
import java.util.*;

public class InputCommand {

    public static void handler(String[] inputStrings, int topId, List<Cycle> cycles, String filePath) {

        switch (inputStrings[0]) {
            case "a":
            case "add":
                switch (inputStrings[1]) {
                    case "c":
                    case "cycle":
                        AddCycle.execute(topId + 1, filePath);
                        break;
                    default:
                        error404();
                        break;
                }
                break;
            case "d":
            case "delete":
                switch (inputStrings[1]) {
                    case "c":
                    case "cycle":
                        switch (inputStrings[2]) {
                            case "a":
                            case "area":
                                DeleteCycleArea.execute(cycles, filePath);
                                break;
                            case "i":
                            case "id":
                                DeleteCycleId.execute(cycles, filePath);
                                break;
                            default:
                                error404();
                                break;
                        }
                        break;

                    default:
                        error404();
                        break;
                }
                break;
            case "g":
            case "get":
                switch (inputStrings[1]) {
                    case "c":
                    case "cycle":
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
