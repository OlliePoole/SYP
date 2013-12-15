/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.and.pkgabstract.classes;

/**
 *
 * @author 12022846
 */
public abstract class AbstractStats implements Stats{
    
    public abstract double getSum();
    
    public abstract int getCount();
    
    public double getAverage() {return getSum()/getCount();};
    
    public abstract void add(double element);
    
}
