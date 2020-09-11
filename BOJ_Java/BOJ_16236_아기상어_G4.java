package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16236_아기상어_G4 {
    static class Point implements Comparable<Point> {
        // 아기상어 위치에서 해당 좌표까지의 거리를 의미한다.
        int row, col, dist;

        public Point(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        // TreeSet을 쓰기 위한 코드
        @Override
        public int compareTo(Point o) {
            if (this.dist == o.dist) {
                if (this.row == o.row) return this.col - o.col;
                return this.row - o.row;
            }
            return this.dist - o.dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row &&
                    col == point.col &&
                    dist == point.dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, dist);
        }
    }

    // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0,};
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];

        // 아기 상어의 좌표와 사이즈, 먹은 개수를 의미.
        int row, col, size, count;
        row = col = count = 0;
        size = 2;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 9) {
                    row = i;
                    col = j;
                }
                grid[i][j] = input;
            }
        }

        int answer = 0;
        // 먹을 수 있는 물고기들의 좌표를 담아 둘 Set.
        Set<Point> set = new TreeSet<>();
        // BFS를 이용하기 위한 Queue와 boolean 배열.
        Queue<Point> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[n][n];
        q.add(new Point(row, col, 0));
        int dist = 0;

        while (true) {
            // 더이상 먹을 물고기가 없다는 것을 의미.
            if (q.isEmpty() && set.isEmpty()) break;
            // 모든 경로를 탐색했고 먹을 물고기를 비교한다.
            if (q.isEmpty() && !set.isEmpty()) {
                Iterator<Point> iterator = set.iterator();
                Point point = iterator.next();

                answer += point.dist;
                if (size == ++count) {
                    size++; count = 0;
                }
                grid[row][col] = 0;
                row = point.row;
                col = point.col;
                grid[row][col] = 9;

                isVisited = new boolean[n][n];
                isVisited[row][col] = true;
                dist = 0;
                q.add(new Point(row, col, 0));
                set.clear();
                continue;
            }

            dist++;
            int qSzie = q.size();
            for (int i = 0; i < qSzie; i++) {
                Point point = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nr = point.row + dy[j];
                    int nc = point.col + dx[j];

                    if (isIn(nr, nc) && !isVisited[nr][nc]) {
                        isVisited[nr][nc] = true;

                        if (grid[nr][nc] == 0 || size == grid[nr][nc]) {
                            q.add(new Point(nr, nc, dist));
                        } else if (grid[nr][nc] < size) {
                            set.add(new Point(nr, nc, dist));
                            q.add(new Point(nr, nc, dist));
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    } // end of main

    public static boolean isIn(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
} // end of class
