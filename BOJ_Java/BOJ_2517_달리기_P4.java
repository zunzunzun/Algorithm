import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class BJ2517 {
	static class Player { // 문제를 풀기 위해 현재 순위와 실력을 저장할 구조체. 
		int index;
		int power;
	}
	
	static Player[] arr = new Player[500001]; // 정보를 저장할 배열
	static Player[] temp = new Player[500001]; // merge sort를 위해 사용될 임시 배열
	static int[] ans = new int[500001]; // 출력할 답 배열
	
	static void mergeSort(int l, int r) {
		if(l == r) return;
		else {
			int m = (l + r) / 2;
			mergeSort(l, m);
			mergeSort(m + 1, r);
			
			// merge sort의 개념은 왼쪽 배열과 오른쪽을 합병하는 것.
			int lIndex = l; // 배열시킬 왼쪽 배열의 인덱스
			int rIndex = m + 1; // 배열시킬 오른쪽 배열의 인덱스
			for(int i = l; i <= r; i++) {
				if(lIndex > m) temp[i] = arr[rIndex++]; // 왼쪽 배열을 다 병합했을 경우
				else if(rIndex > r) temp[i] = arr[lIndex++]; // 오른쪽 배열을 다 병합했을 경우
				// 앞서 있는 선수가 뒷사람보다 앞서 있는건 볼 필요가 없다. 그대로 저장
				else if(arr[lIndex].power > arr[rIndex].power) temp[i] = arr[lIndex++];
				// 하지만 뒤에 있는 선수가 앞으로 갈때는 앞으로 갈때는 갱신.  
				else {
					temp[i] = arr[rIndex];
					ans[arr[rIndex].index] -= (m - lIndex + 1); // 왼쪽에 배열의 원소의 개수만큼 빼줘서 업데이트 시켜준다.
					rIndex++;
				}
			}
			
			for(int i = l; i <= r; i++) {
				arr[i] = temp[i];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = new Player();
			ans[i] = arr[i].index = i;
			arr[i].power = Integer.parseInt(br.readLine());
		}
		mergeSort(1, n);
		for(int i = 1; i <= n; i++) bw.write(ans[i] + "\n");
		bw.flush();
	}
}
