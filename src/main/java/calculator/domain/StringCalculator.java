package calculator.domain;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        input = input.replace("\\n", "\n");

        String[] stringNumbers = split(input);
        return sum(stringNumbers);
    }

    private String[] split(String input) {
        Matcher matcher = Pattern.compile("//\\[(.*?)]\n(.*)", Pattern.DOTALL).matcher(input);
        if (matcher.matches()) {
            String customDelimiter = matcher.group(1);
            String numberString = matcher.group(2);
            return numberString.split(Pattern.quote(customDelimiter), -1);
        }

        if (input.startsWith("//") && input.contains("\n")) {
            int separatorIndex = input.indexOf("\n");
            String customDelimiter = input.substring(2, separatorIndex);
            String numberString = input.substring(separatorIndex + 1);
            return numberString.split(Pattern.quote(customDelimiter), -1);
        }

        return input.split("[,:]", -1);
    }

    private int sum(String[] stringNumbers) {
        return Arrays.stream(stringNumbers)
                .mapToInt(this::parseAndValidateNumber)
                .sum();
    }

    private int parseAndValidateNumber(String stringNumber) {
        String trimmed = stringNumber.trim();

        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("구분자 뒤 값이 비어있음");
        }

        try {
            int number = Integer.parseInt(trimmed);
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
