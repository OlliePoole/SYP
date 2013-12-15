/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.and.pkgabstract.classes;

/**
 *
 * @author 12022846
 */
public class TestBounds {

    public static void main(String[] args) {
        Bounds simple = new SimpleBounds();
        Bounds bstats = new BStats(new ArrayStats());

        simple.add(-1);
        simple.add(450);
        simple.add(1);

        bstats.add(56);
        bstats.add(34);
        bstats.add(1);

        System.out.println("SimpleBounds");
        showBounds(simple);
        System.out.println();
        System.out.println("BStats");
        showBounds(bstats);

    }

    private static void showBounds(Bounds bounds) {
        System.out.println("Min = " + bounds.getMin());
        System.out.println("Max = " + bounds.getMax());
    }
}

