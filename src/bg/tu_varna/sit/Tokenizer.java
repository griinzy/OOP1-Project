package bg.tu_varna.sit;


import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public static List<String> tokenize(String args) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentArg = new StringBuilder();

        boolean inQuotes = false;

        for (char c : args.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
                continue;
            }

            if (c == ' ') {
                if (inQuotes) {
                    currentArg.append(c);
                } else {
                    if (currentArg.length() > 0) {
                        tokens.add(currentArg.toString());
                        currentArg.setLength(0);
                    }
                }
            } else {
                currentArg.append(c);
            }
        }

        if (currentArg.length() > 0) {
            tokens.add(currentArg.toString());
        }

        return tokens;
    }
}
