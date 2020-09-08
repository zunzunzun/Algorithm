import java.util.Scanner;

public class BJ2748 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] arr = new long[91];
		arr[1] = 1;
		for(int i = 2; i <= n; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		System.out.println(arr[n]);
	}
}
