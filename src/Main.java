import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

class Solution {
    private HashMap <Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        if (visited.containsKey(node)) { // If the node was already visited before
            return visited.get(node); // return the 'visited'
        }

        Node cloneNode = new Node(node.val, new ArrayList()); // Create a clone for the given node.
        visited.put(node, cloneNode); // node key and map value is cloneNode

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor: node.neighbors) {  // each node has List<Node> for field. Loop through
            cloneNode.neighbors.add(cloneGraph(neighbor)); // retrieve the List field of the cloneNode
            // and recursively call method on the neighbors of the OG node.
        }
        return cloneNode;
    }
}
/*
            1----2          supposing this is our graph
            |    |
            3----4
                            1, [2,3] 'visited' does not contain '1'. so add a clone node of same val to 'visited' with empty List field
                                     need to add neighbors of the node to empty List field looping through [2,3]. However loop calls
                                     cloneGraph(2) recursively. At this point, '1' is in 'visited' and the original '2' node is new 'node'
                            2, [1,4] '2' clone made, and clone added as value in 'visited'. Check the OG node neighbor '1'. Which shows in
                                     'visited' so no DFS, just return the value (cloneNode) at key of '1'. Since no recursion called, we
                                     move directly to neighbor 4. Its not in 'visited' so create clone and map it. Recursively call cloneGraph(4)
                                     At this point, 'visited' contains (1,2). Recall a node only cloned during its own recursive call
                            4, [2,3] Create clone, and Map. 2 in 'visited' so return the clone. recursively call cloneGraph(3). visited = (1,2,4)
                            3, [1,4] create clone of 3. Map clone. 1 and 4 in visited, so both return their clones. At this point, those were
                                     the final calls on the call stack. cloneGraph(3) picks up after loop and returns its cloneNode to
                                     cloneGraph(4) and adds 3 to its neighbords List. cloneNode 4 then returned to cloneGraph(2) and completes
                                     2's neighbors List. 2 is then returned to cloneGraph(1) so cloneNode of 1 has neighbors of [2]. Finally
                                     we get to finish second neighbor in for loop from cloneGraph(1) and call cloneGraph(3). we see its
                                     already in 'visited' so we return its clone so cloneNode List = [2,3] and cloneNode 1 returned to complete
                                     the function. we only need to return a single Node because they have all been linked. There is no 'graph'
                                     variable.


 */