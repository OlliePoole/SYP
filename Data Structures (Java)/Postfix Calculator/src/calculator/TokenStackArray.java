/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author olliepoole
 */
public class TokenStackArray implements TokenStack
{
    private final ArrayList<Token> tokenList = new ArrayList<>();

    @Override
    public int size()
    {
        return tokenList.size();
    }

    @Override
    public Token peek() throws NoSuchElementException
    {
        if (tokenList.isEmpty())
        {
            throw new NoSuchElementException();
        }
        else
        {
            return tokenList.get(tokenList.size() - 1);
        } 
    }

    @Override
    public Token pop() throws NoSuchElementException
    {
        if (tokenList.isEmpty())
        {
            throw new NoSuchElementException();
        }
        else
        {
            return tokenList.remove(tokenList.size() - 1);
        }
    }

    @Override
    public void push(Token t)
    {
        tokenList.add(t);
    }
    
    
    
}
