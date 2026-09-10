/*
 * LeetCode #3: Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */

import java.util.*;

// ========================================
// Approach 1: Brute Force (Optimised)
// Time: O(n²) | Space: O(1)
// ========================================
/*
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Handle edge cases: null or empty string
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Track the maximum length found so far
        int maxLength = 0;

        // Outer loop: try starting from each position in the string
        for (int i = 0; i < s.length(); i++) {
            // Set to keep track of characters we've seen in current substring
            Set<Character> seen = new HashSet<>();

            // Inner loop: extend the substring from position i
            for (int j = i; j < s.length(); j++) {
                char currentChar = s.charAt(j);

                // If we've seen this character before in current substring,
                // we found a duplicate, so break out of inner loop
                if (seen.contains(currentChar)) {
                    break;
                }

                // Add the new character to our seen set
                seen.add(currentChar);

                // Update maxLength if current substring is longer.
                // Current substring length is (j - i + 1)
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }

        // Return the length of the longest substring found
        return maxLength;
    }
}
*/

// ========================================
// Approach 2: Sliding Window (with a Set)
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Handle edge cases: null or empty string
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Set to keep track of characters in current window
        Set<Character> seen = new HashSet<>();

        // Left pointer of the sliding window
        int left = 0;

        // Variable to store the maximum length found so far
        int maxLength = 0;

        // Right pointer of the sliding window - expands the window
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If current character is already in the window,
            // shrink from left until the duplicate character is removed
            while (seen.contains(currentChar)) {
                seen.remove(s.charAt(left));
                left++;
            }

            // Add current character to the window
            seen.add(currentChar);

            // Update maximum length if current window is larger
            maxLength = Math.max(maxLength, right - left + 1);
        }

        // Return the length of the longest substring without repeating characters
        return maxLength;
    }
}
