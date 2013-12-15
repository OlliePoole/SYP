package framework;


import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p0073862
 */
public class GUIUtils {
    
    public static AffineTransform getReflection(double xc, double yc, double theta) {
        AffineTransform result = getHorizontalFlip(xc);
        result.concatenate(AffineTransform.getRotateInstance(2*theta, xc, yc));
        return result;
    }
    
    public static AffineTransform getHorizontalFlip(double c) {
        AffineTransform result = AffineTransform.getScaleInstance(-1, 1);
        result.concatenate(AffineTransform.getTranslateInstance(-2*c, 0));
        
        return result;
    }
    
}
