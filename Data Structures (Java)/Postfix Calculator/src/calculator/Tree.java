/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

/**
 *
 * @author olliepoole
 */
public class Tree implements TokenTree
{

    private Token token;
    private Tree left;
    private Tree right;

    public Tree(Token token)
    {
        this.left = null;
        this.right = null;
        this.token = token;
    }
    
    public Tree()
    {
        this.left = null;
        this.right = null;
        this.token = null;
    }
    
    @Override
    public TokenTree getLeft() {
        return left;
    }

    @Override
    public TokenTree getRight() {
        return right;
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public void setLeft(TokenTree left) {
       this.left = (Tree)left;
    }

    @Override
    public void setRight(TokenTree right) {
        this.right = (Tree)right;
    }

    @Override
    public void setToken(Token token) {
        this.token = token;
    }
    
}
