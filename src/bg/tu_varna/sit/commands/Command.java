package bg.tu_varna.sit.commands;

public interface Command {
    String getName();
    String getDescription();
    void execute();
}
