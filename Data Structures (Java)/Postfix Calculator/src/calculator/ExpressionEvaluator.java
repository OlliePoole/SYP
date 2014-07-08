/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

import java.text.ParseException;

/**
 *
 * @author olliepoole
 */
public class ExpressionEvaluator implements Evaluator
{
    
    @Override
    public int evaluate(TokenQueue q) throws ParseException
    {
        TokenStack tokenStack = new TokenStackArray();
        QueueArray tokenQueue = (QueueArray) q;
        
        while (tokenQueue.size() != 0)
        {
            if (tokenQueue.peek().isNumber())
            {
                tokenStack.push(tokenQueue.remove());
            }
            else if (tokenQueue.peek().isOperator())
            {
                if (tokenStack.size() < 2)
                {
                    throw new ParseException("Syntax Error", -1);
                }
                else
                {
                    int numberA = tokenStack.pop().getValue();
                    int numberB = tokenStack.pop().getValue();
                    
                    Token operator = tokenQueue.remove();
                    
                    switch (operator.getValue())
                    {
                        case 0:
                            //PLUS
                            tokenStack.push(new Token(0, numberB+numberA));
                            break;
                            
                        case 1:
                            //MINUS
                            tokenStack.push(new Token(0, numberB-numberA));
                            break;
                            
                        case 2:
                            //TIMES
                            tokenStack.push(new Token(0, numberB*numberA));
                            break;
                            
                        case 3:
                            //DIVIDE
                            tokenStack.push(new Token(0, numberB/numberA));
                            break;                 
                    }
                    
                }
            }
        }
        
        if (tokenStack.size() > 1)
        {
            throw new ParseException("Syntax Error", 1);
            
        }
        else
        {
            return tokenStack.pop().getValue();
        }
        
        
    }

}
