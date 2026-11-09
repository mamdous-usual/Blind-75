/*
 * LeetCode #133: Clone Graph
 * https://leetcode.com/problems/clone-graph/
 */

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


// ========================================
// Approach 1: DFS (Recursive)
// Time: O(V + E) | Space: O(V)
// where V = number of vertices (nodes)
//       E = number of edges
// ========================================
/*
class Solution {
  // HashMap to store mapping between original nodes and their clones. This
  // prevents infinite loops and ensures each node is cloned exactly once
  private HashMap<Node, Node> visited = new HashMap<>();

  public Node cloneGraph(Node node) {
    // Base case: if the input node is null, return null
    if (node == null) {
      return null;
    }

    // If we've already cloned this node, return the existing clone
    // This handles cycles in the graph and avoids duplicate clones
    if (visited.containsKey(node)) {
      return visited.get(node);
    }

    // Create a new node with the same value as the original
    // Initialize with an empty neighbors list (to be populated later)
    Node cloneNode = new Node(node.val);

    // Store the mapping between original node and clone before processing neighbors
    // This is crucial for handling cycles - if we encounter this node again, we'll return this clone
    visited.put(node, cloneNode);

    // Recursively clone all neighbors and add them to the clone's neighbor list
    for (Node neighbor : node.neighbors) {
      cloneNode.neighbors.add(cloneGraph(neighbor));
    }

    // Return the fully cloned node with all its neighbors
    return cloneNode;
  }
}
*/


// ========================================
// Approach 2: BFS (Iterative)
// Time: O(V + E) | Space: O(V)
// where V = number of vertices (nodes)
//       E = number of edges
// ========================================
class Solution {

  public Node cloneGraph(Node node) {
    // Handle edge case: if the input graph is empty, return null
    if (node == null)
      return null;

    // Map to store the mapping from original nodes to their clones
    // This serves two purposes: tracking visited nodes and accessing clones
    HashMap<Node, Node> visited = new HashMap<>();
    Queue<Node> queue = new LinkedList<>(); // Queue for BFS traversal of the original graph

    Node clonedNode = new Node(node.val); // Create the clone of the starting node
    visited.put(node, clonedNode); // Mark the starting node as visited and map it to its clone
    queue.offer(node); // Add the starting node to the queue to begin BFS

    // Perform BFS traversal
    while (!queue.isEmpty()) {
      Node current = queue.poll(); // Get the next node to process from the queue
      for (Node neighbor : current.neighbors) { // Iterate through all neighbors of the current node
        if (!visited.containsKey(neighbor)) { // If this neighbor hasn't been cloned yet
          Node clonedNeighbor = new Node(neighbor.val); // Create a clone of the neighbor node
          visited.put(neighbor, clonedNeighbor); // Mark the neighbor as visited and map it to its clone
          queue.offer(neighbor); // Add the neighbor to the queue for further processing
        }
        visited.get(current).neighbors.add(visited.get(neighbor)); // Add the cloned neighbor to the current clone’s neighbor list (builds edges in the cloned graph)
      }
    }

    return clonedNode; // Return the clone of the starting node (root of cloned graph)
  }
}
