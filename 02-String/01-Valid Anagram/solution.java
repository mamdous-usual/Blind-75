/*
 * LeetCode #242: Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 */

import java.util.*;

// ========================================
// Approach 1: Brute Force (Sorting)
// Time: O(n log n) | Space: O(n)
// ========================================
/*
class Solution {
    public boolean isAnagram(String s, String t) {
        // If lengths are not equal, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Convert both strings to lowercase and then to character arrays
        char[] sArray = s.toLowerCase().toCharArray();
        char[] tArray = t.toLowerCase().toCharArray();

        // Sort the character arrays so that identical letters are aligned
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        // Compare the sorted arrays; if equal, the strings are anagrams
        return Arrays.equals(sArray, tArray);
    }
}
*/

// ========================================
// Approach 2: Frequency Count (with a HashMap)
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public boolean isAnagram(String s, String t) {
        // Check if the lengths of the two strings are different.
        // If so, they cannot be anagrams.
        if (s.length() != t.length()) {
            return false;
        }

        // Create a map to store the frequency of characters in string `s`.
        Map<Character, Integer> charFrequency = new HashMap<>();

        // Count the occurrences of each character in the string `s`.
        for (int i = 0; i < s.length(); i++) {
            // Get the current character in string `s`.
            Character currentChar = s.charAt(i);

            // Increment the count of that character in the `charFrequency` map.
            charFrequency.put(currentChar, charFrequency.getOrDefault(currentChar, 0) + 1);
        }

        // Decrement the character counts for string `t`.
        for (int i = 0; i < t.length(); i++) {
            // Get the current character in string `t`.
            Character currentChar = t.charAt(i);

            // Decrement the count of that character in the `charFrequency` map.
            charFrequency.put(currentChar, charFrequency.getOrDefault(currentChar, 0) - 1);
        }

        // Check if all character counts in `charFrequency` map are back to 0
        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            Integer value = entry.getValue();
            if (value != 0) {
                return false;
            }
        }

        // If all character counts are 0, the strings are anagrams
        return true;
    }
}
*/

// ========================================
// Approach 3: Frequency Count (with a Character Array)
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public boolean isAnagram(String s, String t) {
        // First check: if lengths are different, they can't be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Frequency counter for 26 lowercase letters (a-z)
        int[] count = new int[26];

        // Process both strings simultaneously, character by character
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            int sIndex = sChar - 'a'; // 'a' -> 0, 'b' -> 1, etc.
            int tIndex = tChar - 'a';

            count[sIndex]++; // Increment count for character from string s
            count[tIndex]--; // Decrement count for character from string t
        }

        // Check if all character frequencies balance out to zero
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false; // Found unbalanced character frequency
            }
        }

        // All character frequencies are balanced - strings are anagrams
        return true;
    }
}
