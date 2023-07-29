package dictionary;

import enums.InputCommand;

import java.util.Dictionary;
import java.util.Hashtable;

public final class CommandDictionary {
    public static Dictionary<String, InputCommand> commandDictionary = new Hashtable<>(10);

    private CommandDictionary() {}

    public static void initCommandDictionary () {
        commandDictionary.put("exit", InputCommand.EXIT_COMMAND);
        commandDictionary.put("quit", InputCommand.EXIT_COMMAND);
        commandDictionary.put("close", InputCommand.EXIT_COMMAND);
        commandDictionary.put("play", InputCommand.BEGIN_PLAY_COMMAND);
        commandDictionary.put("surrender", InputCommand.SURRENDER_COMMAND);
        commandDictionary.put("ff", InputCommand.SURRENDER_COMMAND);
    }


}
