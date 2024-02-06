import jdk.swing.interop.SwingInterOpUtils;


import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше арифметическое выражение через пробел: ");
        String expression = scanner.nextLine();
        System.out.println(calc(expression));
    }

    public static String calc (String input) throws Exception {
        String[] tokens = input.split(" ");

        int num1;
        int num2;
        String result;
        char operator = tokens[1].charAt(0);
        boolean isRoman;
        String oper = detectOperation(input);

//проверка что два операнда и один оператор
        if(tokens.length != 3) {
            throw new Exception("Ошибка: должно быть два операнда и один оператор");
        }

        //если оба римские
        if(Roman.isRoman(tokens[0]) && Roman.isRoman(tokens[2])) {
            num1 = Roman.convertToArabian(tokens[0]);
            num2 = Roman.convertToArabian(tokens[2]);
            isRoman = true;
        }
        //если оба арабские
        else if (!Roman.isRoman(tokens[0]) && !Roman.isRoman(tokens[2])) {
            num1 = Integer.parseInt(tokens[0]);
            num2 = Integer.parseInt(tokens[2]);
            isRoman = false; //почему фолс?
        }
        //если одно римское а другое арабское
        else {
            throw new Exception("Ошибка: разные системы счисления");
        }

        //проверка что числа от 1 до 10
        if (num1 > 10 || num1 < 1 || num2 > 10 || num2 < 1) {
            throw new Exception("Калькулятор принимает числа от 1 до 10");
        }

//проверка что правильный оператор
        switch (operator){
            case '+':
                result = String.valueOf(num1 + num2);
                break;
            case '-':
                result = String.valueOf(num1 - num2);
                break;
            case '*':
                result = String.valueOf(num1 * num2);
                break;
            case '/':
                result = String.valueOf(num1 / num2);
                break;
            default:
                System.out.println("Неверная операция. Калькулятор принимает операции: +, -, *, /");
        }

        //конвертация ответа из арабских в римское
        int arabian = calc(num1, num2, oper);
        if(isRoman) {
            if (arabian <= 0) {
                throw new Exception("Ошибка: римское число не может быть меньше нуля");
            } result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    private static String detectOperation(String expression) {
        if (expression.contains("+")) {
            return "+";
        } else if (expression.contains("-")) {
            return "-";
        } else if (expression.contains("*")) {
            return "*";
        } else if (expression.contains("/")){
            return "/";
        } else {
            return null;
        }
    }

    static int calc (int a, int b, String oper) {
        if (oper.equals("+")) {
            return a+b;
        } else if (oper.equals("-")) {
            return a-b;
        } else if (oper.equals("*")) {
            return a*b;
        } else {
            return a/b;
        }
    }

    class Roman {
        //инициализируем массив из римских цифр
         static String[] romanArray = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                 "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                 "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                 "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                 "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                 "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                 "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                 "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                 "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                 "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCVIX", "C"};

         //проверяем что число римское
         public static boolean isRoman (String value) {
             for (int i = 0; i < romanArray.length; i++) {
                 if(value.equals(romanArray[i])) {
                     return true;
                 }
             } return false;
         }

         //конвертируем римское в арабское
         public static int convertToArabian(String roman) {
             for (int i = 0; i < romanArray.length; i++) {
                 if(roman.equals(romanArray[i])) {
                     return i;
                 }
             } return -1;
         }

         //конвертируем арабское в римское для вывода результата
         public static String convertToRoman(int arabian) {
             return romanArray[arabian];
         }
    }
}