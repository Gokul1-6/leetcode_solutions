class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];

        // dp[r] = number of subarrays ending at the
        // current position whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            int rem = num % k;

            // New DP for subarrays ending at current element
            long[] next = new long[k];

            // Start a new subarray with only nums[i]
            next[rem] = 1;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                int newRem = (int) ((long) r * rem % k);

                next[newRem] += dp[r];
            }

            // Add current subarrays to final answer
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}