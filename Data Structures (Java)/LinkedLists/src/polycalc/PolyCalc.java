/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polycalc;

/**
 *
 * @author p0073862
 */
public class PolyCalc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        createAndShowGui();
                    }
                }
        );

    }

    private static void createAndShowGui() {
        Polynomial entryPoly = new LinkedListPoly();
        Polynomial storePoly = new LinkedListPoly();

        GUI view = new GUI(entryPoly, storePoly);
    }

}
