

/** A simple container class representing a node of a friendship graph.
  */
public class Node {

/** The name at this node. */
    public String name;

/** The x-coordinate of this node, for display purposes. */
    public float x;

/** The y-coordinate of this node, for display purposes. */
    public float y;

/** Constructs a node with the given name, to be displayed at
  * the given position.
  */
    public Node(String name, int x, int y) {
        this.name = name;
        this.x = (float)x;
        this.y = (float)y;
     }


}