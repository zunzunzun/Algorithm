package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_3190_뱀_G5 {
    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row &&
                    col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    static int N;
    static Set<Point> set = new HashSet<>();
    static char[] arr = new char[10_001];
    // 우 하 좌 상.
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            set.add(new Point(row, col));
        }
        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sec = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);
            arr[sec] = ch;
        }

        List<Point> list = new LinkedList<>();
        list.add(new Point(1, 1));
        int time = 1;
        int dir = 0;
        loop:
        while (true) {
            Point point = list.get(0);
            int nr = point.row + dy[dir];
            int nc = point.col + dx[dir];

            if (!isIn(nr, nc)) break;
            for (Point item : list) {
                if (item.row == nr && item.col == nc) break loop;
            }

            boolean flag = true;
            for (Point item : set) {
                if (item.row == nr && item.col == nc) {
                    flag = false;
                    set.remove(item);
                    break;
                }
            }

            if (flag) list.remove(list.size() - 1);
            list.add(0, new Point(nr, nc));

            if (arr[time] == 'L') {
                if (dir == 0) dir = 3;
                else dir--;
            } else if (arr[time] == 'D') {
                dir = (dir + 1) % 4;
            }
            time++;
        }

        System.out.println(time);
    } // end of main

    static boolean isIn(int row, int col) {
        return 0 < row && row <= N && 0 < col && col <= N;
    }
} // end of class
