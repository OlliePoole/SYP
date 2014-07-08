


/** Represents a simple binary tree of integers.
  *
  */
public class BinaryTree
{

/** The integer stored at this tree node. */
    public int data;

/** A reference to this node's left subtree. */
    public BinaryTree left;

/** A reference to this node's right subtree. */
    public BinaryTree right;
    
    
    private String returnString = "";

/** Constructs a binary tree with a single node, using the given value.
  * Note that there is no constructor to construct an empty tree;
  * an empty tree is just represented by a null pointer.
  *
  * @param val     the integer value to be placed at the root node of this tree
  */
    public BinaryTree(int val)
    {
        data = val;
        left = null;
        right = null;
        
    }
    
    public void setReturnString(String s)
    {
        this.returnString = s;
    }

    public String preOrderTraversal(BinaryTree rt)
    {
        if (rt != null)
        {
            returnString += " " + rt.data;
            preOrderTraversal(rt.left);
            preOrderTraversal(rt.right);  
            
        }
        return returnString;
        
    }

    public String inOrderTraversal(BinaryTree rt)
    {
        
        if (rt != null)
        {
            inOrderTraversal(rt.left);
            returnString += " " + rt.data;
            inOrderTraversal(rt.right);
        }
        return returnString;
    }
    
    public String postOrderTraversal(BinaryTree root)
    {

        if (root != null)
        {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            returnString += " " + root.data;
        }
        return returnString;
    }


// These are additional attributes that are used when working out how to
// draw the tree. You should ignore this code but leave it here.

    public int x,y;     // display position of this tree node

    public int boxWidth;      // used for calculating x,y coordinates
    public int nodeFromLeft;  // used for calculating x,y coordinates

    public int xdfp;          // x-distance of this node from parent node
    public int height;        // to calculate the height/depth of the tree


}
