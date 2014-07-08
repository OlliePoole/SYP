 package polycalc;

/**
 *
 * Interface to be implemented by classes that represent polynomial expressions
 */
public interface Polynomial extends Iterable<Term> {

    /**
     * Add a term to the expression
     *
     * @param term the term to be added.
     */
    public void addTerm(Term term);

    /**
     * Add another polynomial to this one
     *
     * @post This polynomial is equal to its original value plus poly.
     * The added polynomial poly is unchanged
     * added
     * @param poly Expression to be added
     */
    public void addPoly(Polynomial poly);

    /**
     * Multiply this polynomial by another one
     *
     * @post This polynomial is equal to its original value multiplied by
     * the expression poly. The expression poly is unchhanged.
     * 
     * @param poly Expression to be added
     */
    public void multiplyByPoly(Polynomial poly);

    /**
     * Remove all terms from this polynomial
     */
    public void clear();
}


