/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import javax.swing.*; 

/**
 *
 * @author Piotrek
 */
public class VirtualWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                JFrame frame = new JFrame("HelloWorldSwing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //Add the ubiquitous "Hello World" label.
                JLabel label = new JLabel("Hello World");
                frame.getContentPane().add(label);

                //Display the window.
                frame.pack();
                frame.setVisible(true);
             }
         });
    }
    
}
