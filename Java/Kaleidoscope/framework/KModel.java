package framework;

import java.util.ArrayList;
import java.util.Observable;
import student.ObjectCell;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p0073862
 */
public class KModel extends Observable {

    private static final int RADIUS = 300;
    private static int WIDTH = RADIUS * 2;
    private int nbrSegments = 12;
    ObjectCell shapes = new ObjectCell(WIDTH, WIDTH);

    public ArrayList<int[]> getShapes() {
        return shapes.getShapeData();
    }

    public int getNbrSegments() {
        return nbrSegments;
    }

    public void setNbrSegments(int nbrSegments) {
        this.nbrSegments = nbrSegments;
        sendUpdate();
    }

    public void reset() {
        shapes.reset();
        sendUpdate();
    }

    private void sendUpdate() {
        setChanged();
        notifyObservers();
    }

    void nudge() {
        shapes.nudge();
        sendUpdate();
    }

    void but1() {
        shapes.but1();
        sendUpdate();
    }

    void but2() {
        shapes.but2();
        sendUpdate();
    }
}
