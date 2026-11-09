/*
 * LeetCode #269: Alien Dictionary
 * https://leetcode.com/problems/alien-dictionary/
 */

import java.util.*;

// ========================================
// Approach: Kahn’s Algorithm (BFS Topological Sort)
// Time: O(C + V + E) | Space: O(V + E)
// where:
//   C = total number of characters across all words
//   V = number of unique characters
//   E = number of precedence rules (edges)
// ========================================

class Solution {

  public String alienOrder(String[] words) {

    Map<Character, Set<Character>> graph = new HashMap<>(); // adjacency list: c1 -> {chars that come after c1}
    Map<Character, Integer> indegree = new HashMap<>();     // number of prerequisites for each char

    // Initialize every character found across all words in both data structures
    for (String word : words) {
      for (char c : word.toCharArray()) {
        graph.putIfAbsent(c, new HashSet<>()); // only add if not already present
        indegree.putIfAbsent(c, 0);            // start with no prerequisites
      }
    }

    // Compare each adjacent pair of words to extract character ordering rules
    for (int i = 0; i < words.length - 1; i++) {
      String w1 = words[i];
      String w2 = words[i + 1];

      if (w1.length() > w2.length() && w1.startsWith(w2)) { // e.g. "abc" before "ab" is invalid
        return "";
      }

      int len = Math.min(w1.length(), w2.length()); // only compare up to the shorter word's length

      // Find the first character position where the two words differ
      for (int j = 0; j < len; j++) {
        char c1 = w1.charAt(j);
        char c2 = w2.charAt(j);

        if (c1 != c2) {
          if (!graph.get(c1).contains(c2)) {           // avoid duplicate edges
            graph.get(c1).add(c2);                     // c1 -> c2: c1 comes before c2
            indegree.put(c2, indegree.get(c2) + 1);    // c2 now has one more prerequisite
          }
          break; // only the first differing char gives ordering info
        }
      }
    }

    // Topological sort via BFS (Kahn's algorithm)
    Queue<Character> queue = new LinkedList<>();
    for (char c : indegree.keySet()) {
      if (indegree.get(c) == 0)
        queue.offer(c); // start with chars that have no prerequisites
    }

    StringBuilder sb = new StringBuilder();
    while (!queue.isEmpty()) {
      char c = queue.poll();
      sb.append(c); // this char's position in the order is now finalized

      for (char neighbor : graph.get(c)) {
        indegree.put(neighbor, indegree.get(neighbor) - 1); // one fewer prerequisite remaining
        if (indegree.get(neighbor) == 0)
          queue.offer(neighbor); // all prerequisites met, ready to order
      }
    }

    if (sb.length() != indegree.size())
      return ""; // cycle detected — no valid ordering exists

    return sb.toString();
  }
}
