package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_1012_유기농배추_S2 {
    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] grid = new int[N][M];
            boolean[][] isVisted = new boolean[N][M];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int col = Integer.parseInt(st.nextToken());
                int row = Integer.parseInt(st.nextToken());
                grid[row][col] = 1;
            }

            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 1 && !isVisted[i][j]) {
                        count++;
                        Queue<Point> q = new LinkedList<>();
                        q.add(new Point(i, j));
                        isVisted[i][j] = true;

                        while (!q.isEmpty()) {
                            Point point = q.poll();

                            for (int k = 0; k < 4; k++) {
                                int nr = point.row + dy[k];
                                int nc = point.col + dx[k];

                                if (isIn(nr, nc) && grid[nr][nc] == 1 && !isVisted[nr][nc]) {
                                    q.add(new Point(nr, nc));
                                    isVisted[nr][nc] = true;
                                }
                            }
                        }
                    }
                }
            }

            sb.append(count).append("\n");
        } // end of testCase
        System.out.print(sb.toString());
    } // end of main

    static boolean isIn(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < M;
    }
} // end of class
