package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_13460_구슬탈출2_G2 {
    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean isSame(Point point) {
            if (this.row == point.row && this.col == point.col) return true;
            else return false;
        }
    }
    static int n;
    static int m;
    static int min = 11;
    static Point hole;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char[][] grid = new char[n][m];

        Point red = new Point(0, 0);
        Point blue = new Point(0, 0);
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = input.charAt(j);
                if (input.charAt(j) == 'R') red = new Point(i, j);
                else if (input.charAt(j) == 'B') blue = new Point(i, j);
                else if (input.charAt(j) == 'O') hole = new Point(i, j);
            }
        }

        dfs(grid, red, blue, 0);

        System.out.println(min == 11 ? -1 : min);
    } // end of main

    public static void dfs(char[][] grid, Point red, Point blue, int count) {
        if (count == 11) return;

        // 상 하 좌 우로 한번씩 돌려본다.
        for (int i = 0; i < 4; i++) {
            char[][] tempGrid = new char[n][m];
            for (int j = 0; j < n; j++) {
                tempGrid[j] = grid[j].clone();
            }

            Point tempRed = new Point(red.row, red.col);
            Point tempBlue = new Point(blue.row, blue.col);

            if (move(tempGrid, tempRed, tempBlue, i)) {
                // 파랑공이 구멍에 안빠졌을 경우.
                if (!hole.isSame(tempBlue)) {
                    // 빨간공이 구멍이면 탐색 끝.
                    if (hole.isSame(tempRed)) {
                        if (min > count + 1) min = count + 1;
                    } else { // 아니라면 계속 탐색.
                        dfs(tempGrid, tempRed, tempBlue, count + 1);
                    }
                }
            }
        }
    }

    public static boolean move(char[][] grid, Point red, Point blue, int dir) {
        Point tempRed = new Point(red.row, red.col);
        Point tempBlue = new Point(blue.row, blue.col);

        // 상하좌우.
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (true) {
            char ch = grid[red.row + dy[dir]][red.col + dx[dir]];
            if (ch == '#') break;
            red.row = red.row + dy[dir];
            red.col = red.col + dx[dir];
            if (ch == 'O') break;
        }
        while (true) {
            char ch = grid[blue.row + dy[dir]][blue.col + dx[dir]];
            if (ch == '#') break;
            blue.row = blue.row + dy[dir];
            blue.col = blue.col + dx[dir];
            if (ch == 'O') break;
        }

        if (red.isSame(blue) && !red.isSame(hole)) {
            if (dir == 0) {
                if (tempRed.row < tempBlue.row) blue.row++;
                else red.row++;
            } else if (dir == 1) {
                if (tempRed.row < tempBlue.row) red.row--;
                else blue.row--;
            } else if (dir == 2) {
                if (tempRed.col < tempBlue.col) blue.col++;
                else red.col++;
            } else {
                if (tempRed.col < tempBlue.col) red.col--;
                else blue.col--;
            }
        }

        if (tempRed.isSame(red) && tempBlue.isSame(blue)) return false;
        else return true;
    }

} // end of class
