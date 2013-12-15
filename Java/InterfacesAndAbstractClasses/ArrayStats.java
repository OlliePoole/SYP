/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.and.pkgabstract.classes;

/**
 *
 * @author 12022846
 */
public class ArrayStats extends AbstractStats {

    public static final int MAX_ELEMENTS = 1000;
    protected double[] elements = new double[MAX_ELEMENTS];
    private int count;

    public double getElement(int i) {
        return elements[i];
    }

    public double getSum() {
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += getElement(i);
        }
        return sum;
    }

    public int getCount() {
        return count;
    }


    public void add(double element) {
        elements[count] = element;
        count++;
    }
}

