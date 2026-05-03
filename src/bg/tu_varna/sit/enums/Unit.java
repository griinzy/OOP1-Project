package bg.tu_varna.sit.enums;

public enum Unit {
    KG,
    LITRE;

    public static Unit parse(String input) {
        return switch (input.toLowerCase()) {
            case "kg" -> KG;
            case "l", "litre" -> LITRE;
            default -> throw new IllegalStateException("Unexpected unit: " + input);
        };
    }
}
