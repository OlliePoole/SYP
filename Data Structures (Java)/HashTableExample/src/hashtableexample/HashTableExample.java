/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hashtableexample;


import java.util.LinkedList;
import java.util.ListIterator;
/**
 *
 * @author olliepoole
 */
public class HashTableExample 
{

    public static void main(String[] args) 
    {
        final int HTS = 12; //hash table size
        LinkedList[] staff = new LinkedList[HTS];
        for (int i = 0; i < HTS; i++) 
        {
            staff[i]=new LinkedList();
        }
        addStaff(staff, "Sharon");
        addStaff(staff, "Chris");
        addStaff(staff, "Ian");
        addStaff(staff, "David");
        addStaff(staff, "Peter");
        addStaff(staff, "Muhammad");
        addStaff(staff, "Arantza");
        addStaff(staff, "Ken");
        addStaff(staff, "Richard");
        addStaff(staff, "Hong");
        addStaff(staff, "William");
        addStaff(staff, "Mark");
        addStaff(staff, "Bob");
        addStaff(staff, "Clare");
        addStaff(staff, "Faye");
        ListIterator iterator = staff[0].listIterator();
        for (int i = 0; i < HTS; i++)
        {
            iterator = staff[i].listIterator();
            System.out.print("staff[" + i + "]: ");
            while (iterator.hasNext())
            {
                System.out.print(iterator.next() + " ");
            }
            System.out.println();
        }
    }
    
  private static void addStaff(LinkedList[] staff, String key)
  {
      final int HTS = 12; //hash table size
      boolean isPresent = false;
      for (int x = 0; x < 12; x++)
      {
          for (int y = 0; y < staff[x].size();y++)
          {
              if (staff[x].get(y).equals(key))
              {
                 isPresent = true;
              }
          }
          
      }
      
      if (isPresent)
      {
          staff[hash(key)].addLast(key);
      }
      
  }
  
  private static int hash(String key)
  { 
      final int HTS = 12; //hash table size
      return Math.abs(key.hashCode()%HTS);
  }
  
  private static boolean hasKey(LinkedList[] staff)
  {
        for (LinkedList staff1 : staff) {
            if (staff1.isEmpty()) {
                return false;
            }
        }
      return true;
  }
  
  private static double[] avgChainLen(LinkedList[] staff)
  {
      double[] chain = new double[staff.length];
      
      for (int x = 0; x < staff.length; x++)
      {
          for (int y = 0; y < staff[x].size(); y++)
          {
              chain[x]++;
          }
          chain[x] = chain[x]/staff[x].size();
      }
      
      return chain;
      
  }
  
  private static double loadFactor(LinkedList[] staff)
  {
      final int HTS = 12; //hash table size
      return (staff.length/12);
  }
  
}


