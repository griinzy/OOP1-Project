package bg.tu_varna.sit.enums;

public enum CommandType {
    ADD,
    REMOVE,
    CLEAN,
    LOG,
    PRINT,
    HELP,
    EXIT,
    OPEN,
    CLOSE,
    SAVE,
    SAVE_AS;

    @Override
    public String toString() {
        return name().toLowerCase().replace("_", " ");
    }
}
