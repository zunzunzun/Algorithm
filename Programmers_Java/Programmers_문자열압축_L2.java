package Programmers;

public class Programmers_문자열압축_L2 {
    public static int solution(String s) {
        StringBuilder sb = new StringBuilder();
        int min = s.length();

        // 문자열 길이의 반 이후 부터는 압축을 할 수 없으니 초기값을 절반으로 잡는다.
        for (int i = s.length() / 2; i > 0; i--) {
            int start = i;
            int end = i * 2;
            int count = 1;

            String str = s.substring(0, i);
            while (start < s.length()) {
                // end가 넘어가면 남아 있는 문자열을 합친다.
                if (end > s.length()) {
                    if (count != 1) sb.append(count);
                    sb.append(str).append(s.substring(start, s.length()));
                    count = 0;
                    break;
                }

                // 전에 있는 문자열과 비교한다.
                String temp = s.substring(start, end);
                if (str.equals(temp)) {
                    count++;
                } else {
                    if (count != 1) sb.append(count);
                    sb.append(str);
                    str = temp;
                    count = 1;
                }
                start += i;
                end += i;
            }

            // 붙일 문자열이 있다면 붙인다.
            if (count != 0) {
                if (count != 1) sb.append(count);
                sb.append(str);
            }

            if (min > sb.length()) min = sb.length();
            sb.setLength(0);
        }

        return min;
    }

    public static void main(String[] args) {
//        System.out.println(solution("aabbaccc"));
        System.out.println(solution("a"));
    }
}
