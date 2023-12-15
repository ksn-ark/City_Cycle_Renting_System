package App.Commands.Utiltities;

import App.Commands.Command;
import App.Data.AppData;

public class Exit extends Command { // exits the program

    public Exit() {
        this.inModuleId = 2;
        this.commandName = "exit";
        this.commandShort = "e";
        this.commandInfo = "Exits Program";
    }

    public void execute(AppData data) {
        System.err.println("\nExiting\n");
        System.exit(0);
    }
}
