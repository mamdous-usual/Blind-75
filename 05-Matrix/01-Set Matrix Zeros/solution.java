/*
 * LeetCode #73: Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes/
 */

// ========================================
// Approach 1: Brute Force (Matrix Copy)
// Time: O(m * n * (m + n)) | Space: O(m * n)
// ========================================
/*
class Solution {
    public void setZeroes(int[][] matrix) {
        // Get the number of rows (m) and columns (n) in the matrix
        int m = matrix.length; // Total rows
        int n = matrix[0].length; // Total columns

        // Create a copy of the original matrix to avoid modifying it while iterating
        int[][] matrixCopy = new int[m][n];

        // Copy original matrix values to matrixCopy
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrixCopy[i][j] = matrix[i][j]; // Copy element
            }
        }

        // Iterate through the original matrix to find zeros and mark corresponding rows and columns in matrixCopy
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) { // Found a zero in original matrix
                    for (int k = 0; k < n; k++) {
                        matrixCopy[i][k] = 0; // Set entire row i to zero in matrixCopy
                    }
                    for (int k = 0; k < m; k++) {
                        matrixCopy[k][j] = 0; // Set entire column j to zero in matrixCopy
                    }
                }
            }
        }

        // Copy the modified matrixCopy back to the original matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrixCopy[i][j]; // Update original matrix
            }
        }
    }
}
*/

// ========================================
// Approach 2: Marker / Auxiliary Arrays
// Time: O(m * n) | Space: O(m + n)
// ========================================
/*
class Solution {
    public void setZeroes(int[][] matrix) {
        // Get the number of rows(m) and columns(n) in the matrix.
        int m = matrix.length;
        int n = matrix[0].length;

        // Initialize arrays to keep track of rows and columns that need to be zeroed.
        boolean[] zeroRows = new boolean[m];
        boolean[] zeroCols = new boolean[n];

        // First loop: Iterate through the matrix and mark rows and columns
        // that need to be zeroed in `zeroRows` and `zeroCols` array.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }

        // Second loop: Iterate through the matrix to zero out its cells
        // using marked rows and columns.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (zeroRows[i] == true || zeroCols[j] == true) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
*/

// ========================================
// Approach 3: In-place (Using First Row & Column)
// Time: O(m * n) | Space: O(1)
// ========================================
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Step 1: Check if first row has any zeros
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Step 2: Check if first column has any zeros
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Step 3: Use first row and column as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 4: Set matrix cells to 0 based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 5: Zero out first row if needed
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 6: Zero out first column if needed
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
