 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

/**
 *
 * @author olliepoole
 */
public class PostfixCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        GUI gui = new GUI();
                    }
                }
        );
    }   
}
