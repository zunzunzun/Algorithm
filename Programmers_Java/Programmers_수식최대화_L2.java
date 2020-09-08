package Programmers;

import java.util.*;

public class Programmers_수식최대화_L2 {
    // *, +, - 이 3개만 나온다. 3!을 해도 경우의 수가 그렇게 크지 않으니 처음부터 정해둔다.
    static String[][] priorityOperators = {
            {"*", "+", "-"},
            {"*", "-", "+"},
            {"+", "*", "-"},
            {"+", "-", "*"},
            {"-", "*", "+"},
            {"-", "+", "*"}
    };

    public static long solution(String expression) {
        // 제한 조건에 따른 long 타입 선언
        long max = 0;

        // 피연사자와 연산자를 나누어서 저장될 list
        LinkedList<String> list = new LinkedList<>();

        int start, end;
        for (start = end = 0; end < expression.length(); end++) {
            char ch = expression.charAt(end);

            if (ch == '*' || ch == '+' || ch == '-') {
                String str = expression.substring(start, end);
                list.add(str);
                list.add(String.valueOf(ch));
                start = ++end;
            }
        }

        list.add(expression.substring(start, end));

        // 미리 선언한 우선순위에 따른 모든 연산결과를 비교해서 가장 큰 값을 저장한다.
        for (String[] operators : priorityOperators) {
            // list를 두개로 나누어 관리한다.
            // tempList1은 연산해야 할 식을 담아둘 list이다.
            // tempList2은 1에서 연산된 결과를 임시로 담아둘 list이다.
            LinkedList<String> tempList1 = new LinkedList<>();
            for (String str : list) {
                tempList1.add(new String(str));
            }
            LinkedList<String> tempList2 = new LinkedList<>();

            // 연산자에 따라서 연산한다.
            for (String operator : operators) {
                for (int i = 0; i < tempList1.size(); i++) {
                    String item = tempList1.get(i);

                    if (item.equals(operator)) {
                        char ch = item.charAt(0);
                        long operand1, operand2;
                        switch (ch) {
                            case '+':
                                operand1 = Long.parseLong(tempList2.pollLast());
                                operand2 = Long.parseLong(tempList1.get(++i));
                                tempList2.add(String.valueOf(operand1 + operand2));
                                break;
                            case '-':
                                operand1 = Long.parseLong(tempList2.pollLast());
                                operand2 = Long.parseLong(tempList1.get(++i));
                                tempList2.add(String.valueOf(operand1 - operand2));
                                break;
                            case '*':
                                operand1 = Long.parseLong(tempList2.pollLast());
                                operand2 = Long.parseLong(tempList1.get(++i));
                                tempList2.add(String.valueOf(operand1 * operand2));
                                break;
                        }

                    } else {
                        tempList2.add(item);
                    }
                }
                // 연산이 끝났으니 임시로 담아두었던 tempList2를 다시 tempList1으로 옮기고
                // 새로운 값을 담아야 하니 tempList2를 비운다.
                tempList1 = tempList2;
                tempList2 = new LinkedList<>();
            }

            // 모든 연산자를 연산했으니 최고값과 비교한다.
            long temp = Math.abs(Long.parseLong(tempList1.getFirst()));
            if (temp > max) max = temp;
        }

        return max;
    }

    public static void main(String[] args) {
//        System.out.println(solution("100-200*300-500+20"));
        System.out.println(solution("50*6-3*2"));
    }
}
