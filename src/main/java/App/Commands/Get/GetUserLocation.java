package App.Commands.Get;

import App.Commands.CommandAbstract;
import App.Data.AppData;
import App.Data.User;

public class GetUserLocation extends CommandAbstract {

    public GetUserLocation() {
        this.inModuleId = 2;
        this.commandName = "get user location";
        this.commandShort = "g u l";
        this.commandInfo = "returns user location.";
    }

    public void execute(AppData data) {
        User user = data.getUser();

        System.out.println("\nUser location is at x: " + user.getX() + " and y: " + user.getY());
    }

}