package services;

import constants.Constants;

import java.util.Arrays;

public class NumbersConverter {

    private int[] ints;

    public int toArabian(String romanian) throws Exception {
        if (romanian.length() == 4 & romanian.equals(Constants.ROMANIAN_EIGHT)) {
            return 8;
        } else if (romanian.length() == 3) {
            if (romanian.equals(Constants.ROMANIAN_THREE)) {
                return 3;
            } else if (romanian.equals(Constants.ROMANIAN_SEVEN)) {
                return 7;
            }
        } else {
            ints = new int[romanian.length()];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = doConverting(String.valueOf(romanian.charAt(i)));
            }

            int result;
            if (romanian.length() == 2) {
                checkTwoDigits();
            }

            result = Arrays.stream(ints).sum();

            if (result > 10) {
                throw new Exception(Constants.MORE_THAN_TEN);
            }
            return  result;
        }
        return 0;
    }

    private void checkTwoDigits() throws Exception {
        if (ints[0] == 1 && ints[1] != 1) {
            ints[0] = Math.negateExact(ints[0]);
        } else if (ints[0] == 5 && ints[1] == 10) {
            throw new Exception(Constants.WRONG_ROMANIAN_FORMAT);
        }
    }

    public int doConverting(String romanian) throws Exception {
        if (romanian.equals("I")) {
            return 1;
        } else if (romanian.equals("V")) {
            return 5;
        } else if (romanian.equals("X")) {
            return 10;
        } else {
            throw new Exception();
        }
    }


    public String toRomanian(int result) {
        int digits = (int) (Math.log10(result) + 1);
        String resultString;
        if (digits == 3) {
            return "C";
        } else if (digits == 2) {
            resultString = twoDigitsToRomanian(result);
        } else {
            resultString = oneDigitToRomanian(result);
        }

        return resultString;
    }

    private String twoDigitsToRomanian(int result) {
        StringBuilder resultString = new StringBuilder();
        if (result >= 50) {
            resultString.append("L");
            result = result - 50;
        }

        while (result >= 10) {
            resultString.append("X");
            result = result - 10;
        }

        resultString.append(oneDigitToRomanian(result));

        return String.valueOf(resultString);
    }

    private String oneDigitToRomanian(int result) {
        StringBuilder resultString = new StringBuilder();
        if (result == 9) {
            resultString.append("IX");
            result = 0;
        } else if (result >= 5){
            resultString.append("V");
            result = result - 5;
        } else if (result == 4) {
            resultString.append("IV");
            result = 0;
        }

        resultString.append("I".repeat(result));

        return String.valueOf(resultString);

    }
}
