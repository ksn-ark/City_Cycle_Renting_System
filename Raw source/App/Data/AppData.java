package Data;

import java.util.*;

import Commands.*;
import Commands.Adddel.*;
import Commands.Get.*;
import Commands.Update.*;

import Commands.Utiltities.*;
import InputHandler.RangeCheck;

public class AppData {

    int topId;

    String filePath;

    User user;

    float rentPerHour;

    List<Cycle> cycles;

    private static final List<Map<String, String>> shortHandMaps;

    private static final Map<String, Command> commandMap;

    public AppData(int topId, String filepath, User user, float rentPerHour, List<Cycle> cycles) {
        this.topId = topId;
        this.filePath = filepath;
        this.user = user;
        this.rentPerHour = rentPerHour;
        this.cycles = cycles;
    }

    public int getTopID() {
        return topId;
    }

    public String getfilePath() {
        return filePath;
    }

    public User getUser() {
        return user;
    }

    public float getRentPerHour() {
        return rentPerHour;
    }

    public List<Cycle> getCycles() {
        return cycles;
    }

    public void addCycle(Cycle cycle) {
        cycles.add(cycle);
    }

    public void updateCycles(List<Cycle> newCycles) {
        cycles = newCycles;
    }

    static { // mapping the command short hands to their long forms
        Map<String, String> shm1 = new HashMap<>();

        shm1.put("h", "Help");
        shm1.put("a", "Add");
        shm1.put("d", "Delete");
        shm1.put("u", "Update");
        shm1.put("g", "Get");
        shm1.put("r", "Rent");
        shm1.put("e", "Exit");

        Map<String, String> shm2 = new HashMap<>();

        shm2.put("u", "User");
        shm2.put("c", "Cycle");

        Map<String, String> shm3 = new HashMap<>();

        shm3.put("i", "Id");
        shm3.put("l", "Location");
        shm3.put("b", "Bill");
        shm3.put("r", "Rented");
        shm3.put("h", "HoursRented");
        shm3.put("p", "Proximity");
        shm3.put("a", "AreaRange");

        shortHandMaps = Arrays.asList(Collections.unmodifiableMap(shm1), Collections.unmodifiableMap(shm2),
                Collections.unmodifiableMap(shm3));
    }

    static { // map of string command names to command instances

        Map<String, Command> m = new LinkedHashMap<>();

        // add + delete commands
        m.put("AddCycle", new AddCycle());
        m.put("DeleteCycleArea", new DeleteCycleArea());
        m.put("DeleteCycleId", new DeleteCycleId());

        // get commands
        m.put("GetUser", new GetUser());
        m.put("GetUserLocation", new GetUserLocation());
        m.put("GetUserHoursRented", new GetUserHoursRented());
        m.put("GetUserBill", new GetUserBill());
        m.put("GetCycle", new GetCycle());
        m.put("GetCycleRented", new GetCycleRented());
        m.put("GetCycleId", new GetCycleId());
        m.put("GetCycleHoursRented", new GetCycleHoursRented());
        m.put("GetCycleProximity", new GetCycleProximity());

        // update commands
        m.put("UpdateUserLocation", new UpdateUserLocation());

        // rent commands

        // utility commands
        m.put("Help", new Help());
        m.put("Exit", new Exit());

        commandMap = m;

    }

    public static List<Map<String, String>> getShortHandMaps() {
        return shortHandMaps;
    }

    public static Map<String, Command> getCommandMap() {
        return commandMap;
    }

    static public String getCommandArgDetails(Command cmd) {
        String output = "";
        Map<String, Object[]> commandargs = cmd.getCommandArgs();

        if (commandargs.size() == 0) {
            output += "\nExpected args: None";
            return output;
        }

        System.out.print("\nExpected args: \n");

        for (Map.Entry<String, Object[]> arg : commandargs.entrySet()) {
            output += "\n\t" + arg.getKey() + " Type: " + arg.getValue()[0].getClass().getSimpleName()
                    + " Default value: "
                    + arg.getValue()[0];
            if (arg.getValue().length > 1) {
                RangeCheck check = (RangeCheck) arg.getValue()[1];
                output += " Range: " + check.getRange();
            }
        }
        return output;
    }

}
