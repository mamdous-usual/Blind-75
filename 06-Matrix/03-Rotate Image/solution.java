/*
 * LeetCode #48: Rotate Image
 * https://leetcode.com/problems/rotate-image/
 */

// ========================================
// Approach 1: Brute Force (Using a Temporary Matrix)
// Time: O(n²) | Space: O(n²)
// ========================================
/*
class Solution {
    public void rotate(int[][] matrix) {
        // Get the size of the square matrix (n x n)
        int n = matrix.length;

        // Create a temporary matrix to store the rotated values
        int[][] tempMatrix = new int[n][n];

        // Rotate the matrix 90 degrees clockwise
        // For each element at position (i, j), move it to position (j, n-i-1)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tempMatrix[j][n - i - 1] = matrix[i][j];
            }
        }

        // Copy the rotated values back to the original matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = tempMatrix[i][j];
            }
        }
    }
}
*/

// ========================================
// Approach 2: Transpose + Reverse Rows (In-place)
// Time: O(n²) | Space: O(1)
// ========================================
class Solution {
    public void rotate(int[][] matrix) {
        // Get the size of the square matrix (n x n)
        int n = matrix.length;

        // Step 1: Transpose the matrix (swap elements across the main diagonal)
        // Only iterate through the upper triangle to avoid swapping twice
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // Swap matrix[i][j] with matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row to complete the 90-degree clockwise rotation
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            // Swap elements from both ends moving toward the center
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}
