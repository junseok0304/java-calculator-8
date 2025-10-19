package calculator.controller;

import calculator.domain.StringCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    public void run() {
        String input = InputView.readInput();

        input = input.replace("\\n", "\n");

        if (input.startsWith("//") && !input.contains("\n")) {
            String second = InputView.readNextLine();
            input = input + "\n" + second;
        }

        StringCalculator calculator = new StringCalculator();
        int result = calculator.add(input);
        OutputView.print(result);
    }
}
