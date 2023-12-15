package App.Commands;

import java.util.LinkedHashMap;
import java.util.Map;

import App.Data.AppData;

public interface Command { // common command interface

    Map<String, Object[]> commandArgs = new LinkedHashMap<>();

    void execute(AppData data); // main execute method

    // get methods for all command attributes

    public int getCommandId();

    public String getCommandIdString();

    public String getCommandName();

    public String getCommandShort();

    public String getCommandInfo();

    public Map<String, Object[]> getCommandArgs();
}
