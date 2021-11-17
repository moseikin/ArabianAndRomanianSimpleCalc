package constants;

public class Constants {
    // паттерны
    public static final String ARABIC_PATTERN = "\\d{1,2}\\s[+-/*]\\s\\d{1,2}";
    public static final String ROMANIAN_PATTERN = "[IVX]{1,4}\\s[+-/*]\\s[IVX]{1,4}";
    public static final String ROMANIAN_THREE = "III";
    public static final String ROMANIAN_SEVEN = "VII";
    public static final String ROMANIAN_EIGHT = "VIII";

    // условия (flags)
    public static final String POSITIVE = "positive";
    public static final String NONE = "none";

    //exceptions
    public static final String WRONG_PATTERN = "Неправильный ввод";
    public static final String MORE_THAN_TEN = "Одно из чисел больше 10";
    public static final String WRONG_ROMANIAN_FORMAT = "Неправильный формат римских цифр";
    public static final String NOT_NATURAL_NUMBER = "Римское число не может быть меньше единицы";

    public static final String RESULT = "Результат: ";
}
