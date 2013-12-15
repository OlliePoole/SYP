/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.and.pkgabstract.classes;

/**
 *
 * @author 12022846
 */
public class BStats implements BoundableStats, TrendBoundableStats {

    private Stats stats;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;

    public BStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public double getMin() {
        return min;
    }

    @Override
    public double getMax() {
        return max;
    }

    @Override
    public void add(double element) {
        if (element > max) {
            max = element;
        }
        if (element < min) {
            min = element;
        }
        stats.add(element);
    }

    @Override
    public double getSum() {
        return stats.getSum();
    }

    @Override
    public int getCount() {
        return stats.getCount();
    }

    @Override
    public double getAverage() {
        return stats.getAverage();
    }

    @Override
    public int getTrend(double element, double lastElement) {
        
        if (element > lastElement) {
            return 1;
        } else if (element < lastElement) {
            return -1;
        } else if (element == lastElement) {
            return 0;
        } else {
            return -2;
        }

    }
}
