package enums;

public enum InputCommand {
    EXIT_COMMAND(-1),
    BEGIN_PLAY_COMMAND(0),
    SURRENDER_COMMAND (1);

    public final Integer commandCode;
    InputCommand(Integer commandCode) {
        this.commandCode = commandCode;
    }

    public Integer getCommandCode() {
        return commandCode;
    }

}
