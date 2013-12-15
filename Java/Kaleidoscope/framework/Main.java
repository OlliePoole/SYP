package framework;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p0073862
 */
public class Main {
    public static void main(String[] args) {
        KModel model = new KModel();
        KView view = new KView(model);
        KController controller = new KController(model, view);
        view.setController(controller);
        model.addObserver(view);
        view.update(model, null);
    }
}
