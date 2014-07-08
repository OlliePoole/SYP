

/** A class representing an (undirected) friendships graph.
  * It is assumed (for simplicity) that all nodes have unique labels.
  *
  */
public class FriendsGraph {


/** Stores information about the nodes in this graph, such as the
  * names of the people and the positions of the nodes when displayed. */
    public Node[] nodes;

    public boolean[][] adjacencyMatrix;

    public int size;
    private int nodesAddedSoFar;

/** Creates an empty graph.
  */
    public FriendsGraph(int size) {
        nodes = new Node[size];
        this.size = size;
        nodesAddedSoFar = 0;

        adjacencyMatrix = new boolean[size][size];
        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++)
               adjacencyMatrix[i][j] = false;

    }

/** Adds a node to the graph.
  *
  * @pre           there is not a node with this name already in the graph
  *                and nodesAddedSoFar<size
  * @post          the node has been added to the graph, with the
  *                given name and position
  * @param name    the name for the node
  * @param x       the x-coordinate of the centre of the node
  * @param y       the y-coordinate of the centre of the node
  */
   public void addNode(String name, int x, int y) {
       nodes[nodesAddedSoFar] = new Node(name,x,y);
       nodesAddedSoFar++;
   }


/** Adds an edge to the graph.
  *
  * @pre           all the nodes have been added to the graph, and
  *                nodes exist in the graph with the given labels person1,person2
  * @post          an edge person1 <-> person2 has been added to the graph
  * @param person1  the name of a node at one end of this edge
  * @param person2  the name of the node at the other end of this edge
  */
    public void addEdge(String person1, String person2) {
        // first, find the person1 node
        int i=0;
        while (i<size && !nodes[i].name.equals(person1))
            i++;
        // now nodes[i].name is person1

        // second, find the person2 node
        int j=0;
        while (j<size && !nodes[j].name.equals(person2))
            j++;
        // now nodes[j].name is person2

        addEdge(i,j);
        // as it's an undirected edge, add the reverse direction too
        addEdge(j,i);

    }


    private void addEdge(int node1, int node2 )
    {
        adjacencyMatrix[node1][node2] = true;

    }

/** Returns true if there is an edge from the first node to the second.
  *
  * @pre      0 < node1,node2 < size
  * @param node1   the source node of the queried edge
  * @param node2   the target node of the queried edge
  * @return        true iff there is an edge node1 -> node2
  */
    public boolean edgeFrom(int node1, int node2)
    {
        return adjacencyMatrix[node1][node2];
    }
    
    private int getNodeIndex(String person) 
    {
        int counter = 0;
        for (Node node : nodes) {
            if (node.name.equals(person))
            {
                return counter;
            }
            counter++;
        }
        return -1;
    }
    
    
        /** Returns true precisely when the two people are in 
      * the graph and are listed as friends.
      * @pre             true
      * @param person1   the first person
      * @param person2   the second person
      * @return          true iff these two people are friends    
      *                  in the graph
      */
    public boolean areFriends(String person1, String person2)
    {
        try
        {
            return adjacencyMatrix[getNodeIndex(person1)][getNodeIndex(person2)];
        }
        catch (IndexOutOfBoundsException ex)
        {
             return false;
        }

    }
    

    public void depthFirstTraversal() 
    {
        dft(0, new boolean[adjacencyMatrix.length]);
    }
    
    
    private void dft(int i, boolean[] visited)
    {
        System.out.println(nodes[i].name);
        visited[i] = true;
        for (int x = 0; x < adjacencyMatrix[i].length; x++)
        {
            if (adjacencyMatrix[i][x])
            {
                if (!visited[x])
                {
                    dft(x, visited);
                }
            }
        }
        
    }
    
    /*
    //GRAPH 1
    run:
    Remi and Victoria are friends.
    Remi and Lena are not friends.
    Mohammad and Lena are not friends.
    Guillaume
    Lena
    Tosan
    Victoria
    Remi
    Muhammad
    */
    
    
}
