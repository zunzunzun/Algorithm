import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15685_이준상 {
	static boolean[][] grid = new boolean[101][101];
	static int[][] g = new int[11][]; // 드래곤 커브 세대. 방향들이 저장됨.
	// 0 오른쪽 1 위쪽 2 왼쪽 3아래쪽
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, -1, 0, 1};
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 커브개수 n
		// x, y, 방향, 세대
		// 0 오른쪽 1 위쪽 2 왼쪽 3아래쪽
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] input = new int[n][4]; // 
		for (int i = 0; i < input.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < input[i].length; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 드래곤 커브 세대 만들기
		// 0 방향 오른쪽을 기준으로 만들기.
		g[0] = new int[1]; // 0세대 만들기
		g[0][0] = 0;
		g[1] = new int[2]; // 1세대 만들기
		g[1][0] = 0;
		g[1][1] = 1;
		
		for(int i = 2; i < g.length; i++) {
			int exLength = g[i - 1].length;
			g[i] = new int[exLength * 2]; // 다음 세대는 전세대 보다 2배 더 길다.
			System.arraycopy(g[i - 1], 0, g[i], 0, exLength); // 배열 복사
			int index = exLength;
			for(int j = exLength - 1; j >= 0; j--) {
				g[i][index++] = (g[i - 1][j] + 1) % 4;
			}
		}
		
		for(int i = 0; i < input.length; i++) {
			int x = input[i][0];
			int y = input[i][1];
			int dir = input[i][2];
			int gern = input[i][3];
			
			grid[y][x] = true;
			for(int j = 0; j < g[gern].length; j++) {
				int move = (g[gern][j] + dir) % 4;
				x += dx[move];
				y += dy[move];
				if(x >= 0 && x <= 100 && y >= 0 && y <= 100) {
					grid[y][x] = true;
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(grid[i][j] && grid[i + 1][j] && grid[i][j + 1] && grid[i + 1][j + 1]) {
					count++;
				}
			}
		}
		bw.write(count + "\n");
		bw.flush();
	} // end of main
} // end of class
