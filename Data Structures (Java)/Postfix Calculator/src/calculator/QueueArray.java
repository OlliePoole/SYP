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
public class QueueArray implements TokenQueue
{
    private final ArrayList<Token> tokenList = new ArrayList<>();

    @Override
    public void add(Token tok)
    {
        tokenList.add(tok);
    }

    @Override
    public Token remove() throws NoSuchElementException
    {
        if (tokenList.isEmpty())
        {
            throw new NoSuchElementException();
        }
        else
        {
            return tokenList.remove(0);
        }
        
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
            return tokenList.get(0);
        }
        
    }

    @Override
    public int size()
    {
        return tokenList.size();
    }
    
}
