package com.company;

import java.io.IOException;

public class Decision {

    public static String head (String text) throws IOException {
        if (text == null) {
            throw new IllegalArgumentException("Invalid data! Decision is null");
        }

        text = text.replaceAll(" ","").toUpperCase();

        String[] arrayOfDecision = toArray(text);
        String num1 = arrayOfDecision[0];
        String arithmeticSign = arrayOfDecision[1];
        String num2 = arrayOfDecision[2];

        return numbers(num1, arithmeticSign, num2);
    }


    public static String[] toArray(String text) throws IOException {
        String num1;
        String arithmeticSign;
        String num2;
        String[] arrayOfDecision = new String[3];
        char[] chars = text.toCharArray();

        try {
            num1 = String.valueOf(chars[0]);
            int i = 1;
            while (chars[i] != '+' && chars[i] != '-' &&
                    chars[i] != '*'&& chars[i] != '/') {
                num1 = num1.concat(String.valueOf(chars[i]));
                i++;
            }
            arithmeticSign = String.valueOf(chars[i]);
            i++;
            num2 = String.copyValueOf(chars, i,
                    chars.length - (num1.length() + arithmeticSign.length())) ;

            arrayOfDecision[0] = num1;
            arrayOfDecision[1] = arithmeticSign;
            arrayOfDecision[2] = num2;
            return arrayOfDecision;
        } catch (Exception e) {
            throw new IOException ("Invalid data");
        }
    }


    public static String numbers(String num1, String arithmeticSign, String num2) throws IOException {
        int number1;
        int number2;
        boolean arabic = true;

        try {
            if (Character.isLetter(num1.charAt(num1.length() - 1))) {
                arabic = false;
                number1 = NumberConvertor.romanToArabic(num1);
                number2 = NumberConvertor.romanToArabic(num2);
            } else {
                number1 = Integer.parseInt(num1);
                number2 = Integer.parseInt(num2);
            }
        } catch (Exception e) {
            throw new IOException ("Incorrect numbers");
        }
        if (number1 > 10 || number1 < 1 || number2 > 10 || number2 < 1) {
            throw new IOException ("Incorrect numbers");
        }

        int answer = mathOperation(number1, arithmeticSign, number2);

        if (arabic) {
            return String.valueOf(answer);
        } else {
            return NumberConvertor.arabicToRoman(answer);
        }
    }

    public static int mathOperation(int number1, String arithmeticSign, int number2) {
        int answer = 0;

        switch(arithmeticSign) {
            case "+": answer = number1 + number2;
                break;
            case "-": answer = number1 - number2;
                break;
            case "*": answer = number1 * number2;
                break;
            case "/": answer = number1 / number2;
                break;
        }
        return answer;
    }
}
