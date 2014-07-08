/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 *
 * @author olliepoole
 */
public class MakeTree implements TreeMaker
{
    @Override
    public TokenTree makeTree(TokenQueue q) throws ParseException
    {
        QueueArray queue = (QueueArray)q;
        TreeStackArray stack = new TreeStackArray();
        
        while (queue.size() > 0)
        {
            Token token = queue.remove();
            if (token.isNumber())
            {
                stack.push(new Tree(token));
            }
            else if (token.isOperator())
            {
                try
                {   
                    Tree tree1 = stack.pop();
                    Tree tree2 = stack.pop();

                    Tree tree3 = new Tree(token);
                    tree3.setLeft(tree1);
                    tree3.setRight(tree2);

                    stack.push(tree3);
                } 
                catch (NoSuchElementException ex)
                {
                    throw new ParseException("Less than two tree's in stack", 0);
                }
            }           
        }
        
        if (stack.size() != 1)
        {
            throw new ParseException("More than one final tree in stack after loop", 0);
        }
        
        return stack.pop(); 
    }
    
}
