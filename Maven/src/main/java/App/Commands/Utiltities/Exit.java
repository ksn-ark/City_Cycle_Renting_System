package App.Commands.Utiltities;

import java.util.Map;

import App.Commands.Command;
import App.Data.AppData;

public class Exit implements Command {

    public void execute(AppData data) {
        System.err.println("\nExiting\n");
        System.exit(0);
    }

    @Override
    public int getCommandId() {
        throw new UnsupportedOperationException("Exit has no such values");
    }

    @Override
    public String getCommandIdString() {
        throw new UnsupportedOperationException("Exit has no such values");
    }

    @Override
    public String getCommandName() {
        throw new UnsupportedOperationException("Exit has no such values");
    }

    @Override
    public String getCommandShort() {
        throw new UnsupportedOperationException("Exit has no such values");
    }

    @Override
    public String getCommandInfo() {
        throw new UnsupportedOperationException("Exit has no such values");
    }

    @Override
    public Map<String, Object[]> getCommandArgs() {
        throw new UnsupportedOperationException("Exit has no such values");
    }
}
