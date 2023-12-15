package App.Commands.Get;

import App.Data.AppData;
import App.Data.Cycle;
import App.Data.User;

import java.util.List;

import App.Commands.CommandAbstract;

public class GetUser extends CommandAbstract {

    public GetUser() {

        this.inModuleId = 1;
        this.commandName = "get user";
        this.commandShort = "g u";
        this.commandInfo = "returns user location, rented cycles, total rented hours & total spending in euro.";
    }

    public void execute(AppData data) {

        User user = data.getUser();
        int hoursRented = user.getHoursRented();
        float rentPerHour = data.getRentPerHour();

        List<Cycle> cycles = user.getUserRentedCycles();

        System.out.println("\nUser: \n");
        System.out.println(user.toString());

        System.err.println("\nRented Cycles: ");

        for (Cycle cycle : cycles) {
            if (cycle.getIsRented()) {
                System.err.println(cycle.toString() + "\n");
            }
        }

        System.out.println("\nTotal rented Hours: " + hoursRented);

        System.err.println("\nTotal Bill: " + rentPerHour * hoursRented);

    }

}
