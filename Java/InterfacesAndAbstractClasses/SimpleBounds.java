/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.and.pkgabstract.classes;

/**
 *
 * @author 12022846
 */
public class SimpleBounds implements Bounds {
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public void add(double element) {
        if (element > max) {
            max = element;
        }
        if (element < min) {
            min = element;
        }
    }
}

