package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //ввод команды
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String value = calc(input);
        System.out.println(value);

    }

    public static String calc(String input) {

        int numA = 0;
        int numB = 0;
        char oper = '0';
        boolean UseNumbers;
        int ans = 0;

        Scanner scanner = new Scanner(input);

        if (scanner.hasNextInt()) {
            UseNumbers = true;
            numA = scanner.nextInt();
        } else {
            UseNumbers = false;
            RomanNum RomanNumA = RomanNum.valueOf(scanner.next());
            numA = RomanNumA.getNum();
        }

        if (scanner.hasNext()) {
            oper = scanner.next().charAt(0);
        } else {
            throw new RuntimeException("строка не является математической операцией");
        }


        if (scanner.hasNextInt() && UseNumbers) {
            //второе число тоже арабское
            numB = scanner.nextInt();
        } else if ((scanner.hasNextInt() && !UseNumbers) || UseNumbers) {
            // второе число арабское, когда мы используем римские
            throw new RuntimeException("используются одновременно разные системы счисления");
        } else if (scanner.hasNext()) {
            RomanNum RomanNumB = RomanNum.valueOf(scanner.next());
            numB = RomanNumB.getNum();
        } else {
            throw new RuntimeException("строка не является математической операцией");
        }

        if (scanner.hasNext()) {
            throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        switch (oper) {
            case '+':
                ans = numA + numB;
                break;
            case '-':
                if ((numA < numB) && !UseNumbers) {
                    throw new RuntimeException("в римской системе нет отрицательных чисел");
                } else {
                    ans = numA - numB;
                }
                break;
            case '*':
                ans = numA * numB;
                break;
            case '/':
                ans = numA / numB;
                break;
        }
        if (!UseNumbers) {
            return RomanStr(ans);
        } else {
            return String.valueOf(ans);
        }

    }

    public static String RomanStr(int num){

        RomanNum[] m = RomanNum.values();
        String strNum = "";
        int value;
        int x;
        int y;

        for (int i = m.length - 1; i >= 0 || num == 0; i--) {

            for (RomanNum roman : RomanNum.values()) {
                if (roman.getNum() == num) {
                    strNum += roman;
                    num = 0;
                    break;
                }
            }
            if (num == 0){
                break;
            }

            System.out.println();
            value = m[i].getNum();

            if (num == value) {
                strNum += String.valueOf(m[i]);
                num -= value;
            } else if (num > value) {
                x = num / value;
                num = num % value;

                if (x == 4){
                    strNum += m[i] + String.valueOf(m[i+1]);
                } else {
                    strNum += String.valueOf(m[i]).repeat(x);
                }
            }
            System.out.println(strNum);
        }
        return strNum;
    }
}
