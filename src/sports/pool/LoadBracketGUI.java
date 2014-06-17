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
public class LoadBracketGUI extends JFrame {
    
    JComboBox poolOptions;
    JButton done = new JButton("Done");
    LoadBracketGUI sender = this;
    
    public LoadBracketGUI(LinkedList<Participant> participants) throws UnsupportedEncodingException, IOException {
        
        setSize(300,200);
        setTitle("Select a Participant");
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(2,1));
        
        LinkedList<String> participantNames = new LinkedList<>();
        
        participantNames.add("");
        
        for (int i = 0; i < participants.size(); i++)
            participantNames.add(participants.get(i).name);
        
        poolOptions = new JComboBox(participantNames.toArray());
        
        initButtons();
        
        add(poolOptions);
        add(done);
    }

    private final void initButtons() {

        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                
                if (!((String) poolOptions.getSelectedItem()).equals("")) {

                }
            }
        });
    }
}
