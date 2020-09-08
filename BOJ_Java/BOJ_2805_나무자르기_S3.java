import java.util.Scanner;

public class BJ2805 {
	public static int[] trees;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		trees = new int[n];
		int maxHeight = 0;
		for(int i = 0; i < n; i++) {
			trees[i] = sc.nextInt();
			if(maxHeight < trees[i]) maxHeight = trees[i];
		}
		
		int start, end, mid, ans;
		start = ans = 0; end = maxHeight;
		long temp;
		
		while(start < end) {
			mid = (start + end) / 2;
			temp = getTree(mid);
			
			if(temp < m) {
				end = mid;
			} else {
				ans = mid;
				start = mid + 1;
			}
		}
		System.out.println(ans);
	}
	
	// 해당 인덱스까지 높이에 대한 합을 구해주는 함수. 
	public static long getTree(int height) {
		long sum = 0;
		for(int i = 0; i < trees.length; i++) {
			if(trees[i] > height) 
				sum += trees[i] - height;
		}
		return sum;
	}
}
