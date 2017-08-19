/**
 * Slider and Car Icon
 * 5.4 - SlideTester
 * Project 3
 * @author Paul Diaz
 * CS 151 
 * Prof. Kim
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SlideTester {
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Paul Diaz");
        frame.setSize(500, 500);
        
        final CarShape shape = new CarShape(0, 0, CAR_WIDTH);
        ShapeIcon icon = new ShapeIcon(shape, ICON_WIDTH, ICON_HEIGHT);
        
        JSlider jSlider = new JSlider(50, 300);
        jSlider.setMinorTickSpacing(10);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
       
        final JLabel label = new JLabel(icon);
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.CENTER);
        frame.add(jSlider, BorderLayout.SOUTH);
        
        jSlider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                shape.setWidth(source.getValue());
                label.repaint();
            }
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private static final int ICON_WIDTH = 400;
    private static final int ICON_HEIGHT = 100;
    private static final int CAR_WIDTH = 100;
}
