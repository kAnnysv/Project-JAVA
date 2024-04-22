package Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class Compute {
    static String oper;
    static int num1;
    static int num2;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        System.out.println(calc(input));


    }

    public static String calc(String input) {
        oper = Compute.operation(input);
        String result = null;
        String[] s = input.split("[+-/*]");
        if (s.length == 2) {
            if (checkRomOrArab(s)) {
                num1 = convertRomToArab(s[0]);
                num2 = convertRomToArab(s[1]);
                int res = calculated(num1, num2, oper);
                result = convertNumRoman(res);

            } else {
                num1 = Integer.parseInt(s[0]);
                num2 = Integer.parseInt(s[1]);
                if(num1 > 0 && num1 < 11 && num2 > 0 && num2 < 11){
                    result = String.valueOf(calculated(num1,num2, oper));
                }else {
                    throw new IllegalArgumentException("Не корректное значение чисел");
                }

            }
        } else {
            throw new IllegalArgumentException("Не корректное количество чисел");
        }

        return result;

    }

    public static String operation(String s) {
        if (s.contains("+")) {
            oper = "+";
        } else if (s.contains("-")) {
            oper = "-";
        } else if (s.contains("/")) {
            oper = "/";
        } else if (s.contains("*")) {
            oper = "*";
        }else {
            throw new IllegalArgumentException("Не корректный знак операции") ;
        }

        return oper;
    }

    public static int calculated(int num1, int num2, String oper) {
        int result = switch (oper) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "/" -> num1 / num2;
            case "*" -> num1 * num2;
            default -> 0;

        };
        return result;

    }

    private static String convertNumRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }

    private static int convertRomToArab(String rom){
        try {
            int result = switch (rom){
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" ->8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> -1;
            };
            return result;
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Некорректный формат данных");
        }
    }

    public static boolean checkRomOrArab(String[] s){
        int number1 = convertRomToArab(s[0]);
        int number2 = convertRomToArab(s[1]);
        return (number1 > 0 && number2 > 0);


    }

}
