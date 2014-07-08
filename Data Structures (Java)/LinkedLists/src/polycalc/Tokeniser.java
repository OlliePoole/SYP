/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polycalc;

import java.text.ParseException;

/**
 *
 * @author p0073862
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p0073862
 */
public class Tokeniser {

    private final String expr;
    private int index;

    /**
     * Create a Tokeniser that can divide an expression into a sequence of Terms
     *
     * @param expr the expression to be converted into Tokens
     */
    public Tokeniser(String expr) {
        this.expr = expr;
        index = 0;
    }

    /**
     * Determine whether we have read the last term in the expression
     *
     * @return true if there is another token to be read from the expression
     * false otherwise.
     */
    public boolean hasNext() {
        return hasCurrentChar();
    }

    /**
     * Get the next Token
     *
     * @throws java.text.ParseException
     * @pre hasNext()
     * @return the next token in the expression
     */
    public Term getNextToken() throws ParseException {
        int sign = 1;
        if (currentChar() == '-') {
            sign = -1;
            consumeChar();
        } else if (currentChar() == '+') {
            consumeChar();
        } else if (index != 0) {
            throw new ParseException("Expected operator", index);
        }

        int coeff;
        if (Character.isDigit(currentChar())) {
            coeff = readInteger();
        } else {
            coeff = 1;
            if (currentChar() != 'x') {
                throw new ParseException("Expected x got " + currentChar(), index);
            }
        }

        int power = 0;
        if (hasCurrentChar() && currentChar() == 'x') {
            consumeChar();
            power = 1;
            if (hasCurrentChar() && currentChar() == '^') {
                consumeChar();
                power = readInteger();
            }
        }

//        System.out.println("Coeff = " + coeff + " Power = " + power);
//        if (hasCurrentChar()) {
//            System.out.println("Current char = " + currentChar());
//        }
        return new Term(sign * coeff, power);

    }

    private int readInteger() throws ParseException {
        StringBuilder builder = new StringBuilder();
        while (hasCurrentChar() && Character.isDigit(currentChar())) {
            builder.append(currentChar());
            consumeChar();
        }

        try {
            int result = Integer.valueOf(builder.toString());
            return result;
        } catch (NumberFormatException ex) {
            throw new ParseException(ex.getMessage(), index);
        }

    }

    private char currentChar() throws ParseException {
        if (!hasCurrentChar()) {
            throw new ParseException("Unexpected end of input", index);
        }
        return expr.charAt(index);
    }

    private boolean hasCurrentChar() {
        return index < expr.length();
    }

//    private void consumeChar(char c) throws ParseException {
//        if (currentChar() != c) {
//            throw new ParseException("Expected character " + c, index);
//        }
//        consumeChar();
//    }

    private void consumeChar() {
        index++;
    }
}
