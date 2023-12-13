package Data;

import java.util.*;

import Commands.Command;
import Commands.Help;
import Commands.Adddel.AddCycle;
import Commands.Adddel.DeleteCycleArea;
import Commands.Adddel.DeleteCycleId;
import Commands.Get.GetCycle;

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

    static { // mapping the command short hands to their long forms
        Map<String, String> shm1 = new HashMap<>();

        shm1.put("h", "Help");
        shm1.put("a", "Add");
        shm1.put("d", "Delete");
        shm1.put("u", "Update");
        shm1.put("g", "Get");
        shm1.put("r", "Rent");

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

        Map<String, Command> m = new HashMap<>();

        m.put("AddCycle", new AddCycle());
        m.put("DeleteCycleArea", new DeleteCycleArea());
        m.put("DeleteCycleId", new DeleteCycleId());
        m.put("GetCycle", new GetCycle());
        m.put("Help", new Help());

        commandMap = m;

    }

    public static List<Map<String, String>> getShortHandMaps() {
        return shortHandMaps;
    }

    public static Map<String, Command> getCommandMap() {
        return commandMap;
    }
}
