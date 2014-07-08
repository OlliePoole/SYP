/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.NoSuchElementException;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author olliepoole
 */
public class GUI
{
    //Frame
    private final JFrame frame = new JFrame("PostFix Calculator");
    
    //Entry field
    private final JTextField entryField = new JTextField(25);
    
    //Panels for each row
    private final JPanel switcherPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JPanel topRowPanel = new JPanel();
    private final JPanel firstRowPanel = new JPanel();
    private final JPanel secondRowPanel = new JPanel();
    private final JPanel thirdRowPanel = new JPanel();
    private final JPanel forthRowPanel = new JPanel();
    
    //infix vs postFix
    private boolean inFix = false;
    
    //String for recursive inOrderTraversal
    String returnString = "";
     
    public GUI()
        {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container contentPane = frame.getContentPane();
            
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
                
            switcherPanel.setLayout(new BoxLayout(switcherPanel, BoxLayout.X_AXIS));
            ButtonGroup group = new ButtonGroup();
            
            switcherPanel.add(new JLabel("Infix"));
            
            JRadioButton inFixRButton = new JRadioButton();
            inFixRButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                       inFix = true;
                       if (!entryField.getText().isEmpty())
                       {
                           convertToInFix(entryField.getText());
                       }
                       
                 }
            });
            group.add(inFixRButton);
            switcherPanel.add(inFixRButton);
            
            switcherPanel.add(new JLabel("Postfix"));
            
            JRadioButton postFixRButton = new JRadioButton();
            postFixRButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                   inFix = false;
                   
                   TokenQueue queue = convertToPostFix(entryField.getText());
                   String output = "";
                   int counter = queue.size();
                   for (int x = 0; x < counter; x++)
                   {
                       output += queue.remove().toString();
                   }
                   entryField.setText(output);                   
                   
                 }
            });
            postFixRButton.setSelected(true);
            group.add(postFixRButton);
            switcherPanel.add(postFixRButton);

            contentPane.add(switcherPanel);
            
            contentPane.add(entryField);
            
            entryField.setEditable(false);
            
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            
            JPanel bracketsPanel = new JPanel();
            bracketsPanel.setLayout(new BoxLayout(bracketsPanel, BoxLayout.X_AXIS));
            bracketsPanel.add(addButton("("));
            bracketsPanel.add(addButton(")"));
            
            buttonPanel.add(bracketsPanel);
            
            topRowPanel.setLayout(new BoxLayout(topRowPanel, BoxLayout.X_AXIS));
            topRowPanel.add(addClearButton());
            topRowPanel.add(addBackButton());
            topRowPanel.add(addButton("/"));
            
            buttonPanel.add(topRowPanel);
            
        
            //First row
            firstRowPanel.setLayout(new BoxLayout(firstRowPanel, BoxLayout.X_AXIS));
            for (int x = 9; x >= 7; x--)
            {
                firstRowPanel.add(addButton(Integer.toString(x)));
            }
            firstRowPanel.add(addButton("*"));
            buttonPanel.add(firstRowPanel);
                     
            //Second row
            secondRowPanel.setLayout(new BoxLayout(secondRowPanel, BoxLayout.X_AXIS));
            for (int x = 6; x >= 4; x--)
            {
                secondRowPanel.add(addButton(Integer.toString(x)));
            }
            secondRowPanel.add(addButton("-"));
            buttonPanel.add(secondRowPanel);
                    
            //Third row
            thirdRowPanel.setLayout(new BoxLayout(thirdRowPanel, BoxLayout.X_AXIS));
            for (int x = 3; x >= 1; x--)
            {
                thirdRowPanel.add(addButton(Integer.toString(x)));
            }
            thirdRowPanel.add(addButton("+"));
            buttonPanel.add(thirdRowPanel);
                    
            //bottom row
            forthRowPanel.setLayout(new BoxLayout(forthRowPanel, BoxLayout.X_AXIS));
            forthRowPanel.add(addButton("0"));
            forthRowPanel.add(addSpaceButton());
            forthRowPanel.add(addEqualsButton());
            
            buttonPanel.add(forthRowPanel);
            
            contentPane.add(buttonPanel);

             frame.pack();
             frame.setVisible(true);
        }
    
    private JButton addButton(final String number)
    {
        final JButton button = new JButton(number);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String current = entryField.getText();
                entryField.setText(current + number);
            }
        });
        return button;
    }
    
    private JButton addSpaceButton()
    {
        final JButton button = new JButton("SPACE");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String current = entryField.getText();
                entryField.setText(current+ " ");
            }
        });
        return button;
    }
    
    private JButton addEqualsButton()
    {
        final JButton button = new JButton("=");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Tester test = new Tester(); 
                int eval = 0;
                boolean error = false;
                
                try
                {
                    if (inFix)
                    {
                        TokenQueue queue = convertToPostFix(entryField.getText());
                        ExpressionEvaluator ee = new ExpressionEvaluator();
                        eval = ee.evaluate(queue);
                    } 
                    else
                    {
                         TokenQueue tq = generateTokenQueue(entryField.getText());
                         ExpressionEvaluator ee = new ExpressionEvaluator();
                         eval = ee.evaluate(tq);
                    }
                    
                }
                catch (ParseException | NoSuchElementException ex)
                {
                    JOptionPane.showMessageDialog(frame, "Syntax Error", "Error",
                    JOptionPane.ERROR_MESSAGE);
                    error = true;
                }            
                if (!error)
                {
                    entryField.setText(eval + "");
                }         
            }
        });
        return button;
    }
    
    private JButton addClearButton()
    {
        final JButton button = new JButton("AC");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryField.setText("");
            }
        });
        return button;
    }
      
    private JButton addBackButton()
    {
        final JButton button = new JButton("Del");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String current = entryField.getText();
                if (!(current.isEmpty()))
                {
                   entryField.setText(current.substring(0, current.length()-1));
                }   
            }
        });
        return button;
    }

    public TokenQueue generateTokenQueue(String s)
    {
        Tokeniser tokeniser = new Tokeniser(s);
        QueueArray tokenQueue = new QueueArray();
        
        while (tokeniser.hasNext())
        {
            tokenQueue.add(tokeniser.next());
        }
        return (TokenQueue)tokenQueue;
    }  

    public TokenQueue convertToPostFix(String s)
    {
        TokenStackArray stack = new TokenStackArray();
        QueueArray queue = new QueueArray();
        Tokeniser tokeniser = new Tokeniser(s);
        
        while (tokeniser.hasNext())
        {
            Token token = tokeniser.next();
            if (token.isNumber())
            {
                queue.add(token);
            }
            else if (token.isOperator())
            {
                while ((stack.size() > 0) && (stack.peek().isOperator()) && (stack.peek().getPrecedence() > token.getPrecedence()))
                {
                    queue.add(stack.pop());
                }

                stack.push(token);
            }
            else if (token.getValue() == 5)
            {
                stack.push(token);
            }
            else if (token.getValue() == 6)
            {
                try
                {
                    while (stack.peek().getValue() != 5)
                    {
                        queue.add(stack.pop());
                    }
                    stack.pop();
                }
                catch (NoSuchElementException ex)
                {
                    throw new NoSuchElementException();
                }
            }
        }
        while (stack.size() > 0)
        {
            queue.add(stack.pop());
        }
        
        return queue;
    }

    public void convertToInFix(String s) 
    {
        TokenQueue queue = generateTokenQueue(s);
        MakeTree makeTree = new MakeTree();
        try
        {
            Tree tree = (Tree)makeTree.makeTree(queue);
            returnString = "";
            inOrderTraversal(tree);
            entryField.setText(returnString);
        }
        catch (ParseException ex)
        {
            JOptionPane.showMessageDialog(frame, "Syntax Error", "Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void inOrderTraversal(Tree tree)
    {
        if (tree != null)
        {
            if (tree.getToken().isOperator()
                    && tree.getLeft().getToken().isNumber()
                    && tree.getRight().getToken().isNumber())
            {
                returnString += "(";
                inOrderTraversal((Tree)tree.getLeft());
                returnString += tree.getToken() + " ";
                inOrderTraversal((Tree)tree.getRight());
                returnString += ")";
            }
            else
            {
                inOrderTraversal((Tree)tree.getLeft());
                returnString += tree.getToken() + " ";
                inOrderTraversal((Tree)tree.getRight());
            }
            
        }     
    }
}


