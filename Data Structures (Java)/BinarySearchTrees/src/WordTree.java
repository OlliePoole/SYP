// U08223 Practical 10.
// Bodies of methods:
//   recordWord,
//   display and
//   numberOfEntries
// to be completed by student

import java.util.*;

/**
 * A class representing a binary tree containing words.
 *
 * @author Ollie Poole - 12022846
 */
public class WordTree {

    public String word;
    public ArrayList<Integer> lineNumbers;
    public WordTree left, right;
    public ArrayList<String> wordList;

    /**
     * Constructs a tree consisting of a single node, with the given word and
     * line number.
     *
     * @param w the word
     * @param lineNo the line number
     * @pre true
     * @post word tree containing word w on line lineNo has been constructed
     */
    public WordTree(String w, int lineNo) {
        word = w;
        lineNumbers = new ArrayList<>();
        lineNumbers.add(lineNo);
        left = null;
        right = null;
        wordList = new ArrayList<>();
    }

    /**
     * Records a new occurrence of the given word, in the tree.
     *
     * @param w the word
     * @param lineNo the line number
     * @pre this is a well formed binary search tree
     * @post if word was not in this tree, then the word and its line number
     * line have been inserted into ordered word tree, else line has been
     * appended to line-number list for word (if we haven't already recorded
     * that line number for this word)
     */
    public void recordWord(String w, int lineNo)
    {

        if (word.compareToIgnoreCase(w) < 0)
        {
            if (left == null)
            {
                left = new WordTree(w, lineNo);
            }
            else
            {
                left.recordWord(w, lineNo);
            }
        }
        else if (word.compareToIgnoreCase(w) > 0)
        {
            if (right == null)
            {
                right = new WordTree(w, lineNo);
            }
            else
            {
                right.recordWord(w, lineNo);
            }
        }
        else if (word.compareToIgnoreCase(w) == 0)
        {
            lineNumbers.add(lineNo);
        }
    
    }

    /**
     * Displays all the words in this tree.
     *
     * @pre this is a well formed binary search tree
     * @post words have been written out in alphabetical order, each followed by
     * ascending list of line numbers on which the word occurs
     */
    public void display()
    {
        WordTree t = this;
        wordList = inOrderTraversal(t, wordList);
        
        for (int x = wordList.size()-1; x >= 0; x--)
        {
            System.out.println(wordList.get(x));
        }
    }
    
    public ArrayList<String> inOrderTraversal(WordTree t, ArrayList <String> returnArray)
    {
        String returnString = "";
        if (t != null)
        {
            inOrderTraversal(t.left, returnArray);
            returnString += t.word + ": ";
            for (int x = 0; x < t.lineNumbers.size(); x++)
            {
                returnString += t.lineNumbers.get(x) + " ";
            }
            returnArray.add(returnString);
            
            inOrderTraversal(t.right, returnArray);
        }

        return returnArray;
    }



    /**
     * Counts how many different words there are in this tree.
     *
     * @pre this is a well formed binary search tree
     * @return the number of different words in tree
     */
    public int numberOfEntries() {
        
        
        return wordList.size();
    }

}


/*
run:
************************************
Producing a cross-reference for the file:
Moses.txt
************************************

1: Moses supposes his toeses are Roses,
2: But Moses supposes erroneously,
3: Moses he knowses his toeses aren't roses,
4: As Moses supposes his toeses to be!
5: Moses supposes his toeses are Roses,
6: But Moses supposes erroneously,
7: A mose is a mose!
8: A rose is a rose!
9: A toes a toes!
10: Hooptie doodie doodle
11: Moses supposes his toeses are Roses,
12: But Moses supposes erroneously,
13: For Moses he knowses his toeses aren't roses,
14: As Moses supposes his toeses to be!
15: Moses
16: (Moses supposes his toeses are roses)
17: Moses
18: (Moses supposes erroneously)
19: Moses
20: (Moses supposes his toeses are roses)
21: As Moses supposes his toeses to be!
22: A Rose is a rose is a rose is a rose is
23: A rose is for Moses as potent as toeses
24: Couldn't be a lily or a daphi daphi dilli
25: It's gotta be a rose cuz it rhymes with mose!
26: Moses!
27: Moses!
28: Moses!


A: 7 7 8 8 9 9 22 22 22 22 23 24 24 25 
are: 1 5 11 16 20 
aren't: 3 13 
As: 4 14 21 23 23 
be: 4 14 21 24 25 
But: 2 6 12 
Couldn't: 24 
cuz: 25 
daphi: 24 24 
dilli: 24 
doodie: 10 
doodle: 10 
erroneously: 2 6 12 18 
For: 13 23 
gotta: 25 
he: 3 13 
his: 1 3 4 5 11 13 14 16 20 21 
Hooptie: 10 
is: 7 8 22 22 22 22 23 
it: 25 
It's: 25 
knowses: 3 13 
lily: 24 
mose: 7 7 25 
Moses: 1 2 3 4 5 6 11 12 13 14 15 16 17 18 19 20 21 23 26 27 28 
or: 24 
potent: 23 
rhymes: 25 
rose: 8 8 22 22 22 22 23 25 
Roses: 1 3 5 11 13 16 20 
supposes: 1 2 4 5 6 11 12 14 16 18 20 21 
to: 4 14 21 
toes: 9 9 
toeses: 1 3 4 5 11 13 14 16 20 21 23 
with: 25 

number of words: 144
number of different words: 35
BUILD SUCCESSFUL (total time: 9 seconds)
*/