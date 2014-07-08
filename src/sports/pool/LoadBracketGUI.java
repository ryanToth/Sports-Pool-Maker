/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sports.pool;

import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryan
 */
public class LoadBracketGUI {
    
    public LoadBracketGUI(LinkedList<Participant> participants) {
        
        LinkedList<String> participantNames = new LinkedList<>();
        
        participantNames.add("");
        
        for (int i = 0; i < participants.size(); i++)
            participantNames.add(participants.get(i).name);
        
        Collections.sort(participantNames);
        
        String name = (String)JOptionPane.showInputDialog(null, "Select a Participant", "Check Bracket",
                        JOptionPane.INFORMATION_MESSAGE,
                        Icon.Icon(), participantNames.toArray(), participantNames.toArray()[0]);
        
        if (name != null && !name.equals("")) {
            for (int i = 0; i < participants.size(); i++) {
                if (name.equals(participants.get(i).name))
                    new ViewBracketGUI(participants.get(i));
            }
        }
    }   
}
