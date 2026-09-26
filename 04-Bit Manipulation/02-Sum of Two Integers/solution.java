/*
 * LeetCode #371: Sum of Two Integers
 * https://leetcode.com/problems/sum-of-two-integers/
 */

// ========================================
// Approach: Bit Manipulation using XOR and AND
// Time: O(1) | Space: O(1)
// ========================================
class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int sum = a ^ b; // Calculate sum without carry first
            int carry = (a & b) << 1; // Then calculate carry
            a = sum; // Update a with the sum
            b = carry; // Update b with the carry
        }
        return a;
    }
}
