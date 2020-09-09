package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_경주로건설_L3 {
    static class Node {
        int row, col, sum, dir;
        public Node(int row, int col, int sum, int dir) {
            this.row = row;
            this.col = col;
            this.sum = sum;
            this.dir = dir;
        }
    }

    static int min;
    static int[][] grid;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int n;

    public static int solution(int[][] board) {
        min = Integer.MAX_VALUE;
        n = board.length;
        grid = board;

        // 처음에 오른쪽, 아래쪽으로 갈 수 있기에 -1 값을 집어 넣어준다.
        bfs(0, 0, 0, -1);
        return min;
    }

    static void bfs(int row, int col, int sum, int dir) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(row, col, sum, dir));
        // 첫 초기값을 넣어준다.
        grid[0][0] = 1;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.row == n - 1 && node.col == n - 1) {
                if (min > node.sum) min = node.sum;
                continue;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nr = node.row + dy[i];
                int nc = node.col + dx[i];

                if (isIn(nr, nc) && grid[nr][nc] != 1) {
                    int ns = 0;

                    if (node.dir == -1 || node.dir == i) ns = node.sum + 100;
                    else ns = node.sum + 600;

                    // 한번도 방문하지 않았다면
                    if (grid[nr][nc] == 0) {
                        grid[nr][nc] = ns;
                        q.add(new Node(nr, nc, ns, i));
                    } else if (grid[nr][nc] >= ns) { // 방문하는 곳이 값이 적다면
                        grid[nr][nc] = ns;
                        q.add(new Node(nr, nc, ns, i));
                    }
                }
            }
        }
    }

    static boolean isIn(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }

    public static void main(String[] args) {
        int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
//        int[][] board = {
//                {0,0,0,0,0,0,0,1},
//                {0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,1,0,0},
//                {0,0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0,1},
//                {0,0,1,0,0,0,1,0},
//                {0,1,0,0,0,1,0,0},
//                {1,0,0,0,0,0,0,0}
//        };
//        int[][] board = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
        System.out.println(solution(board));
    }
}
