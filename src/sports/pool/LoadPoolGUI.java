/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sports.pool;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author Ryan
 */
public class LoadPoolGUI extends JFrame {
    
    JComboBox poolOptions;
    JButton done = new JButton("Done");
    LoadPoolGUI sender = this;
    
    public LoadPoolGUI() throws UnsupportedEncodingException, IOException {
        
        setSize(300,200);
        setTitle("Select a Pool");
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(2,1));
        
        FileInputStream input = new FileInputStream("files/list_of_pools.txt");
        BufferedReader configReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
        
        LinkedList<String> poolNames = new LinkedList<>();
        
        while(configReader.ready()) {
            
            String tmp = configReader.readLine();
            poolNames.add(tmp);
        }
        
        poolOptions = new JComboBox(poolNames.toArray());
        
        initButtons();
        
        add(poolOptions);
        add(done);
    }

    private final void initButtons() {

        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                
                if (!((String) poolOptions.getSelectedItem()).equals("")) {
                    try {
                        new PoolGUI((String)poolOptions.getSelectedItem(), sender);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(LoadPoolGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(LoadPoolGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}
