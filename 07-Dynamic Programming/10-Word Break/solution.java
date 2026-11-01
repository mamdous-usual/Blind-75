/*
 * LeetCode #139: Word Break
 * https://leetcode.com/problems/word-break/
 */

import java.util.*;

/* ================================================================
   Approach: Dynamic Programming — Bottom-Up (Tabulation)
   Time:  O(n^2)
   Space: O(n)
   ================================================================ */

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert the word dictionary to a HashSet for O(1) lookup time
        Set<String> wordSet = new HashSet<>(wordDict);

        // Create a DP array where dp[i] represents whether the substring s[0...i-1] can be segmented
        // dp[i] = true means the first i characters of s can be broken into dictionary words
        boolean[] dp = new boolean[s.length() + 1];

        // Base case: empty string can always be segmented
        dp[0] = true;

        // Iterate through each position in the string
        for (int i = 1; i <= s.length(); i++) {
            // Check all possible ways to split s[0...i-1]
            // Try every position j as a potential breaking point
            for (int j = 0; j < i; j++) {
                // If s[0...j-1] can be segmented (dp[j] is true)
                // AND s[j...i-1] is in the dictionary
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    // Then s[0...i-1] can be segmented
                    dp[i] = true;
                    // No need to check other splits for position i
                    break;
                }
            }
        }

        // Return whether the entire string can be segmented
        return dp[s.length()];
    }
}
