package bg.tu_varna.sit.commands.interfaces;

public interface Command {
    String getName();
    String getDescription();
    String execute(String[] args);
}
