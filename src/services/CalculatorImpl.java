package services;

import interfaces.Calculator;

public class CalculatorImpl implements Calculator {
    int result;

    @Override
    public int addition(int a, int b) {
        return result = a + b;
    }

    @Override
    public int subtraction(int a, int b) {
        return result = a - b;
    }

    @Override
    public int multiplication(int a, int b) {
        return result = a * b;
    }

    @Override
    public int division(int a, int b) {
        return result = a / b;
    }
}
