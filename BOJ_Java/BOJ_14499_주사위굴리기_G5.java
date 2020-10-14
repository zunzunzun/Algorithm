package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14499_주사위굴리기_G5 {
    static int N;
    static int M;
    // 가로.
    static int[] hor = new int[4];
    // 세로.
    static int[] ver = new int[4];
    static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 동 서 북 남.
        int[] dx = {0, 1, -1, 0, 0};
        int[] dy = {0, 0, 0, -1, 1};
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());

            int nr = row + dy[dir];
            int nc = col + dx[dir];
            if (isIn(nr, nc)) {
                row = nr; col = nc;
                move(dir);

                System.out.println(hor[1]);
                if (grid[row][col] == 0) grid[row][col] = hor[3];
                else {
                    hor[3] = ver[3] = grid[row][col];
                    grid[row][col] = 0;
                }
            }
        }

    } // end of main

    public static void move(int dir) {
        // 동 서 북 남.
        if (dir == 1) {
            int temp = hor[3];
            for (int i = 3; i > 0; i--) {
                hor[i] = hor[i - 1];
            }
            hor[0] = temp;
            ver[1] = hor[1]; ver[3] = hor[3];
        } else if (dir == 2) {
            int temp = hor[0];
            for (int i = 0; i < 3; i++) {
                hor[i] = hor[i + 1];
            }
            hor[3] = temp;
            ver[1] = hor[1]; ver[3] = hor[3];
        } else if (dir == 3) {
            int temp = ver[0];
            for (int i = 0; i < 3; i++) {
                ver[i] = ver[i + 1];
            }
            ver[3] = temp;
            hor[1] = ver[1]; hor[3] = ver[3];
        } else {
            int temp = ver[3];
            for (int i = 3; i > 0; i--) {
                ver[i] = ver[i - 1];
            }
            ver[0] = temp;
            hor[1] = ver[1]; hor[3] = ver[3];
        }
    }

    public static boolean isIn(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < M;
    }

} // end of class
