package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_12100_2048Easy_G2 {
    static int N;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(grid, 0);
        System.out.println(max);
    } // end of main

    public static void dfs(int[][] grid, int count) {
        if (count == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] > max) max = grid[i][j];
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] tempGrid = new int[N][N];
            for (int j = 0; j < N; j++) {
                tempGrid[j] = grid[j].clone();
            }

            move(tempGrid, i);
            dfs(tempGrid, count + 1);
        }
    }

    // 상 하 좌 우.
    public static void move(int[][] grid, int dir) {
        boolean[][] check = new boolean[N][N];

        if (dir == 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    int index = j;
                    while (index != 0) {
                        if (grid[index - 1][i] == 0) {
                            grid[index - 1][i] = grid[index][i];
                            grid[index][i] = 0;
                            index--;
                        } else if (grid[index - 1][i] == grid[index][i] && !check[index - 1][i]) {
                            grid[index - 1][i] = 2 * grid[index - 1][i];
                            grid[index][i] = 0;
                            check[index - 1][i] = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        } else if (dir == 1) {
            for (int i = 0; i < N; i++) {
                for (int j = N - 2; j >= 0; j--) {
                    int index = j;
                    while (index != N - 1) {
                        if (grid[index + 1][i] == 0) {
                            grid[index + 1][i] = grid[index][i];
                            grid[index][i] = 0;
                            index++;
                        } else if (grid[index + 1][i] == grid[index][i] && !check[index + 1][i]) {
                            grid[index + 1][i] = 2 * grid[index + 1][i];
                            grid[index][i] = 0;
                            check[index + 1][i] = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        } else if (dir == 2) {
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    int index = j;
                    while (index != 0) {
                        if (grid[i][index - 1] == 0) {
                            grid[i][index - 1] = grid[i][index];
                            grid[i][index] = 0;
                            index--;
                        } else if (grid[i][index - 1] == grid[i][index] && !check[i][index - 1]) {
                            grid[i][index - 1] = 2 * grid[i][index - 1];
                            grid[i][index] = 0;
                            check[i][index - 1] = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = N - 2; j >= 0; j--) {
                    int index = j;
                    while (index != N - 1) {
                        if (grid[i][index + 1] == 0) {
                            grid[i][index + 1] = grid[i][index];
                            grid[i][index] = 0;
                            index++;
                        } else if (grid[i][index + 1] == grid[i][index] && !check[i][index + 1]) {
                            grid[i][index + 1] = 2 * grid[i][index + 1];
                            grid[i][index] = 0;
                            check[i][index + 1] = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
} // end of class
