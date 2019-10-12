//TODO Rewrite

import java.util.*;

/*Prim's algorithm, also known as the minimum spanning tree algorithm MST, is a greedy algorithm used to find the minimum spanning tree from a network of
nodes from a starting origin node. Problems which can utilize Prim's algorithm have 4 general characteristics. Firstly, they have a origin or starting
node Ex. John is at house X. Secondly, the problem has a network of nodes, that is to say that every node has at least one path to every other node Ex.
John wants to visit N houses which are all connected by R roads. Thirdly, each edge [bridge between two nodes] of the network has a weight
[cost of travel] Ex. The next R lines hold houses A and B and T the time to travel between them. Lastly, the problem must be looking for the most efficient
way to travel to all the other nodes from the origin [without cycling].
 */

public class PrimsMinimumSpanningTreeAlgorithm {
    private static int V = 5;


    //This function returns the index of the lightest edge that does not travel to a vertices
    //that is in the MST
    private static int minKey(int[] keys, Boolean[] mstSet)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!mstSet[v] && keys[v] < min) {
                min = keys[v];
                min_index = v;
            }
        return min_index;
    }

    private static void display(ArrayList<int[]> edges, int[] parentNodes){
        System.out.println("Edges\tWeight");
        ArrayList<int[]> done = new ArrayList<>();
        for (int i = 1; i < V; i++){
            int parent = parentNodes[i];
            for (int[] edge: edges){
                if (edge[0] == parent || edge[1] == parent){
                    if ((edge[1] == i || edge[0] == i) && !done.contains(edge)){
                        System.out.println(parent + "  -  " + i + "   " + edge[2]);
                        done.add(edge);
                    }
                }
            }
        }
    }
    private static void getMST(ArrayList<int[]> edges){
        /*parent holds the parent nodes of V nodes. Index Y will hold the vertice X and
        edge X Y will have the current smallest weight connecting Y to the tree.
         */
        int[] parent = new int[V];

        //keys hold the current smallest weight linking node index i to another node in the tree
        int[] keys = new int[V];

        //mstSet hold the state of the vertices, if index i == false then the vertice i has not yet been included int the MST
        Boolean[] mstSet = new Boolean[V];

        for (int i = 0; i < V; i++){
            //All keys start with max weight
            keys[i] = Integer.MAX_VALUE;
            //Initialized false because no values are in the MST
            mstSet[i] = false;
        }
        //Starting node has weight 0
        keys[0] = 0;
        //Starting node does not have a parent [-1 is the conventional way of denoting a lack of]
        parent[0] = -1;
        //Vertices are added to the MST 1 by 1. The loop runs # vertices - 1 times because the origin node has been set and finalized
        for (int count = 0; count < (V - 1); count++){
            //gets index of the cheapest node
            int u = minKey(keys, mstSet);
            //Adds the node to the MST
            mstSet[u] = true;

            //Checks u's adjacent nodes for updatable values
            for (int[] edge: edges){
                //W = weight of the edge
                int W = edge[2];
                //if A == probe && B is not in MST
                if (edge[0] == u && !mstSet[edge[1]]){
                    //if the current lowest weight is greater then the other possible weight
                    if (keys[edge[1]] > W) {
                        //update the values
                        keys[edge[1]] = W;
                        //update the parent node
                        parent[edge[1]] = edge[0];
                    }
                    //if B == probe && A is not in MST then execute a similar update
                } else if (edge[1] == u && !mstSet[edge[0]]){
                    if (keys[edge[0]] > W) {
                        keys[edge[0]] = W;
                        parent[edge[0]] = edge[1];
                    }
                }
            }
        }
        display(edges, parent);
    }
    public static void main(String[] args){
        //Edges will hold the edges of the network
        ArrayList<int[]> edges = new ArrayList<>();
        edges.add(new int[] {0, 3, 6});
        edges.add(new int[] {1, 4, 5});
        edges.add(new int[] {0, 1, 2});
        edges.add(new int[] {1, 2, 3});
        edges.add(new int[] {2, 4, 7});
        edges.add(new int[] {3, 4, 9});

        //Get MST is the driver function GOTO line 48
        getMST(edges);
    }
}
