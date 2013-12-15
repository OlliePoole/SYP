/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.and.pkgabstract.classes;

/**
 *
 * @author 12022846
 */
public class SimpleStats extends AbstractStats {
    private double sum;
    private double lastElement;
    private int count;
    
    public double getSum() {
        return sum;
    }
    
    public int getCount() {
        return count;
    }
    
    
    public void add(double element) {
        lastElement = element;
        count++;
        sum += element;
    }
    
    public double getLastElement() {
        return lastElement;
    }
}
