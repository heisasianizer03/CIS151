/**
 * MVCTester
 * CS 151
 * Paul Diaz
 * Prof. Kim
 * 
 * README - JFrame and JTextField serve as the VIEW,
 *          JButton and JTextArea serve as the CONTROLLER, and
 *          Line class(which has Listeners) serves as the model
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MVCTester {
     public static void main(String[] args){
        //JFrame serves as the VIEW
        JFrame frame = new JFrame("Paul Diaz");
        frame.setSize(200, 400);
        //JTextField also serves as the VIEW
        final JTextField txtField = new JTextField();
        
        final Line lines = new Line();
       
        //JButton serves as the Controller
        JButton button = new JButton("Add");
        button.addActionListener(new 
            ActionListener(){
                public void actionPerformed(ActionEvent event){
                   lines.addText(txtField.getText());
                }
        });
        
        //JTextArea also serves as the Constroller
        final JTextArea text = new JTextArea(15, 20);
        ChangeListener listener = new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e){
	        text.setText(lines.getLine());
	    }

	};
        lines.attach(listener);
        
        //JFrame serves as the VIEW
        frame.add(txtField, BorderLayout.SOUTH);
        frame.add(text, BorderLayout.CENTER);
        frame.add(button, BorderLayout.NORTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
