/*
 * LeetCode #424: Longest Repeating Character Replacement
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 */

// ========================================
// Approach: Sliding Window (with a frequency array)
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int characterReplacement(String s, int k) {
        // Array to store frequency of each character (A-Z maps to indices 0-25)
        int[] charFrequency = new int[26];

        int left = 0; // Left pointer of sliding window
        int maxFrequency = 0; // Maximum frequency of any character in current window
        int maxLength = 0; // Length of longest valid substring found

        // Expand window with right pointer
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right); // Add current character to window
            int charIndex = currentChar - 'A'; // Convert char to array index
            charFrequency[charIndex]++; // Increment frequency

            // Update max frequency in current window
            maxFrequency = Math.max(maxFrequency, charFrequency[charIndex]);

            int windowLength = right - left + 1; // Current window size
            int charsToChange = windowLength - maxFrequency; // Characters to change

            // Shrink window if invalid (need to change more than k characters)
            while (charsToChange > k) {
                char leftChar = s.charAt(left); // Remove leftmost character from window
                int leftCharIndex = leftChar - 'A';
                charFrequency[leftCharIndex]--; // Decrease frequency
                left++; // Move left pointer right

                // Recalculate window properties
                windowLength = right - left + 1;
                charsToChange = windowLength - maxFrequency;
            }

            // Update maximum valid window length
            maxLength = Math.max(maxLength, windowLength);
        }

        return maxLength;
    }
}
