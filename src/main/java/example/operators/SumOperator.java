package example.operators;

import example.BinaryOperator;

public class SumOperator implements BinaryOperator {
    @Override
    public int apply(int x, int y) {
        return x + y;
    }
}
