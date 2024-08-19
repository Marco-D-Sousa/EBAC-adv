public class FatDynamic {

	public static  long factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Factorial is not defined for negative numbers");
		}

		long[] dp = new long[n + 1];
		dp[0] = 1;

		for (int i = 1; i <= n; i++) {
			dp[i] = i * dp[i - 1];
		}

		return dp[n];
	}

	public static void main(String[] args) {
		long result;
		result = factorial(200);

		System.out.println("Result is: " + result);
	}
}
