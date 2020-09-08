import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_17143_이준상_200216 {
	// 1위 2 아래 3 오른쪽 4 왼쪽
	static int[] dx = {0, 0, 0, 1, -1};
	static int[] dy = {0, -1, 1, 0, 0};
	static class Shark {
		int row;
		int col;
		int s;
		int d;
		int z;
		boolean live;
		public Shark(int row, int col, int s, int d, int z, boolean live) {
			this.row = row;
			this.col = col;
			this.s = s;
			this.d = d;
			this.z = z;
			this.live = live;
		}
	}
	static int R;
	static int C;
	static int M;
	static ArrayList<Shark> sharks = new ArrayList<>();
	static int[][] grid;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[R + 2][C + 2]; 
		for(int i = 1 ; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				grid[i][j] = -1;
			}
		}
		
		for(int i = 0; i < M; i++) {
			st =  new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			grid[row][col] = i;
			sharks.add(new Shark(row, col, s, d, z, true));
		}
		
		int man = 0;
		int ans = 0;
		while(++man <= C) {
			for(int i = 1; i <= R; i++) { // 건져올릴 상어 찾기.
				if(grid[i][man] != -1) {
					ans += sharks.get(grid[i][man]).z;
					sharks.get(grid[i][man]).live = false;
					break;
				}
			}
			
			int[][] tempGrid = new int[R + 2][C + 2];
			for(int i = 1 ; i <= R; i++) {
				for(int j = 1; j <= C; j++) {
					tempGrid[i][j] = -1;
				}
			}
			
			for(int i = 0; i < sharks.size(); i++) {
				Shark s = sharks.get(i);
				if(!s.live) continue;
				int nextRow = s.row;
				int nextCol = s.col;
				int tempS = s.s;
				// 1위 2 아래 3 오른쪽 4 왼쪽
				if(s.d == 1 || s.d == 2) { // 위 아래인 경우. 
					tempS %= (R - 1) * 2;
				} else {
					tempS %= (C - 1) * 2;
				}
				
				while(tempS > 0) { // 도착지점 설정
					nextRow += dy[s.d];
					nextCol += dx[s.d];
					if(nextRow == 0 || nextRow == R + 1 || nextCol == 0 || nextCol == C + 1) { // 끝자락에 있을 경우
						s.d = changeDir(s.d); // 방향 전환
						nextRow += dy[s.d] * 2;
						nextCol += dx[s.d] * 2;
					}
					tempS--;
				}
				
				s.row = nextRow;
				s.col = nextCol;
				
				if(tempGrid[nextRow][nextCol] != -1) {
					if(sharks.get(tempGrid[nextRow][nextCol]).z > s.z) {
						s.live = false;
					} else {
						sharks.get(tempGrid[nextRow][nextCol]).live = false;
						tempGrid[nextRow][nextCol] = i;
					}
				} else {
					tempGrid[nextRow][nextCol] = i;
				}
				
			} // end of for
			
			grid = tempGrid;
		} // end of while
		
		System.out.println(ans);
	} // end of main
	
	public static int changeDir(int d) {
		switch(d) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return -1;
	}
} // end of class
