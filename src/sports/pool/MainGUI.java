/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sports.pool;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Ryan
 */
public class MainGUI extends JFrame {
    
    JButton createNewPool = new JButton("Create New Pool");
    JButton loadPool = new JButton("Load Pool");
    
    public MainGUI() {
        
        setSize(400,200);
        setTitle("Sports Pool");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        initButtons();
        
        setLayout(new GridLayout(1,2));
        
        add(createNewPool);
        add(loadPool);
        validate();
    }
    
    public final void initButtons() {
        
        createNewPool.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                
                try {
                    new Pool();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        loadPool.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                try {
                    new LoadPoolGUI();
                } catch (IOException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
}
