package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096_내려가기_G4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[3];
        // dp를 활용할 배열.
        int[][] max = new int[2][3];
        int[][] min = new int[2][3];
        StringTokenizer st;

        // 메모리가 제한되므로 입력이 주어지면 바로 계산한다.
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            // 나머지 연산자를 통해 배열 범위 밖을 넘어가지 않도록 하고, 저장해둔 dp 값을 이용해 다음 값을 구한다.
            min[i%2][0] = Math.min(min[(i+1)%2][0],min[(i+1)%2][1])+arr[0];
            min[i%2][1] = Math.min(min[(i+1)%2][2],Math.min(min[(i+1)%2][0],min[(i+1)%2][1]))+arr[1];
            min[i%2][2] = Math.min(min[(i+1)%2][2],min[(i+1)%2][1])+arr[2];

            max[i%2][0] = Math.max(max[(i+1)%2][0],max[(i+1)%2][1])+arr[0];
            max[i%2][1] = Math.max(max[(i+1)%2][2],Math.max(max[(i+1)%2][0],max[(i+1)%2][1]))+arr[1];
            max[i%2][2] = Math.max(max[(i+1)%2][2],max[(i+1)%2][1])+arr[2];
        }

        int ansMax = 0;
        int ansMin = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (ansMax < max[(n - 1) % 2][i]) ansMax = max[(n - 1) % 2][i];
            if (ansMin > min[(n - 1) % 2][i]) ansMin = min[(n - 1) % 2][i];
        }

        System.out.println(ansMax + " " + ansMin);
    } // end of main
} // end of class
