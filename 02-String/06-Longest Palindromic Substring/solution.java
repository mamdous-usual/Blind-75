/*
 * LeetCode #5: Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */

// ========================================
// Approach 1: Expand Around Center (Direct Substring)
// Time: O(n²) | Space: O(n)
// ========================================
/*
class Solution {
    public String longestPalindrome(String s) {
        // Single-character string is always a palindrome
        if (s.length() == 1) {
            return s; // Return single char
        }

        String longest = ""; // Track longest palindrome found

        // Check palindromes centered at each index
        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindromes (center at i)
            int left = i, right = i; // Start at center
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                String currentPalindrome = s.substring(left, right + 1);
                int currentLength = currentPalindrome.length();
                if (currentLength > longest.length()) {
                    longest = currentPalindrome; // Update if longer
                }
                left--; // Expand outward
                right++;
            }

            // Even-length palindromes (center between i and i+1)
            left = i; // Left of center
            right = i + 1; // Right of center
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                String currentPalindrome = s.substring(left, right + 1);
                int currentLength = currentPalindrome.length();
                if (currentLength > longest.length()) {
                    longest = currentPalindrome; // Update if longer
                }
                left--; // Expand outward
                right++;
            }
        }

        return longest; // Return longest found
    }
}
*/

// ========================================
// Approach 2: Expand Around Center (Index Tracking)
// Time: O(n²) | Space: O(1)
// ========================================
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return ""; // Handle edge cases

        // Track the starting position and length of the longest palindrome found
        int longestPalindromeStart = 0, longestPalindromeLength = 0;

        for (int i = 0; i < s.length(); i++) { // Iterate through each potential center
            // Check for odd-length palindromes (single character center)
            int left = i, right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) { // Expand while matching
                left--;
                right++;
            }
            int oddCenterPalindromeLength = right - left - 1; // Calculate odd-centered length

            // Check for even-length palindromes (between two characters)
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) { // Expand while matching
                left--;
                right++;
            }
            int evenCenterPalindromeLength = right - left - 1; // Calculate even-centered length

            int currentMaxLength = Math.max(
                oddCenterPalindromeLength,
                evenCenterPalindromeLength
            ); // Find max at this center

            if (currentMaxLength > longestPalindromeLength) { // Update if longer palindrome found
                longestPalindromeLength = currentMaxLength;
                longestPalindromeStart = i - (currentMaxLength - 1) / 2; // Calculate start position
            }
        }

        // Return result
        return s.substring(
            longestPalindromeStart,
            longestPalindromeStart + longestPalindromeLength
        );
    }
}
