import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter your math expression.\nExample: V + V\n1 <= a,b <= 10: ");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        String [] operands = expression.split(" ");
        boolean arabic = isArabic(operands[0]);
        if ( (isArabic(operands[0]) && isArabic(operands[2])) || (!isArabic(operands[0]) && !isArabic(operands[2])) ) {
            int a, b;

            if (arabic) {
                a = Integer.parseInt(operands[0]);
                b = Integer.parseInt(operands[2]);
                if (a > 10 || a < 1 || b > 10 || b < 1) {
                    System.out.println("a or b not in available values.");
                    return;
                }
                switch (operands[1]) {
                    case "+" -> System.out.println(Calc.sum(a, b));
                    case "-" -> System.out.println(Calc.deduct(a, b));
                    case "*" -> System.out.println(Calc.multiply(a, b));
                    case "/" -> System.out.println(Calc.divide(a, b));
                    default -> System.out.println("throws Exception");
                }
            } else {
                try {
                    a = RomanToInteger.romanToInteger(operands[0]);
                    b = RomanToInteger.romanToInteger(operands[2]);
                } catch (NullPointerException e) {
                    System.out.println("throws Exception");
                    return;
                }
                if (a > 10 || a < 1 || b > 10 || b < 1) {
                    System.out.println("a or b not in available values.");
                    return;
                }
                switch (operands[1]) {
                    case "+" -> System.out.println(IntegerConverter.intToRoman(Calc.sum(a, b)));
                    case "-" -> System.out.println(IntegerConverter.intToRoman(Calc.deduct(a, b)));
                    case "*" -> System.out.println(IntegerConverter.intToRoman(Calc.multiply(a, b)));
                    case "/" -> System.out.println(IntegerConverter.intToRoman(Calc.divide(a, b)));
                    default -> System.out.println("throws Exception");
                }
            }
        } else System.out.println("throws Exception");
    }

    public static boolean isArabic(String num) {
        String [] arabics = {"1","2","3","4","5","6","7","8","9","10"};
        for (String arabic : arabics) {
            if (arabic.equals(num)) return true;
        }
        return false;
    }
}

class Calc {
    static int sum(int a, int b) {
        return a + b;
    }
    static int deduct(int a, int b) {
        return a - b;
    }
    static int multiply(int a, int b) {
        return a * b;
    }
    static int divide (int a, int b) {
        return a / b;
    }
}


class IntegerConverter {

    public static String intToRoman(int number) {
        if (number >= 4000 || number <= 0)
            return "throws Exception";
        StringBuilder result = new StringBuilder();
        for(Integer key : units.descendingKeySet()) {
            while (number >= key) {
                number -= key;
                result.append(units.get(key));
            }
        }
        return result.toString();
    }

    private static final NavigableMap<Integer, String> units;
    static {
        NavigableMap<Integer, String> initMap = new TreeMap<>();
        initMap.put(1000, "M");
        initMap.put(900, "CM");
        initMap.put(500, "D");
        initMap.put(400, "CD");
        initMap.put(100, "C");
        initMap.put(90, "XC");
        initMap.put(50, "L");
        initMap.put(40, "XL");
        initMap.put(10, "X");
        initMap.put(9, "IX");
        initMap.put(5, "V");
        initMap.put(4, "IV");
        initMap.put(1, "I");
        units = Collections.unmodifiableNavigableMap(initMap);
    }
}

class RomanToInteger {
    public static int romanToInteger(String roman) {
        Map<Character, Integer> numbersMap = new HashMap<>();
        numbersMap.put('I', 1);
        numbersMap.put('V', 5);
        numbersMap.put('X', 10);
        numbersMap.put('L', 50);
        numbersMap.put('C', 100);
        numbersMap.put('D', 500);
        numbersMap.put('M', 1000);

        int result = 0;

        for (int i = 0; i < roman.length(); i++) {
            char ch = roman.charAt(i);      // Current Roman Character

            //Case 1
            if (i > 0 && numbersMap.get(ch) > numbersMap.get(roman.charAt(i - 1))) {
                result += numbersMap.get(ch) - 2 * numbersMap.get(roman.charAt(i - 1));
            }

            // Case 2: just add the corresponding number to result.
            else
                result += numbersMap.get(ch);
        }

        return result;
    }
}