class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // Store: {left, right, weight, originalIndex}
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        // Sort by starting point
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));

        // next[i] = first interval whose start > a[i][1]
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int l = i + 1, r = n;

            while (l < r) {
                int mid = l + (r - l) / 2;

                if (a[mid][0] > a[i][1]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            next[i] = l;
        }

        /*
         * dp[k][i] = best answer using at most k intervals
         * from index i onwards.
         *
         * We store:
         * [score, indices...]
         */
        long[][] score = new long[5][n + 1];
        int[][][] ans = new int[5][n + 1][];

        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                ans[k][i] = new int[0];
            }
        }

        for (int k = 1; k <= 4; k++) {

            for (int i = n - 1; i >= 0; i--) {

                // Don't choose current interval
                score[k][i] = score[k][i + 1];
                ans[k][i] = ans[k][i + 1];

                // Choose current interval
                long takeScore = a[i][2] + score[k - 1][next[i]];

                int[] takeAns = new int[ans[k - 1][next[i]].length + 1];

                takeAns[0] = a[i][3];

                for (int j = 0; j < ans[k - 1][next[i]].length; j++) {
                    takeAns[j + 1] = ans[k - 1][next[i]][j];
                }

                Arrays.sort(takeAns);

                if (takeScore > score[k][i] ||
                    (takeScore == score[k][i] &&
                     smaller(takeAns, ans[k][i]))) {

                    score[k][i] = takeScore;
                    ans[k][i] = takeAns;
                }
            }
        }

        return ans[4][0];
    }

    private boolean smaller(int[] a, int[] b) {

        for (int i = 0; i < Math.min(a.length, b.length); i++) {

            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}