package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1072_게임_S3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());
        long z = 100 * y / x;

        // 패가 하나라도 있으면 승률을 100%로 만들수 없다.
        // 승률이 100% 여도 답을 구할 수 없다.
        if (z >= 99) {
            System.out.println(-1);
            return;
        }

        int l = 0;
        int r = 1000000000;
        int ans = 0;
        long temp = 0;

        // 이진탐색.
        while (l <= r) {
            int m = (l + r) / 2;
            temp = 100 * (y + m) / (x + m);

            if (z < temp) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        System.out.println(ans);
    } // end of main
} // end of class
