class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        boolean[][] p = new boolean[n][n];
        int[] dp = new int[n + 1];

        // Palindrome check
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                    (j - i < 2 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                }
            }
        }

        // DP
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];

            for (int j = 0; j < i; j++) {
                if (i - j >= k && p[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}