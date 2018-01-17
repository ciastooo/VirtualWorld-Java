/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*; 
import javax.swing.text.DefaultCaret;
import static javax.swing.text.DefaultCaret.ALWAYS_UPDATE;

/**
 *
 * @author Piotrek
 */
public class VirtualWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean okX = false, okY = false;
        int x = 40, y = 40;
        do {
            try {
                String txt;
                if(!okX) {
                    txt = JOptionPane.showInputDialog("Podaj szerokość świata (2-40)");
                    x = Integer.parseInt(txt);
                    okX = x >= 2 && x<= 40;
                }
                if(okX && !okY) {
                    txt = JOptionPane.showInputDialog("Podaj wysokość świata (2-40)");
                    y = Integer.parseInt(txt);
                    okY = y >= 2 && y <= 40;
                }
            } catch(IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Podaj poprawną liczbę (2-40)");
            }
        } while(!okX || !okY);
        
        World world = new World(x, y);
        JPanel worldPanel = new WorldPanel(world);
        worldPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                world.addAtPixelPosition((me.getX()/10)+1, (me.getY()/10)+1);
                worldPanel.repaint();
            }
            @Override
            public void mousePressed(MouseEvent me) {}
            @Override
            public void mouseReleased(MouseEvent me) {}
            @Override
            public void mouseEntered(MouseEvent me) {}
            @Override
            public void mouseExited(MouseEvent me) {}
        });
        JPanel buttons = new JPanel();        
        
        JButton tickBtn = new JButton("Następna tura");
        tickBtn.setVerticalTextPosition(AbstractButton.CENTER);
        tickBtn.setHorizontalAlignment(AbstractButton.LEADING);
        tickBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                world.tick();
                worldPanel.repaint();
            }
        });

        JButton saveBtn = new JButton("Zapisz");
        saveBtn.setVerticalTextPosition(AbstractButton.CENTER);
        saveBtn.setHorizontalAlignment(AbstractButton.LEADING);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                world.exportToFile();
            }
        });

        JButton loadBtn = new JButton("Wczytaj");
        loadBtn.setVerticalTextPosition(AbstractButton.CENTER);
        loadBtn.setHorizontalAlignment(AbstractButton.LEADING);
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                world.importFromFile();
                worldPanel.repaint();
            }
        });
        
        buttons.add(tickBtn);
        buttons.add(saveBtn);
        buttons.add(loadBtn);
        
        JTextArea console = new JTextArea();
        DefaultCaret caret = (DefaultCaret)console.getCaret();
        caret.setUpdatePolicy(ALWAYS_UPDATE);
        console.setEditable(false);
        //console.setLineWrap(true);
        JScrollPane scrollWrapper = new JScrollPane(console);  
        scrollWrapper.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollWrapper.setPreferredSize(new Dimension(400, 200));
        JPanel consoleWrapper = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        consoleWrapper.add(scrollWrapper, gbc);
        gbc.weighty = 0.1;
        gbc.gridy = 1;
        consoleWrapper.add(buttons, gbc);
        
        world.setConsole(console);
        world.consoleLogLn("Kliknij na dowolne pole aby dodać organizm");

        JFrame frame = new JFrame("Piotr Wontka 167951- wirtualny świat");
        frame.setSize(420, 680);
        frame.add(worldPanel);
        frame.add(consoleWrapper, BorderLayout.PAGE_END);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }   
}
