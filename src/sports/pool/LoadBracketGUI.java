/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sports.pool;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
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
    LinkedList<Participant> participants;
    
    public LoadBracketGUI(LinkedList<Participant> participants) {
        
        setSize(300,200);
        setTitle("Select a Participant");
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(2,1));
        
        this.participants = participants;
        
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
                    new ViewBracketGUI(participants.get(poolOptions.getSelectedIndex()-1));
                }
            }
        });
    }
}
