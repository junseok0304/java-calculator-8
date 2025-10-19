package calculator.domain;

import java.util.Arrays;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        String[] stringNumbers = split(input);
        return sum(stringNumbers);
    }

    private String[] split(String input) {
        if (input.startsWith("//") && input.contains("\n")){
            int separatorIndex = input.indexOf("\n");
            String customDelimiter = input.substring(2, separatorIndex);
            String numberString = input.substring(separatorIndex + 1);
            return numberString.split(customDelimiter);
        }
        return input.split("[,:]");
    }

    private int sum(String[] stringNumbers) {
        return Arrays.stream(stringNumbers)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
