package framework;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p0073862
 */
public class KController {

    private KModel model;
    private KView view;

    public KController(KModel model, KView view) {
        this.model = model;
        this.view = view;
    }

    public void changeNbrSegments(int nbrSegments) {
        model.setNbrSegments(nbrSegments);
    }
    
    void nudge() {
        model.nudge();
    }
    
    void reset() {
        model.reset();
    }

    void but1() {
        model.but1();
    }

    void but2() {
        model.but2();
    }
}
