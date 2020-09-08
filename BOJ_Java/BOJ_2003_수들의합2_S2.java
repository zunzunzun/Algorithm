import java.util.Scanner;

public class BJ2003 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[10000];
		int n = sc.nextInt();
		int m = sc.nextInt();
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int sum = 0;
		int count = 0;
		int start, end;
		start = end = 0;
		for(; start < n; start++) {
			while(end < n && sum < m) {
				sum += arr[end++];
			}
			if(sum == m) count++;
			
			sum -= arr[start];
		}
		
		System.out.println(count);
	}
}
