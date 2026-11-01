/*
 * LeetCode #91: Decode Ways
 * https://leetcode.com/problems/decode-ways/
 */

import java.util.*;

/* ================================================================
   Approach 1: Brute Force – Recursion (DFS)
   Time:  O(2^N)  — worst-case branching on each index
   Space: O(N)    — recursion call stack
   ================================================================ */
/*
class Solution {
    public int numDecodings(String s) {
        // Start the recursive decoding process from index 0
        return dfs(s, 0);
    }

    private int dfs(String s, int index) {
        // Base case: if we've reached the end of the string, we've found one valid decoding
        if (index == s.length()) {
            return 1;
        }
        
        // If current character is '0', it cannot be decoded alone (no letter maps to 0)
        if (s.charAt(index) == '0') {
            return 0;
        }
        
        // Variable to accumulate the total number of ways to decode from this index
        int ways = 0;
        
        // Option 1: Decode current character as a single digit (1-9 maps to A-I)
        ways += dfs(s, index + 1);
        
        // Option 2: Try to decode current and next character as a two-digit number
        if (index + 1 < s.length()) {
            // Extract the two-digit number
            int num = Integer.parseInt(s.substring(index, index + 2));
            
            // Check if it's a valid encoding (10-26 maps to J-Z)
            if (num >= 10 && num <= 26) {
                ways += dfs(s, index + 2);
            }
        }
        
        // Return total ways to decode from this index onwards
        return ways;
    }
}
*/

/* ================================================================
   Approach 2: Dynamic Programming – Memoization (Top-Down)
   Time:  O(N)
   Space: O(N)
   ================================================================ */
/*
class Solution {
    public int numDecodings(String s) {
        // Create memoization array to cache results for each index
        // Using Integer[] instead of int[] to distinguish between uncalculated (null) and calculated (0) values
        Integer[] memo = new Integer[s.length()];
        return dfs(s, 0, memo);
    }

    private int dfs(String s, int index, Integer[] memo) {
        // Base case: if we've reached the end of the string, we've found one valid decoding
        if (index == s.length()) {
            return 1;
        }

        // If current character is '0', it cannot be decoded alone (no letter maps to 0)
        if (s.charAt(index) == '0') {
            return 0;
        }

        // Check if we've already calculated the result for this index (memoization)
        if (memo[index] != null) {
            return memo[index];
        }

        // Variable to accumulate the total number of ways to decode from this index
        int ways = 0;

        // Option 1: Decode current character as a single digit (1-9 maps to A-I)
        ways += dfs(s, index + 1, memo);

        // Option 2: Try to decode current and next character as a two-digit number
        if (index + 1 < s.length()) {
            // Extract the two-digit number
            int num = Integer.parseInt(s.substring(index, index + 2));

            // Check if it's a valid encoding (10-26 maps to J-Z)
            if (num >= 10 && num <= 26) {
                ways += dfs(s, index + 2, memo);
            }
        }

        // Store the result in memo array before returning (memoization)
        memo[index] = ways;
        return ways;
    }
}
*/

/* ================================================================
   Approach 3: Dynamic Programming – Bottom-Up (Tabulation)
   Time:  O(N)
   Space: O(N)
   ================================================================ */
/*
class Solution {
    public int numDecodings(String s) {
        // Base case: check for invalid input or string starting with '0'. A string starting with '0' cannot be decoded
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();

        // dp[i] represents the number of ways to decode the substring s[0...i-1]. We use n+1 size to handle the base case more easily
        int[] dp = new int[n + 1];

        // Base case: empty string has one way to decode (decode nothing)
        dp[0] = 1;

        // Base case: first character has one way to decode (already validated it's not '0')
        dp[1] = 1;

        // Fill the dp array for positions 2 through n
        for (int i = 2; i <= n; i++) {
            // Check if we can decode the current character as a single digit (1-9)
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            if (oneDigit >= 1 && oneDigit <= 9) {
                // If valid, add the number of ways to decode up to the previous position
                dp[i] += dp[i - 1];
            }

            // Check if we can decode the last two characters as a two-digit number (10-26)
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigits >= 10 && twoDigits <= 26) {
                // If valid, add the number of ways to decode up to two positions back
                dp[i] += dp[i - 2];
            }
        }

        // Return the number of ways to decode the entire string
        return dp[n];
    }
}
*/


/* ================================================================
   Approach 4: Dynamic Programming – Bottom-Up (Space Optimized)
   Time:  O(N)
   Space: O(1)
   ================================================================ */
class Solution {
    public int numDecodings(String s) {
        // Base case: check for invalid input or string starting with '0'. A string starting with '0' cannot be decoded
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();

        // Space-optimized approach: instead of maintaining entire dp array,
        // we only need the last two values since dp[i] only depends on dp[i-1] and dp[i-2]

        // prev2 represents dp[i-2]: number of ways to decode up to 2 positions back
        int prev2 = 1; // Equivalent to dp[0] = 1 (empty string base case)

        // prev1 represents dp[i-1]: number of ways to decode up to 1 position back
        int prev1 = 1; // Equivalent to dp[1] = 1 (first character base case)

        // Fill values for positions 2 through n
        for (int i = 2; i <= n; i++) {
            // current will hold dp[i]: number of ways to decode up to current position
            int current = 0;

            // Check if we can decode the current character as a single digit (1-9)
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            if (oneDigit >= 1 && oneDigit <= 9) {
                // If valid, add the number of ways from the previous position
                current = current + prev1;
            }

            // Check if we can decode the last two characters as a two-digit number (10-26)
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigits >= 10 && twoDigits <= 26) {
                // If valid, add the number of ways from two positions back
                current = current + prev2;
            }

            // Shift the window: move prev1 to prev2, and current to prev1
            prev2 = prev1;
            prev1 = current;
        }

        // prev1 now holds the number of ways to decode the entire string
        return prev1;
    }
}
