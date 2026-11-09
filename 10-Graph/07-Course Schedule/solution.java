/*
 * LeetCode #207: Course Schedule
 * https://leetcode.com/problems/course-schedule/
 */

import java.util.*;

// ========================================
// Approach: DFS (Recursive) — Cycle Detection
// Time: O(V + E) | Space: O(V + E)
// where V = number of courses, E = number of prerequisites
// ========================================

class Solution {

  // Returns true if all courses can be finished without cyclic dependencies
  public boolean canFinish(int numCourses, int[][] prerequisites) {

    // Build adjacency list to represent the directed course graph
    List<List<Integer>> graph = new ArrayList<>();

    // Initialize an empty list for each course
    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }

    // Add edge b → a meaning "take b before a"
    for (int[] p : prerequisites) {
      int a = p[0]; // Course that depends on b
      int b = p[1]; // Prerequisite course
      graph.get(b).add(a);
    }

    // 0 = unvisited, 1 = in current DFS path, 2 = fully processed
    int[] state = new int[numCourses];

    // Start DFS from every unvisited node to handle disconnected components
    for (int i = 0; i < numCourses; i++) {
      // Only visit nodes that haven't been explored yet
      if (state[i] == 0) {
        if (hasCycle(i, graph, state)) {
          return false; // Cycle found, can't complete all courses
        }
      }
    }

    return true; // No cycle found, all courses can be completed
  }

  // DFS helper that detects a cycle via back edge tracking
  private boolean hasCycle(int node, List<List<Integer>> graph, int[] state) {

    if (state[node] == 1)
      return true; // Back edge = cycle detected

    if (state[node] == 2)
      return false; // Already fully explored, skip

    state[node] = 1; // Mark as in-progress before visiting neighbors

    // Recursively visit all courses that depend on this node
    for (int neighbor : graph.get(node)) {
      if (hasCycle(neighbor, graph, state)) {
        return true; // Propagate cycle up the call stack
      }
    }

    state[node] = 2; // Mark as fully processed, no cycle from this node
    return false;
  }
}
