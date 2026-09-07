class Solution {
    public int distinctSubseqII(String s) {
        long mod = 1000000007;
        long total = 0;
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int x = c - 'a';
            long add = (total + 1 - last[x] + mod) % mod;
            total = (total + add) % mod;
            last[x] = (last[x] + add) % mod;
        }

        return (int) total;
    }
}