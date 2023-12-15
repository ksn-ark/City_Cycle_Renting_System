package App.Commands.Get;

import App.Commands.Command;
import App.Data.AppData;
import App.Data.User;

public class GetUserHoursRented extends Command {

    public GetUserHoursRented() {
        this.inModuleId = 3;
        this.commandName = "get user hoursRented";
        this.commandShort = "g u h";
        this.commandInfo = "returns users total rented hours";
    }

    public void execute(AppData data) {

        User user = data.getUser();
        int hoursRented = user.getHoursRented();

        System.out.println("\nTotal hours rented by user: " + hoursRented);

    }

}