/*
 * LeetCode #125: Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 */

// ========================================
// Approach 1: Reverse & Compare
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public boolean isPalindrome(String s) {
        // Remove all non-alphanumeric characters and convert to lowercase.
        // This ensures we only compare letters and numbers, ignoring spaces,
        // punctuation, and case
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Create reversed string
        String reversedStr = new StringBuilder(cleaned).reverse().toString();

        // Compare
        return cleaned.equals(reversedStr);
    }
}
*/

// ========================================
// Approach 2: Two Pointer
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public boolean isPalindrome(String s) {
        // Handle edge cases: null or empty string is considered a palindrome
        if (s == null || s.length() == 0) {
            return true;
        }

        // Initialize two pointers at the beginning and end of the string
        int left = 0;
        int right = s.length() - 1;

        // Compare characters from both ends moving toward the center
        while (left < right) {
            // Skip non-alphanumeric characters from the left side
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip non-alphanumeric characters from the right side
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) !=
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            // Move pointers closer to the center
            left++;
            right--;
        }

        // If all character pairs match, it's a valid palindrome
        return true;
    }
}
