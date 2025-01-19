package code;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Converter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();
    Map<String, Integer> numberMap = new HashMap<>();

    public Converter() {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);
        romanKeyMap.put('D', 500);
        romanKeyMap.put('M', 1000);

        arabianKeyMap.put(1000, "M");
        arabianKeyMap.put(900, "CM");
        arabianKeyMap.put(500, "D");
        arabianKeyMap.put(400, "CD");
        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");


        numberMap.put("I", 1);
        numberMap.put("II", 2);
        numberMap.put("III", 3);
        numberMap.put("IV", 4);
        numberMap.put("V", 5);
        numberMap.put("VI", 6);
        numberMap.put("VII", 7);
        numberMap.put("VIII", 8);
        numberMap.put("IX", 9);
        numberMap.put("X", 10);


    }

    boolean isRoman(String number) {
        return numberMap.containsKey(number);
    }

    String intToRoman(int number) {
        StringBuilder roman = new StringBuilder();
        int arabianKey;
        do {
            arabianKey = arabianKeyMap.floorKey(number);
            roman.append(arabianKeyMap.get(arabianKey));
            number -= arabianKey;
        } while (number != 0);
        return roman.toString();
    }

    public int romanToInt(String s) {
        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int arabian;
        int result = romanKeyMap.get(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            arabian = romanKeyMap.get(arr[i]);

            if (arabian < romanKeyMap.get(arr[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }

        return result;
    }
}