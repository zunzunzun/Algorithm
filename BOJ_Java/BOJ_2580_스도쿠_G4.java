package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2580_스도쿠_200726 {
    static int[][] sudoku = new int[9][9];
    // 각각 행, 열, 사각형의 숫자가 있는지 확인할 배열
    // rows[0][1]이 true면 첫 번째 행에는 1이라는 숫자가 있다는 것을 의미한다.
    // 숫자를 담기위해 길이를 10으로 한다.
    static boolean[][] rows = new boolean[9][10];
    static boolean[][] cols = new boolean[9][10];
    static boolean[][] squares = new boolean[9][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                int input = Integer.parseInt(st.nextToken());
                sudoku[i][j] = input;
                if (input != 0) {
                    rows[i][input] = true;
                    cols[j][input] = true;
                    // 가로, 세로 길이 3인 정사각형 정보를 담을 배열.
                    // 아래와 같은 작업으로 index를 구할 수 있다.
                    squares[(i / 3) * 3 + (j / 3)][input] = true;
                }
            }
        }

        dfs(0);

    } // end of main

    // 여러 숫자를 집어 넣어야 할 경우가 있을 수 있으니 DFS를 통한 백트래킹으로 구현
    static void dfs(int count) {
        if (count == 81) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(sudoku[i][j] + " ");
                }
                System.out.println();
            }
            // 이미 답이 나왔으니 종료.
            System.exit(0);
        } else {
            int row = count / 9;
            int col = count % 9;

            if (sudoku[row][col] == 0) {
                // 모든 숫자를 대입해 본다.
                for (int i = 1; i <= 9; i++) {
                    if (!rows[row][i] && !cols[col][i] && !squares[(row / 3) * 3 + (col / 3)][i]) {
                        rows[row][i] = true;
                        cols[col][i] = true;
                        squares[(row / 3) * 3 + (col / 3)][i] = true;
                        sudoku[row][col] = i;
                        dfs(count + 1);
                        // 해당 숫자가 답이 아니므로 다시 원래대로 복구한다.
                        rows[row][i] = false;
                        cols[col][i] = false;
                        squares[(row / 3) * 3 + (col / 3)][i] = false;
                        sudoku[row][col] = 0;
                    }
                }
            } else { // 0이 아닌 경우는 바로 들어간다.
                dfs(count + 1);
            }
        }
    } // end of dfs

} // end of class
