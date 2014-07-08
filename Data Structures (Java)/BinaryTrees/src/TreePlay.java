

public class TreePlay {
    
    public static void main(String[] args) {


       BinaryTree t = TreeUtilities.createRandomTree(); 
       TreeUtilities.showTree(t); 
       System.out.println("Pre-order traversal: " + t.preOrderTraversal(t));
       
       t.setReturnString("");
       System.out.println("In-order traversal: " + t.inOrderTraversal(t));

       t.setReturnString("");
       System.out.println("Post-Order traversal: " + t.postOrderTraversal(t));
       
       
       for (int x = 0; x < 5; x++)
       {
           t = TreeUtilities.createRandomTree();
           System.out.println("Leaf Count: " + leafCount(t));
           System.out.println("Height: " + heightOfTree(t));
           TreeUtilities.showTree(t);
       }

       // keep the following line as the last line of the main method
       System.exit(0); // <- exits the program immediately
       // (the above line is handy if there's an invisible pop-up window
       // still hanging about preventing the program from terminating)

    }
    
    public static int leafCount(BinaryTree t)
    {
        if (t == null)
        {
            return 0;
        }
        else
        {
            if (t.left == null && t.right == null)
            {
                return 1;
            }
            else
            {
                return leafCount(t.left) + leafCount(t.right);
            }
        }
        
    }
    
    public static int heightOfTree(BinaryTree t)
{
    if (t == null)
    {
        return 0;
    }
    else
    {
        return 1 + Math.max(heightOfTree(t.left), heightOfTree(t.right));
    }
}

}