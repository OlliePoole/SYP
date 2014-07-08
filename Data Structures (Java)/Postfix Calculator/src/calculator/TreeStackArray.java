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
public class TreeStackArray
{
     private final ArrayList<Tree> treeList = new ArrayList<>();

    
    public int size()
    {
        return treeList.size();
    }

    
    public Tree peek() throws NoSuchElementException
    {
        if (treeList.isEmpty())
        {
            throw new NoSuchElementException();
        }
        else
        {
            return treeList.get(treeList.size() - 1);
        } 
    }

    
    public Tree pop() throws NoSuchElementException
    {
        if (treeList.isEmpty())
        {
            throw new NoSuchElementException();
        }
        else
        {
            return treeList.remove(treeList.size() - 1);
        }
    }

    
    public void push(Tree t)
    {
        treeList.add(t);
    }
    
    
    
}
