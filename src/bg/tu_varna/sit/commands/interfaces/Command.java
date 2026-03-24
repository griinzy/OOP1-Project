package bg.tu_varna.sit.commands.interfaces;

public interface Command {
    String getName();
    String getDescription();
    void execute(String[] args);
}
