/*
 * LeetCode #338: Counting Bits
 * https://leetcode.com/problems/counting-bits/
 */

// ========================================
// Approach 1: Brute Force (Brian Kernighan’s Algorithm)
// Time: O(nlogn) | Space: O(1)
// ========================================
/*
class Solution {
    public int[] countBits(int n) {
        // Initialize the result array of size n + 1
        int[] ans = new int[n + 1];

        // Loop through each number from 0 to n
        for (int i = 0; i <= n; i++) {
            // Use the private helper method to count set bits in i
            ans[i] = hammingWeight(i);
        }

        // Return the filled result array
        return ans;
    }

    // Private helper method to count set bits using Brian Kernighan's Algorithm
    private int hammingWeight(int n) {
        int count = 0;

        // Keep removing the rightmost set bit until n becomes 0
        while (n != 0) {
            // Remove the rightmost set bit
            n = n & (n - 1);
            // Increment count for each set bit removed
            count++;
        }

        return count;
    }
}
*/

// ========================================
// Approach 2: Dynamic Programming
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0; // Base case: 0 has 0 set bits

        for (int i = 1; i <= n; i++) {
            // f(i) = f(i / 2) if i is even
            // f(i) = f(i / 2) + 1 if i is odd
            if (i % 2 == 0) {
                ans[i] = ans[i / 2]; // Even: same as i/2
            } else {
                ans[i] = ans[i / 2] + 1; // Odd: i/2 + 1
            }
        }

        return ans;
    }
}
*/

// ========================================
// Approach 3: Dynamic Programming (With Bitwise Optimization)
// Time: O(n) | Space: O(n)
// ========================================
class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0; // Base case: 0 has 0 set bits

        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 0) {
                // i is even → last bit is 0
                // f(i) = f(i / 2)
                ans[i] = ans[i >> 1];
            } else {
                // i is odd → last bit is 1
                // f(i) = f(i / 2) + 1
                ans[i] = ans[i >> 1] + 1;
            }
        }

        return ans;
    }
}
