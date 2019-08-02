import java.util.*;

/*  Dijkstra's shortest path algorithm is a greedy algorithm used to find the shortest path between two nodes
    in a network of nodes ie a tree of nodes where each node has as at least one path to every other node. Dijkstra's
    algorithm works in two basic steps. First, from a given starting point N, the algorithm will prioritize the least
    weighted edges (the cheapest path between the current node to another node) and move to the node at the end of the
    cheapest edge making the new node N. Second, the algorithm updates the current shortest path to the nodes
    that branch off of N by adding the weight of the edge from N to next node with the shortest path
    to N. The current shortest path to node H only updates if a new path to H is shorter then the current path.
    This two part cycle of search and update continues in tell all of the nodes have been explored and their shortest
    path found.
 */

public class DijkstrasAlgorithm {
    //Number of vertices
    private static int V = 5;
    private static int minKey(int[] keys, Boolean[] mstSet)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        //Finds shortest distance among the nodes who's current shortest path has not been confirmed as shortest
        for (int v = 0; v < V; v++)
            if (!mstSet[v] && keys[v] < min) {
                min = keys[v];
                min_index = v;
            }
        //Returns the node
        return min_index;
    }

    private static void display(ArrayList<int[]> edges, int[] parentNodes, int[] keys){
        System.out.println("Distance to Vertice");
        for (int i = 1; i < V; i++){
            int parent = parentNodes[i];
            for (int[] edge: edges){
                if (edge[0] == parent || edge[1] == parent){
                    if ((edge[1] == i || edge[0] == i)){
                        System.out.println(i + "  -  " +  + keys[i]);
                    }
                }
            }
        }
    }
    private static void findShortestPath(ArrayList<int[]> edges){
        /*Initializes parent node array
          this is important if you want find
          the sequence of edges taken to produce the
          networks shortest path
         */
        int[] parent = new int[V];

        //Holds the current shortest path to the vertice at index i
        int[] keys = new int[V];

        //If index i is true the cheapest path has been found
        Boolean[] mstSet = new Boolean[V];

        //The shortest path to all nodes starts at max value
        for (int i = 0; i < V; i++){
            keys[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        //Distance to start
        keys[0] = 0;
        //The start's parent node
        parent[0] = -1;
        //Loops through nodes - 1 because the start's shortest path has already been found
        for (int count = 0; count < (V - 1); count++){
            //Find cheapest edge
            int u = minKey(keys, mstSet);
            //Shortest path to node u has been found
            mstSet[u] = true;
            //Finds edges that branch off of u
            for (int[] edge: edges) {
                        //If distance to node H through the edge u - H is cheaper then the current shortest path to H then the path is updated
                        if (edge[0] == u && !mstSet[edge[1]]) {
                            int W = keys[u] + edge[2];
                            if (keys[edge[1]] > W) {
                                keys[edge[1]] = W;
                                parent[edge[1]] = edge[0];
                            }
                        } else if (edge[1] == u && !mstSet[edge[0]]) {
                            int W = keys[u] + edge[2];
                            if (keys[edge[0]] > W) {
                                keys[edge[0]] = W;
                                parent[edge[0]] = edge[1];
                            }
                        }
            }
        }
        //Displays the shortest path
        display(edges, parent, keys);
    }
    public static void main(String[] args){
        //Adds edges to edges
        ArrayList<int[]> edges = new ArrayList<>();
        edges.add(new int[] {0, 3, 6});
        edges.add(new int[] {1, 4, 5});
        edges.add(new int[] {0, 1, 2});
        edges.add(new int[] {1, 2, 3});
        edges.add(new int[] {2, 4, 7});
        edges.add(new int[] {3, 4, 9});

        //Driver function
        findShortestPath(edges);
    }
}
