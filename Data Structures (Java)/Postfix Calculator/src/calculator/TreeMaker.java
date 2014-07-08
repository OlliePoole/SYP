
package calculator;

import java.text.ParseException;


public interface TreeMaker {
    
    /**
     * 
     * @param q A queue of tokens in postfix order
     * @return A TokenTree representing this expression. There is an example
     * of such a tree in the coursework documentation
     * 
     * @throws ParseException This exception should only be thrown if the
     * queue contains a syntactically invalid expression. However the method
     * is not required to throw an exception for every syntactically invalid
     * expression.
     */
    TokenTree makeTree(TokenQueue q) throws ParseException;
}
