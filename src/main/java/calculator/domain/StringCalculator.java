package calculator.domain;

import java.util.Arrays;
import java.util.regex.Pattern;

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

            return numberString.split(Pattern.quote(customDelimiter));
        }
        return input.split("[,:]");
    }

    private int sum(String[] stringNumbers) {
        return Arrays.stream(stringNumbers)
                .mapToInt(this::parseAndValidateNumber)
                .sum();
    }

    private int parseAndValidateNumber(String stringNumber) {
        try {
            int number = Integer.parseInt(stringNumber.trim());
            validateNonNegative(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값에 문자가 포함됨.");
        }
    }

    private void validateNonNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없음.");
        }
    }
}
