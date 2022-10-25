import java.util.Scanner;

public class Main {
    public static String calc(String input) throws Exception {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        String[] strAr = input.split(" ");
        String ans = "";
        StringBuilder ansInArabic = new StringBuilder();
        boolean isArabicNumA = false;
        boolean isArabicNumB = false;

        if (strAr.length > 3) {
            throw new Exception("т.к. формат математической операции не удовлетворяет" +
                    " заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (strAr.length < 3) {
            throw new Exception("т.к. строка не является математической операцией");
        }

        if (!(strAr[1].contains("+")) && !(strAr[1].contains("-")) && !(strAr[1].contains("*")) && !(strAr[1].contains("/"))) {
            throw new Exception("т.к. нет подходящей математической операции");
        }

        String oper = strAr[1];

        for (ArabicNum ar : ArabicNum.values()) {
            if (ar.name().equals(strAr[0])) {
                isArabicNumA = true;
                break;
            }
        }
        for (ArabicNum ar : ArabicNum.values()) {
            if (ar.name().equals(strAr[0])) {
                isArabicNumB = true;
                break;
            }
        }
        if (!isArabicNumA && !isArabicNumB) {
            throw new Exception("т.к. введен неверный формат чисел");
        }
        if (isArabicNumA && isArabicNumB) {

            ArabicNum arabicNum1 = ArabicNum.valueOf(strAr[0]);
            ArabicNum arabicNum2 = ArabicNum.valueOf(strAr[2]);
            int a = arabicNum1.getValue();
            int b = arabicNum2.getValue();
            ans = operating(ans, oper, a, b);
            if (Integer.parseInt(ans) < 1) {
                throw new Exception("т.к. в римской системе нет отрицательных чисел");
            }
            if (Integer.parseInt(ans) > 10 && Integer.parseInt(ans) < 19) {
                ansInArabic.append("X");
                for (ArabicNum ar : ArabicNum.values()) {
                    if (ar.getValue() == Integer.parseInt(String.valueOf(ans.charAt(1)))) {
                        ansInArabic.append(ar.name());
                        return ansInArabic.toString();
                    }
                }

            }
            if (Integer.parseInt(ans) > 19 && Integer.parseInt(ans) < 50) {
                ansInArabic.append("X".repeat(Math.max(0, Integer.parseInt(ans) / 10)));
                return ansInArabic.toString();
            }
            if (Integer.parseInt(ans) > 51 && Integer.parseInt(ans) < 90) {
                ansInArabic.append("L");
                for (int i = (Integer.parseInt(ans) - 50); i > 9; i -= 10) {
                    ansInArabic.append("X");
                }
                return ansInArabic.toString();
            }
            if (Integer.parseInt(ans) == 50) {
                ansInArabic.append("L");
                return ansInArabic.toString();
            }

            if (Integer.parseInt(ans) == 90) {
                ansInArabic.append("XC");
                return ansInArabic.toString();
            }
            if (Integer.parseInt(ans) == 100) {
                ansInArabic.append("C");
                return ansInArabic.toString();
            }

            for (int i = 0; i < ans.length(); i++) {
                for (ArabicNum ar : ArabicNum.values()) {
                    if (ar.getValue() == Integer.parseInt(String.valueOf(ans.charAt(i)))) {
                        ansInArabic.append(ar.name());
                        break;

                    }
                }

            }
            return ansInArabic.toString();


        } else {
            try {
                int a = Integer.parseInt(strAr[0]);
                int b = Integer.parseInt(strAr[2]);
                ans = operating(ans, oper, a, b);

            } catch (Exception e) {
                throw new Exception("т.к. используются одновременно разные системы счисления");
            }

        }

        return ans;
    }

    static String operating(String ans, String oper, int a, int b) {
        switch (oper) {
            case "+" -> ans = String.valueOf(a + b);
            case "-" -> ans = String.valueOf(a - b);
            case "*" -> ans = String.valueOf(a * b);
            case "/" -> ans = String.valueOf(a / b);
        }
        return ans;
    }
}

enum ArabicNum {
    I(1), II(2), III(3), IV(4), V(5), VI(6),
    VII(7), VIII(8), IX(9), X(10);
    private final int value;

    ArabicNum(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }

}


