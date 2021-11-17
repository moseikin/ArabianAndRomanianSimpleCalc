package services;

import constants.Constants;
import interfaces.Calculator;
import pojo.Arguments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParsing {
    private final Calculator calculator = new CalculatorImpl();
    private final NumbersConverter converter = new NumbersConverter();
    private final Pattern arabic = Pattern.compile(Constants.ARABIC_PATTERN);
    private final Pattern romanian = Pattern.compile(Constants.ROMANIAN_PATTERN);

    public void input(String data) throws Exception {
        Matcher matcherArabic = arabic.matcher(data);
        Matcher matcherRomanian = romanian.matcher(data);
        if (matcherArabic.matches()) {
            parseArabianExp(data);
        } else if (matcherRomanian.matches()){
            parseRomanianExp(data);
        } else {
            throw new Exception(Constants.WRONG_PATTERN);
        }
    }

    private void parseArabianExp(String data) throws Exception {
        Arguments arguments = new Arguments();
        arguments.setFirstArg(Integer.parseInt(extractFirstArgument(data)));
        arguments.setSecondArg(Integer.parseInt(extractSecondArgument(data)));

        defineMathAction(data, arguments, Constants.NONE);
    }

    private void parseRomanianExp(String data) throws Exception {

        String firstArgString = extractFirstArgument(data);
        String secondArgString = extractSecondArgument(data);
        Arguments arguments = new Arguments();
        arguments.setFirstArg(converter.toArabian(firstArgString));
        arguments.setSecondArg(converter.toArabian(secondArgString));

        defineMathAction(data, arguments, Constants.POSITIVE);
    }

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

    private void output(int result, String flag) throws Exception {
        if (flag.equals(Constants.POSITIVE)) {
            if (result < 1) {
                throw new Exception(Constants.NOT_NATURAL_NUMBER);
            }
            String resultString = converter.toRomanian(result);
            System.out.println(Constants.RESULT + resultString);
        } else {
            System.out.println(Constants.RESULT + result);
        }
    }


}
