/*
 * LeetCode #76: Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 */

import java.util.*;

// ========================================
// Approach: Sliding Window (with a HashMap)
// Time: O(m + n) | Space: O(k)
// ========================================
class Solution {
    public String minWindow(String s, String t) {
        // Build frequency map of target string t
        Map<Character, Integer> freqT = new HashMap<>();
        for (char c : t.toCharArray()) {
            freqT.put(c, freqT.getOrDefault(c, 0) + 1);
        }

        int remaining = freqT.size(); // Unique chars left to satisfy
        int left = 0; // Left pointer of sliding window
        int minStart = 0, minLen = Integer.MAX_VALUE; // Track minimum window

        // Expand window with right pointer
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            // Process right character
            if (freqT.containsKey(ch)) {
                freqT.put(ch, freqT.get(ch) - 1);
                if (freqT.get(ch) == 0) { // Character fully satisfied
                    remaining--;
                }
            }

            // Contract window when valid (all chars satisfied)
            while (remaining == 0) {
                int windowLength = right - left + 1;

                // Update minimum window if current is smaller
                if (windowLength < minLen) {
                    minLen = windowLength;
                    minStart = left;
                }

                // Process left character before moving left pointer
                char leftChar = s.charAt(left);
                if (freqT.containsKey(leftChar)) {
                    freqT.put(leftChar, freqT.get(leftChar) + 1);
                    if (freqT.get(leftChar) > 0) { // Character no longer satisfied
                        remaining++;
                    }
                }

                left++; // Shrink window from left
            }
        }

        // Return result or empty string if no valid window found
        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(minStart, minStart + minLen);
    }
}
