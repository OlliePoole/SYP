/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package polycalc;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author p0073862
 */
public class ALPoly implements Polynomial {
    private ArrayList<Term> termList = new ArrayList<Term>();

    @Override
    public void addTerm(Term term) {
        termList.add(term);
    }

    @Override
    public void addPoly(Polynomial poly) {
        //not implemented
    }

    @Override
    public void multiplyByPoly(Polynomial poly) {
        //not implemented
    }

    @Override
    public void clear() {
        termList.clear();
    }

    @Override
    public Iterator<Term> iterator() {
        return termList.iterator();
    }
    
}
