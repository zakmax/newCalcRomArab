package code;

import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        System.out.println(calc(expression.replaceAll("\\s+", "")));

    }

    private static String calc(String input) throws Exception {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};



        if (input.length() == 1) {
            throw new Exception("т.к. строка не является математической операцией");

        }
        // определяем арифметич действия
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        // если не нашли арифметич действия конец программы
        if (actionIndex == -1) {
            throw new Exception("не является математической операцией");
        }


        // "2+4".split("\\+")-> {"2, 4"} делим строчку по найденному арифметич знаку
        String[] data = input.split(regexActions[actionIndex]);

        //проверка на целое число
        if (data[0].contains(".") || data[1].contains(".")) {
            throw new Exception("Числа должны быть целыми.");
        }
        if (data[0].contains(",") || data[1].contains(",")) {
            throw new Exception("Числа должны быть целыми.");
        }
        if (data.length != 2)
            throw new Exception(
                    "т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        String resultString;
        // определяем в одном формате ли числа
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {

            int a, b;

            // определяем римские ли числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {

                //если римск то конверт-ем в араб-ие
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            } else {
                // конвертируем арабские из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);

                // проверка от 1 до 10
                if (a < 1 || a > 10 || b < 1 || b > 10) {
                    throw new Exception("Числа должны быть от 1 до 10 включительно.");
                }
            }
            // выполняем арифметич дейтвия
            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            if (isRoman) {
                if (result <= 0) {
                    throw new Exception(
                            "в римской системе нет отрицательных чисел");
                    //если римские возвращаем в римские
                }
                resultString = converter.intToRoman(result);
                // проверка от 1 до 10
                if (a < 1 || a > 10 || b < 1 || b > 10) {
                    throw new Exception("Числа должны быть от 1 до 10 включительно.");
                }
            } else {

                //если числа арабиан возвращаем арабские
                resultString = String.valueOf(result);
            }
        } else {
            throw new Exception("т.к. используются одновременно разные системы счисления");

        }

        return resultString;
    }
}