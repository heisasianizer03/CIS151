/**
 * Slider and Car Icon
 * 5.4 - ShapeIcon borrowed from Hostmann Chapter 4
 * Project 3
 * @author Paul Diaz
 * CS 151 
 * Prof. Kim
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * An icon that contains a CarShape shape.
 */
public class ShapeIcon implements Icon{
    public ShapeIcon(CarShape shape, int width, int height){
        this.shape = shape;
        this.width = width;
        this.height = height;
    }
    
    /** Method that takes the width of our icon
     * @return the width of the icon
     */
    public int getIconWidth(){
        return width;
    }
    
     /** Method that takes the height of our icon
     * @return the height of the icon
     */
    public int getIconHeight(){
        return height;
    }
    
    /**
     * Method that paints the Icon
     * @param c is the component to be drawn
     * @param g is the graphics to be drawn
     * @param x is the s coordinate
     * @param y is the y coordinate
     */
    public void paintIcon(Component c, Graphics g, int x, int y){
        Graphics2D g2 = (Graphics2D) g;
        shape.draw(g2);
    }
    
    private int width;
    private int height;
    private CarShape shape;
    
}
