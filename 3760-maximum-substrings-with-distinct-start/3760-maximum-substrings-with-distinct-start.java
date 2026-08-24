class Solution {
    public int maxDistinct(String s) {
        boolean[] seen = new boolean[26];
        int count = 0;
        
        for (char ch : s.toCharArray()) {
            int index = ch - 'a';
            if (!seen[index]) {
                seen[index] = true;
                count++;
            }
        }
        
        return count;
    }
}