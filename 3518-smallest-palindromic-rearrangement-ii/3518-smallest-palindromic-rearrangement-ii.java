class Solution {
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        char middle = 0;
        int halfLength = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                middle = (char) ('a' + i);
            }

            count[i] /= 2;
            halfLength += count[i];
        }

        if (countPermutations(count) < k) {
            return "";
        }

        StringBuilder firstHalf = new StringBuilder();

        for (int position = 0; position < halfLength; position++) {
            for (int letter = 0; letter < 26; letter++) {
                if (count[letter] == 0) {
                    continue;
                }

                count[letter]--;

                long possible = countPermutations(count);

                if (k <= possible) {
                    firstHalf.append((char) ('a' + letter));
                    break;
                } else {
                    k -= possible;
                    count[letter]++;
                }
            }
        }

        StringBuilder answer = new StringBuilder();

        answer.append(firstHalf);

        if (middle != 0) {
            answer.append(middle);
        }

        answer.append(new StringBuilder(firstHalf).reverse());

        return answer.toString();
    }

    private long countPermutations(int[] count) {
        int total = 0;

        for (int value : count) {
            total += value;
        }

        long result = 1;

        for (int value : count) {
            result *= combination(total, value);

            if (result >= LIMIT) {
                return LIMIT;
            }

            total -= value;
        }

        return result;
    }

    private long combination(int n, int r) {
        r = Math.min(r, n - r);

        long result = 1;

        for (int i = 1; i <= r; i++) {
            result = result * (n - i + 1) / i;

            if (result >= LIMIT) {
                return LIMIT;
            }
        }

        return result;
    }
}