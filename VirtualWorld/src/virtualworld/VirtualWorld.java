/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.awt.GraphicsConfiguration;
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
                JFrame frame = new JFrame("Wirtualny świat");
                frame.setSize(200, 200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel headerLabel = new JLabel("Piotr Wontka - ETI Semestr III",JLabel.CENTER );
                frame.getContentPane().add(headerLabel);

                frame.setVisible(true);
             }
         });
    }
    
}
