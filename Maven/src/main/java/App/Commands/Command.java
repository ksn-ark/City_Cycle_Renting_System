package App.Commands;

import java.util.LinkedHashMap;
import java.util.Map;

import App.Data.AppData;

public interface Command {

    Map<String, Object[]> commandArgs = new LinkedHashMap<>();

    void execute(AppData data);

    public int getCommandId();

    public String getCommandIdString();

    public String getCommandName();

    public String getCommandShort();

    public String getCommandInfo();

    public Map<String, Object[]> getCommandArgs();
}
