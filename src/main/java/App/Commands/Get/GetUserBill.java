package App.Commands.Get;

import App.Commands.Command;
import App.Data.AppData;
import App.Data.User;

public class GetUserBill extends Command {

    public GetUserBill() {
        this.inModuleId = 4;
        this.commandName = "get user bill";
        this.commandShort = "g u b";
        this.commandInfo = "returns user total spending (in euro)";
    }

    public void execute(AppData data) {

        User user = data.getUser();

        float rentPerHour = data.getRentPerHour();

        int hoursRented = user.getHoursRented();

        System.out.println("\nUser rent Bill: " + rentPerHour * hoursRented);

    }

}
