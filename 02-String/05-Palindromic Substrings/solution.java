/*
 * LeetCode #647: Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings/
 */

// ========================================
// Approach 1: Brute Force (Check all substrings)
// Time: O(n³) | Space: O(n)
// ========================================
/*
class Solution {
    public int countSubstrings(String s) {
        // Initialize the count of palindromic substrings.
        int count = 0;

        // Iterate through each character in the string.
        for (int i = 0; i < s.length(); i++) {
            // Iterate through each character starting from i to the end of the string.
            for (int j = i; j < s.length(); j++) {
                // Extract the current substring.
                String subtring = s.substring(i, j + 1);

                // Check if the substring is a palindrome.
                if (isPalindrome(subtring)) {
                    // Increment the count if the substring is a palindrome.
                    count++;
                }
            }
        }

        // Return the count of palindromic substrings.
        return count;
    }

    private boolean isPalindrome(String s) {
        // Initialize a `left` pointer to point at the first character of the string.
        int left = 0;
        // Initialize a `right` pointer to point at the last character of the string.
        int right = s.length() - 1;

        // Loop until the pointers meet in the middle.
        while (left < right) {
            // Compare characters from the left and right ends of the string.
            if (s.charAt(left) != s.charAt(right)) {
            }
            // Move the left point to the right by `1`.
            left++;
            // Move the left pointer to the left by `1`.
            right--;
        }

        // If the loop completes without returning false, the string is a palindrome.
        return true;
    }
}
*/

// ========================================
// Approach 2: Expand Around Center
// Time: O(n²) | Space: O(1)
// ========================================
class Solution {
    public int countSubstrings(String s) {
        // Single-character string is always a palindrome
        if (s.length() == 1) {
            return 1;
        }

        int count = 0;

        // Check palindromes centered at each index
        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindromes (center at i)
            int left = i, right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }

            // Even-length palindromes (center between i and i+1)
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }

        return count;
    }
}
