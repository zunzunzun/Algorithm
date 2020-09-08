import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17144_이준상_200216 {
	static class Dust {
		int row;
		int col;
		int value;
		public Dust(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}
	// 0 상 1 하 2 좌 3 우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int R;
	static int C;
	static int T;
	static int[][] grid;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		grid = new int[R][C];
		Queue<Dust> q = new LinkedList<>();
		int[] cleaner = new int[2];
		int cleanerIndex = 0;
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input != 0) {
					if(input == -1) {
						cleaner[cleanerIndex++] = i;
					} else {
						q.add(new Dust(i, j, input));
					}
				}
				grid[i][j] = input;
			}
		}
		
		for(int a = 0; a < T; a++) { // 해당 초까지
			//확산
			int size = q.size();
			for(int b = 0; b < size; b++) { // 처음 큐에 담긴 것 만큼만 실행.
				Dust d = q.poll();
				if(d.value <= 4) {
					q.add(d);
					continue; // 5이하면 확산이 안됨.
				}
				int count = 0;
				for(int i = 0; i < 4; i++) { // 4방향 확산
					int nextRow = d.row + dy[i];
					int nextCol = d.col + dx[i];
					if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
						if(grid[nextRow][nextCol] != -1) {
							q.add(new Dust(nextRow, nextCol, d.value / 5));
							count++;
						}
					}
				}
				d.value -= (d.value / 5) * count;
				q.add(d);
			}

			// 큐에 담긴 여러 정보를 합침
			int[][] tempGrid = new int[R][C];
			while(!q.isEmpty()) {
				Dust d = q.poll();
				tempGrid[d.row][d.col] += d.value;
			}
			tempGrid[cleaner[0]][0] = -1;
			tempGrid[cleaner[1]][0] = -1;
			
			// 공기청정기 가동
			// 위쪽부터 탐색.
			// 0 상 1 하 2 좌 3 우
			int dir = 0;
			int row = cleaner[0] - 1; // 시작 정점 공기청정기 바로 위
			int col = 0;
			while(true) {
				int nextRow = row + dy[dir];
				int nextCol = col + dx[dir];
				// 다음 인덱스 유효성 검사
				if(nextRow == -1 || nextRow == cleaner[0] + 1 || nextCol == C) {
					if(dir == 0) {
						dir = 3;
						nextRow += 1;
						nextCol += 1;
					} else if(dir == 3) {
						dir = 1;
						nextRow += 1;
						nextCol -= 1;
					} else {
						dir = 2;
						nextRow -= 1;
						nextCol -= 1;
					}
				}
				if(tempGrid[nextRow][nextCol] == -1) {
					tempGrid[row][col] = 0;
					break;
				}
				tempGrid[row][col] = tempGrid[nextRow][nextCol];
				row = nextRow;
				col = nextCol;
			} // end of while.
			
			// 아래쪽 탐색
			dir = 1;
			row = cleaner[1] + 1;
			col = 0;
			while(true) {
				int nextRow = row + dy[dir];
				int nextCol = col + dx[dir];
				// 다음 인덱스 유효성 검사
				if(nextRow == R || nextRow == cleaner[1] - 1 || nextCol == C) {
					if(dir == 1) {
						dir = 3;
						nextRow -= 1;
						nextCol += 1;
					} else if(dir == 3) {
						dir = 0;
						nextRow -= 1;
						nextCol -= 1;
					} else {
						dir = 2;
						nextRow += 1;
						nextCol -= 1;
					}
				}
				if(tempGrid[nextRow][nextCol] == -1) {
					tempGrid[row][col] = 0;
					break;
				}
				tempGrid[row][col] = tempGrid[nextRow][nextCol];
				row = nextRow;
				col = nextCol;
			} // end of while.
			grid = tempGrid;
			int sum = 0;
			
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(grid[i][j] > 0) q.add(new Dust(i, j, grid[i][j]));
				}
			}
		} // end of for T

		int sum = 0;
		for(int i = 0 ; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sum += grid[i][j];
			}
		}
		System.out.println(sum + 2);
	} // end of main
} // end of class