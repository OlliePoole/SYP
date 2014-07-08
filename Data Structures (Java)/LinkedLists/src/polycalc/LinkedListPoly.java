/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package polycalc;

import java.util.Iterator;

/**
 *
 * @author olliepoole
 */
public class LinkedListPoly implements Polynomial
{
    
    private class Node
    {
        Term term;
        Node next;
    }
    
    Node head;
    Node current;
    Node previous;
    Iterator<Term> iter;
    
    public LinkedListPoly()
    {
        head = null;
        iter = iterator();
        current = null;
        previous = null;
    }
    
    public Iterator<Term> getIterator()
    {
        return iter;
    }
            
    
    
    @Override
    public void addTerm(Term term)
    {
        
    
        if (head == null)
        {
            Node newNode = new Node();
            newNode.term = term;
            newNode.next = head;
            head = newNode;
        }
        else
        {
            //Reset to start at the start of the list            
            current = head;
            previous = null;
            
            //start interating through
            while (iter.hasNext())
            {
                
                current.term = iter.next();
                
                //If the powers are the same...
                if (current.term.getPower() < term.getPower())
                {
                    if (previous == null)
                    {
                        //Add to the head of the list
                        Node newNode = new Node();
                        newNode.term = term;
                        newNode.next = head;
                        head = newNode;
                    }
                    else
                    {
                        //Add after an existing node
                        Node newNode = new Node();
                        newNode.term = term;
                        previous.next = newNode;
                        newNode.next = current;  
                    }
                      
                }
                previous = current; 
            }
    }
    }
            
        
        


    @Override
    public void addPoly(Polynomial poly)
    {
        //add an iterator to the second poly
        Iterator<Term> iter2 = poly.iterator();
        Term polyTerm;
        current = head;
        previous = null;
        
        //iterating through the new poly
        while (iter2.hasNext())
        {
            polyTerm = iter2.next();
            
            //iterating through the current poly
            while (iter.hasNext())
            {
                current.term = iter.next();
                
                //If the powers are the same...
                if (current.term.getPower() == polyTerm.getPower())
                {
                    //remove the existing term and update it
                    if (previous == null)
                    {
                        head = head.next.next;
                    }
                    else
                    {
                        previous.next = previous.next.next;
                    }
             
                    //add the updated term to the list
                    int temp = current.term.getCoefficient()+polyTerm.getCoefficient();
                    //check to see if the new coefficent is =0
                    if (temp != 0)
                    {
                        addTerm(new Term(temp, (current.term.getPower())));
                    }
                    
                    
                    
                }
                previous = current;
            }
        }
    }

    @Override
    public void multiplyByPoly(Polynomial poly)
    {
         //add an iterator to the second poly
        Iterator<Term> iter2 = poly.iterator();
        Term polyTerm;
        current = head;
        previous = null;
        
        //iterating through the new poly
        while (iter2.hasNext())
        {
            polyTerm = iter2.next();
            
            //iterating through the current poly
            while (iter.hasNext())
            {
                current.term = iter.next();
                
                //If the powers are the same...
                if (current.term.getPower() == polyTerm.getPower())
                {
                    //remove the existing term and update it
                    if (previous == null)
                    {
                        head = head.next.next;
                    }
                    else
                    {
                        previous.next = previous.next.next;
                    }
             
                    //add the updated term to the list
                    addTerm(new Term((current.term.getCoefficient()*polyTerm.getCoefficient()), (current.term.getPower())));
                    
                    
                }
                previous = current;
            }
        }
    }

    @Override
    public void clear()
    {
        //Deleting the link to the second node and remove the first node
        head = null;
    }

    @Override
    public Iterator<Term> iterator()
    {
        Iterator<Term> iterator = new Iterator<Term>()
        {
            
            @Override
            public boolean hasNext()
            {
                if (current == null)
                {
                    return head != null;
                }
                else
                {
                    return current.next != null;
                }
            }

            @Override
            public Term next()
            {
                previous = current;
                if (current == null)
                {
                    current = head;
                }
                else
                {
                    current = current.next;
                }
                return current.term;
            }

            @Override
            public void remove()
            {
                if (current == head)
                {
                    head = head.next;
                }
                else
                {
                    previous.next = current.next;
                }
                current = previous;
            }
           
        };
        return iterator;

      

    }
    
}


