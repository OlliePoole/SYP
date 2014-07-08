/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olliepoole
 */
public abstract class Node
{

    public Node()
    {
        this.left = null;
        this.right = null;
    }
    Node left, right;

    public abstract boolean equalTo(Node y);

    public abstract boolean lessThan(Node y);

    public abstract void display();

    public void addNode(Node x) {

    }

}
