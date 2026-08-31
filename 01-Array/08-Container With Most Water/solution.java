/*
 * LeetCode #11: Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 */

// ========================================
// Approach 1: Brute Force
// Time: O(n²) | Space: O(1)
// ========================================
/*
class Solution {
    public int maxArea(int[] height) {
        // Initialize variable to track the maximum water area found
        int maxWater = 0;

        // Get the length of the height array
        int n = height.length;

        // Outer loop: iterate through each possible left boundary
        for (int i = 0; i < n; i++) {
            // Inner loop: iterate through each possible right boundary after i
            for (int j = i + 1; j < n; j++) {
                // Calculate the width between the two boundaries
                int width = j - i;

                // The water level is limited by the shorter of the two walls
                int minHeight = Math.min(height[i], height[j]);

                // Calculate the area of water that can be contained
                int currentArea = width * minHeight;

                // Update maxWater if current area is larger
                maxWater = Math.max(maxWater, currentArea);
            }
        }

        // Return the maximum water area found
        return maxWater;
    }
}
*/

// ========================================
// Approach 2: Two Pointers
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int maxArea(int[] height) {
        // Initialize two pointers at the start and end of the array
        int left = 0;
        int right = height.length - 1;

        // Variable to track the maximum water area found
        int maxWater = 0;

        // Continue until the two pointers meet
        while (left < right) {
            // Calculate width between the two pointers
            int width = right - left;

            // Water level is determined by the shorter wall
            int minHeight = Math.min(height[left], height[right]);

            // Calculate current area and update maximum if needed
            int currentArea = width * minHeight;
            maxWater = Math.max(maxWater, currentArea);

            // Move the pointer with the smaller height inward
            // This is the key optimization: we discard the smaller height because
            // any container using it with a smaller width will have less area
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}
