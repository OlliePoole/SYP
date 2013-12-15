/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.and.pkgabstract.classes;

import java.util.Scanner;

/**
 *
 * @author 12022846
 */
public class InterfacesAndAbstractClasses {

    private static TrendBoundableStats simpleBStats = new BStats(new SimpleStats());
    private static TrendBoundableStats arrayBStats = new BStats(new ArrayStats());
    private static Scanner scan = new Scanner(System.in);
    private static double aElement;
    private static double sElement;
    private static double aLastElement;
    private static double sLastElement;
    private static boolean firstSElement;
    private static boolean firstAElement;
    private static String option;

    public static void main(String[] args) {
        
        do {
            System.out.println("Options are:");
            System.out.println("AS: Add a value to the SimpleStats Collection");
            System.out.println("PS: Print out SimpleStats statistics");
            System.out.println("AA: Add a value to the ArrayStats Collection");
            System.out.println("PA: Print out ArrayStats statistics");
            System.out.print("Enter your option > ");
            option = scan.nextLine();

            
            if (option.equalsIgnoreCase("AS")) {
                addValue(simpleBStats);
            }
            if (option.equalsIgnoreCase("PS")) {
                printStats(simpleBStats);
            }
            if (option.equalsIgnoreCase("AA")) {
                addValue(arrayBStats);
            }
            if (option.equalsIgnoreCase("PA")) {
                printStats(arrayBStats);
            }
            
            System.out.println();
        } while (!option.equalsIgnoreCase("Q"));
    }

    private static void addValue(Addable addable) { 
        System.out.print("Enter value to be added > ");
        double d = scan.nextDouble();
        addable.add(d);
        
        switch (option) {
            case "AS":
                if (firstSElement == true) {
                    sElement = d;
                } else {
                    sLastElement = sElement;
                    sElement = d;
                }
                firstSElement = false;
                break;
            
            case "AA":
                if (firstAElement == true) {
                    aElement = d;
                } else {
                    aLastElement = aElement;
                    aElement = d;
                }
                firstAElement = false;
                break;
        }
    }

    private static void printStats(TrendBoundableStats stats) {
        System.out.println("Count = " + stats.getCount());
        System.out.println("Sum = " + stats.getSum());
        System.out.println("Average = " + stats.getAverage());
        System.out.println("Min " + stats.getMin());
        System.out.println("Max " + stats.getMax());
        System.out.println("Trend: " + printTrend(stats));
        
        
    }
    private static int printTrend(TrendBoundableStats stats) {
        
        switch (option) {
            case "PS":
                return stats.getTrend(sElement, sLastElement);
                
            case "PA":
                return stats.getTrend(aElement, aLastElement);
            
            default:
                return 0;
                
        }
        
        
        
    }
}

