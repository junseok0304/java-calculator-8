package calculator.controller;

import calculator.domain.StringCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    public void run() {
        try {
            String input = InputView.readInput();

            StringCalculator calculator = new StringCalculator();
            int result = calculator.add(input);

            OutputView.print(result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
