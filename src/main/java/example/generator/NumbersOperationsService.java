package example.generator;

import example.BinaryOperator;
import example.NumberGenerator;
import example.UnaryOperator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumbersOperationsService {

    private final NumberGenerator firstOperandGenerator;
    private final NumberGenerator secondOperandGenerator;
    private final BinaryOperator operator;

    public int doOperation(UnaryOperator transformer) {
        int firstOperand = firstOperandGenerator.generate();
        int secondOperand = secondOperandGenerator.generate();
        int result = operator.apply(firstOperand, secondOperand);
        if (transformer == null) {
            return result;
        }
        return transformer.apply(result);
    }
}
