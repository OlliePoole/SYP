package calculator;

/**
 *
 * @author p0073862
 */
public interface TokenTree {

    /**
     * Get the left subtree of this node
     * @return the left subtree
     */
    TokenTree getLeft();

    /**
     * Get the right subtree of this node
     * @return the right subtree
     */
    TokenTree getRight();

    /**
     * Get the token at this node
     * @return the Token at this node.
     */
    Token getToken();

    /**
     * Set the left subtree of this node
     * @param left the left subtree
     */
    void setLeft(TokenTree left);

    /**
     * Set the right subtree of this node
     * @param right the right to set
     */
    void setRight(TokenTree right);

    /**
     * Set the token at this node
     * @param token the token to set
     */
    void setToken(Token token);
    
}
