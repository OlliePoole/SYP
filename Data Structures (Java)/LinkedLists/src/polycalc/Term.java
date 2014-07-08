

package polycalc;

/**
 *
 * Immutable class representing a term of the form cx^e that could appear in a
 polynomial expression, where x is the variable, c the coefficient, and
 e the power
 */
public final class Term {
    private final int coefficient;
    private final int power;
    
    /**
     * Create an immutable object representing a term
     * @param coefficient Coefficent of the term
     * @param power Power of the term
     */
    public Term(int coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }
    
    /**
     * Get the coefficient of this term
     * @return the coefficient
     */
    public int getCoefficient() {
        return coefficient;
    }
    
    /**
     * Get the power of the term
     * @return the power
     */
    public int getPower() {
        return power;
    }
}
