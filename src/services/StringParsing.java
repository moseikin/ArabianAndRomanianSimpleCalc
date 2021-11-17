package services;

import constants.Constants;
import constants.ExceptionMessage;
import interfaces.Calculator;
import pojo.Arguments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParsing {
    private final Calculator calculator = new CalculatorImpl();
    private final NumbersConverter converter = new NumbersConverter();
    private final Pattern arabic = Pattern.compile(Constants.ARABIC_PATTERN);
    private final Pattern romanian = Pattern.compile(Constants.ROMANIAN_PATTERN);

    // соответствует ли введенная строка правилам ввода
    public void input(String data) throws Exception {
        Matcher matcherArabic = arabic.matcher(data);
        Matcher matcherRomanian = romanian.matcher(data);
        if (matcherArabic.matches()) {
            parseArabianExp(data);
        } else if (matcherRomanian.matches()){
            parseRomanianExp(data);
        } else {
            throw new Exception(ExceptionMessage.WRONG_PATTERN);
        }
    }

    // из строки вытаскиваем первый и второй аргуметн и помещаем его в pojo
    private void parseArabianExp(String data) throws Exception {
        Arguments arguments = new Arguments();
        int firstArg = Integer.parseInt(extractFirstArgument(data));
        if (firstArg > 10) {
            throw new Exception(ExceptionMessage.MORE_THAN_TEN);
        } else {
            arguments.setFirstArg(firstArg);
        }
        int secondArg = Integer.parseInt(extractSecondArgument(data));
        if (secondArg > 10) {
            throw new Exception(ExceptionMessage.MORE_THAN_TEN);
        } else {
            arguments.setSecondArg(secondArg);
        }

        defineMathAction(data, arguments, Constants.NONE);
    }

    // вытаскиваем из строки первое и второе римские числа ,
    // передаем их для преобразования в арабские числа
    private void parseRomanianExp(String data) throws Exception {
        String firstArgString = extractFirstArgument(data);
        String secondArgString = extractSecondArgument(data);
        Arguments arguments = new Arguments();
        arguments.setFirstArg(converter.toArabian(firstArgString));
        arguments.setSecondArg(converter.toArabian(secondArgString));

        defineMathAction(data, arguments, Constants.POSITIVE);
    }

    // определяем математическое действие и передаем данные в калькулятор
    private void defineMathAction(String data, Arguments arguments, String flag) throws Exception {
        int result = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '+') {
                result = calculator.addition(arguments.getFirstArg(), arguments.getSecondArg());
            } else if (data.charAt(i) == '-') {
                result = calculator.subtraction(arguments.getFirstArg(), arguments.getSecondArg());
            } else if (data.charAt(i) == '*') {
                result = calculator.multiplication(arguments.getFirstArg(), arguments.getSecondArg());
            } else if (data.charAt(i) == '/') {
                result = calculator.division(arguments.getFirstArg(), arguments.getSecondArg());
            }
        }

        output(result, flag);
    }

    private String extractFirstArgument(String data) {
        int index = data.indexOf(" ");
        return data.substring(0, index);
    }

    private String extractSecondArgument(String data) {
        int index = data.lastIndexOf(" ");
        return data.substring(index + 1);
    }

    // вывод результата на печать в соответствии с флагом: римское число >= 1
    private void output(int result, String flag) throws Exception {
        if (flag.equals(Constants.POSITIVE)) {
            if (result < 1) {
                throw new Exception(ExceptionMessage.NOT_NATURAL_NUMBER);
            }
            String resultString = converter.toRomanian(result);
            System.out.println(Constants.RESULT + resultString);
        } else {
            System.out.println(Constants.RESULT + result);
        }
    }


}
