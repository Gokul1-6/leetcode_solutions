class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < s.length(); i++) {
            int oddLength = expand(s, i, i);
            int evenLength = expand(s, i, i + 1);

            int currentLength = Math.max(oddLength, evenLength);

            if (currentLength > maxLength) {
                maxLength = currentLength;
                start = i - (currentLength - 1) / 2;
            }
        }

        return s.substring(start, start + maxLength);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        return right - left - 1;
    }
}