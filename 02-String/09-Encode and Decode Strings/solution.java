/*
 * LeetCode #271: Encode and Decode Strings
 * https://leetcode.com/problems/encode-and-decode-strings/
 */

import java.util.*;

// ========================================
// Approach: Length-prefix + delimiter
// Time: O(n) | Space: O(n)
// ========================================
class Solution {

    // Encodes a list of strings to a single string
    public String encode(List<String> strs) {
        StringBuilder encodedStr = new StringBuilder();

        // Iterate through each string in the list
        for (String s : strs) {
            // Append length, delimiter '#', then the actual string
            encodedStr.append(s.length()).append('#').append(s);
        }

        return encodedStr.toString();
    }

    // Decodes a single string back to a list of strings
    public List<String> decode(String str) {
        List<String> decodedStrs = new ArrayList<>();
        int i = 0;

        // Process the encoded string until we reach the end
        while (i < str.length()) {
            // Find the delimiter '#' to locate where length ends
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }

            // Extract the length of the next string
            int length = Integer.parseInt(str.substring(i, j));

            // Extract the actual string using the length we just found
            String word = str.substring(j + 1, j + 1 + length);
            decodedStrs.add(word);

            // Move the index to the start of the next encoded string
            i = j + 1 + length;
        }

        return decodedStrs;
    }
}
