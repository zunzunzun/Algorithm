package Programmers;

public class Programmers_괄호변환_L2 {
    public static String solution(String p) {
        // 1.
        if (p.equals("")) return "";

        // 2.
        // 균형 잡힌 괄호 문자열인지 확인할 변수
        // (일 때는 +1을 )일때는 -1을 해서 확인한다.
        int pair = 0;
        // 올바른 문자열인지 아닌지 저장할 변수
        boolean flag = true;
        // u와 v를 나누어 줄 index
        int index = 0;
        for (; index < p.length(); index++) {
            char ch = p.charAt(index);

            if (ch == '(') pair++;
            else {
                if (--pair < 0) flag = false;
            }

            if (pair == 0) break;
        }

        String u = p.substring(0, ++index);
        String v = p.substring(index, p.length());

        // 3.
        // 올바른 괄호 문자열이라면
        if (flag) {
            // 3.1
            return u + solution(v);
        } else { // 4. 올바른 괄호 문자열이 아니라면
            StringBuilder sb = new StringBuilder();
            // 4.1, 4.2, 4.3
            sb.append('(').append(solution(v)).append(')');
            // 4.4
            for (int i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(') sb.append(')');
                else sb.append('(');
            }
            //4.5
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("(()())()"));
        System.out.println(solution(")("));
    }
}
