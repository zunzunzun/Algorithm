import java.util.Scanner;

public class BJ1806 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int s = sc.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int start, end, sum, ans;
		start = 0;
		sum = arr[0];
		end = 1;
		ans = n + 1;
		for(; start < n; start++) {
			while(end < n && sum < s) {
				sum += arr[end++];
			}
			
			if(sum >= s) {
				int count = end - start;
				if(ans > count) ans = count;
			}
			sum -= arr[start];
		}

		if(ans == n + 1) System.out.println(0);
		else System.out.println(ans);
		
	} // end of main
}
