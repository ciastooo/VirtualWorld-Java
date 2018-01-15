/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.awt.*;
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
                boolean okX = true, okY = true;
                int x = 2, y = 2;
                do {
                    try {
                        String txt;
                        if(!okX) {
                            txt = JOptionPane.showInputDialog("Podaj szerokość świata (2-70)");
                            x = Integer.parseInt(txt);
                            okX = x >= 2 && x<= 70;
                        }
                        if(okX && !okY) {
                            txt = JOptionPane.showInputDialog("Podaj wysokość świata (2-70)");
                            y = Integer.parseInt(txt);
                            okY = y >= 2 && y <= 70;
                        }
                    } catch(IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Podaj poprawną liczbę (2-70)");
                    }
                } while(!okX || !okY);
                World world = new World(x, y);
                
                JFrame frame = new JFrame("Piotr Wontka - wirtualny świat");
                frame.setSize(400, 600);
                
                JPanel worldCanvas = new JPanel();
                JPanel buttons = new JPanel();
                
                JButton tickBtn = new JButton("Następna tura");
                tickBtn.setVerticalTextPosition(AbstractButton.CENTER);
                tickBtn.setHorizontalAlignment(AbstractButton.LEADING);
                
                JButton saveBtn = new JButton("Zapisz");
                saveBtn.setVerticalTextPosition(AbstractButton.CENTER);
                saveBtn.setHorizontalAlignment(AbstractButton.LEADING);
                
                JButton loadBtn = new JButton("Wczytaj");
                loadBtn.setVerticalTextPosition(AbstractButton.CENTER);
                loadBtn.setHorizontalAlignment(AbstractButton.LEADING);
                
                buttons.add(tickBtn);
                buttons.add(saveBtn);
                buttons.add(loadBtn);
                
                frame.add(worldCanvas, BorderLayout.NORTH);
                frame.add(buttons, BorderLayout.SOUTH);
                frame.setVisible(true);
             }
         });
    }
    
}
