package services;

import constants.ExceptionMessage;
import constants.RomanianDigits;

import java.util.Arrays;

public class NumbersConverter {

    private int[] ints;

    // преобразование римского числа в арабское
    public int toArabian(String romanian) throws Exception {
        if (romanian.length() == 4) {
            if (romanian.equals(RomanianDigits.EIGHT)) {
                return 8;
            } else {
                throw new Exception(ExceptionMessage.WRONG_ROMANIAN_FORMAT);
            }
        } else if (romanian.length() == 3) {
            if (romanian.equals(RomanianDigits.THREE)) {
                return 3;
            } else if (romanian.equals(RomanianDigits.SEVEN)) {
                return 7;
            } else {
                throw new Exception(ExceptionMessage.WRONG_ROMANIAN_FORMAT);
            }
        } else {
            ints = new int[romanian.length()];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = doConverting(String.valueOf(romanian.charAt(i)));
            }

            int result;
            if (romanian.length() == 2) {
                checkFirstDigit();
            }

            result = Arrays.stream(ints).sum();

            if (result > 10) {
                throw new Exception(ExceptionMessage.MORE_THAN_TEN);
            }
            return  result;
        }
    }

    private void checkFirstDigit() throws Exception {
        if (ints[0] == 1 && ints[1] != 1) {
            ints[0] = Math.negateExact(ints[0]);
        } else if (ints[0] == 5 && ints[1] == 10) {
            throw new Exception(ExceptionMessage.WRONG_ROMANIAN_FORMAT);
        }
    }

    public int doConverting(String romanian) throws Exception {
        if (romanian.equals(RomanianDigits.ONE)) {
            return 1;
        } else if (romanian.equals(RomanianDigits.FIVE)) {
            return 5;
        } else if (romanian.equals(RomanianDigits.TEN)) {
            return 10;
        } else {
            throw new Exception();
        }
    }

    // в соответствии со статьей
    // https://ru.wikipedia.org/wiki/%D0%A0%D0%B8%D0%BC%D1%81%D0%BA%D0%B8%D0%B5_%D1%86%D0%B8%D1%84%D1%80%D1%8B
    public String toRomanian(int result) {
        int digits = (int) (Math.log10(result) + 1);
        String resultString;
        if (digits == 3) {
            return RomanianDigits.HUNDRED;
        } else if (digits == 2) {
            resultString = twoDigitsToRomanian(result);
        } else {
            resultString = oneDigitToRomanian(result);
        }

        return resultString;
    }

    private String twoDigitsToRomanian(int result) {
        StringBuilder resultString = new StringBuilder();

        if (result >= 90) {
            resultString.append(RomanianDigits.NINETY);
            result = result - 90;
        } else if (result >= 50) {
            resultString.append(RomanianDigits.FIFTY);
            result = result - 50;
        } else if (result >= 40) {
            resultString.append(RomanianDigits.FORTY);
            result = result - 40;
        }

        while (result >= 10) {
            resultString.append(RomanianDigits.TEN);
            result = result - 10;
        }

        resultString.append(oneDigitToRomanian(result));

        return String.valueOf(resultString);
    }

    private String oneDigitToRomanian(int result) {
        StringBuilder resultString = new StringBuilder();
        if (result == 9) {
            resultString.append(RomanianDigits.NINE);
            result = 0;
        } else if (result >= 5){
            resultString.append(RomanianDigits.FIVE);
            result = result - 5;
        } else if (result == 4) {
            resultString.append(RomanianDigits.FOUR);
            result = 0;
        }

        resultString.append(RomanianDigits.ONE.repeat(result));

        return String.valueOf(resultString);

    }
}
