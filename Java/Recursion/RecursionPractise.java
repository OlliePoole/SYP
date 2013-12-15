/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursionpractise;

/**
 *
 * @author olliepoole
 */
public class RecursionPractise {

    public static void main(String[] args) {

        ////////////////// Below is print out code for Exercise 2 ///////////////////
        System.out.println("2 to the power 5 is " + power(2, 5));
        System.out.println("4 to the power 0 is " + power(4, 0));
        System.out.println("0 to the power 6 is " + power(0, 6));
        System.out.println("-7 to the power 3 is " + power(-7, 3));
        System.out.println("0.5 to the power 2 is " + power(0.5, 2) + "\n");

        
        ////////////////// Below is print out code for Exercise 3 ///////////////////
        for (int i = 1; i <= 8; i++) {
            System.out.println("chessBoardSquares(i) for i=" + i
                    + " is " + chessBoardSquares(i));
        }
        System.out.println("\n");

        ////////////////// Below is print out code for Exercise 4 ///////////////////
        int[] a = {1, 2, 3, 4, 5, 6};
        int[] b = {1, 2, 4, 8, 16};
//        int[] a = {1, 2, 5, 4, 5, 6};
//        int[] b = {1, 2, 3, 4, 5};

        System.out.println("a: " + a[0] + "," + a[1] + "," + a[2] + "," + a[3] + "," + a[4] + "," + a[5]);
        System.out.println("b: " + b[0] + "," + b[1] + "," + b[2] + "," + b[3] + "," + b[4] + "\n");

        for (int i = 1; i <= 5; i++) {
            System.out.println("arrayMatch(i,a,b) for i=" + i
                    + " is " + arrayMatch(i, a, b));
        }
        System.out.println("\n");

        ////////////////// Below is print out code for Exercise 5 //////////////
        for (int i = 1; i <= 5; i++) {
            System.out.println("triangleNumber(" + i + ") is "
                    + triangle(i));
        }

        ////////////////// Below is print out code for Exercise 6 //////////////
        String[] as = {"Red", "Yellow", "Pink", "Green", "Orange"};
        for (int i = 1; i < as.length; i++) {
            System.out.println("Printing first " + i);
            printFirstN(as, i);
        }
    }

    ////////////////// Below is code for Exercise 5 ///////////////////
    // YOU HAVE TO IMPLEMENT THIS METHOD!!
    public static double power(double y, int n) {
        assert n >= 0;
        if (n == 0) {
            return 1;
        } else {
            return y * power(y, n - 1);
        }
    }

    ////////////////// Below is code for Exercise 3 ///////////////////
    // YOU HAVE TO IMPLEMENT THIS METHOD!!
    public static int chessBoardSquares(int n) {
        assert n >= 1;
        int nbr;
        if (n == 1) {
            return 1;
        } else if (n == 0) {
            return 0;
        } else {
            nbr = (4 * n) - 4;
            return nbr += chessBoardSquares(n - 2);
        }
    }

    ////////////////// Below is code for Exercise 4 ///////////////////
    // YOU HAVE TO IMPLEMENT THIS METHOD!!
    public static boolean arrayMatch(int n, int[] a, int[] b) {
        assert 0 <= n && n <= a.length && 0 <= n && n <= b.length;
        n--;
        if (a[n] == b[n]) {
            if (n == 0) {
                return true;
            } else {
                return arrayMatch(n, a, b);
            }

        } else {
            return false;
            //returning false as if the first dont match there is
            //no point checking the rest

            /////////////////////////////////////////////////////////////////////
            //Wasn't sure if this was meant to check if the digits are the same//
            //i.e. array1 = [1, 2, 3] and array2 = [1, 2, 3] checking if       //
            //array1[1] = array2[1]                                            //
            //or if every digit is the same i.e.                               //  
            //array1 = [1, 1, 1] and array2 = [1, 1, 1]                        //
            //But i have implemented for the first option                      //
            /////////////////////////////////////////////////////////////////////
        }

    }

    ////////////////// Below is code for Exercise 5 ///////////////////
    // YOU HAVE TO IMPLEMENT THIS METHOD!!
    public static int triangleNumber(int i) {
        assert i >= 0;

        return 0;//dummy value
    }

////////////////// Below is code for Exercise 5 ///////////////////
    // YOU HAVE TO IMPLEMENT THIS METHOD!!
    public static int triangle(int n) {

        // pre: n >= 1
        // post: the result is the sum 1 + 2 + ... + n
        assert n >= 1;
        if (n == 1) {
            return 1;

        } else {
            n += triangle(n - 1);
            return n;

        }

    }

    ////////////////// Below is code for Exercise 6 ///////////////////
    ///////////////YOU HAVE TO IMPLEMENT THIS METHOD!!
    public static void printFirstN(String[] a, int n) {

        assert 0 <= n && n <= a.length;

        if (n == 1) {
            System.out.println(a[n - 1]);
        } else {
            printFirstN(a, n - 1);
            System.out.println(a[n - 1]);
            
        }

    }

}
