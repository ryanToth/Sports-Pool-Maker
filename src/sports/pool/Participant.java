/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sports.pool;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryan
 */
public class Participant implements Comparable {
    
    int score = 0;
    String name;
    String[] roundPicks;
    String thirdPlacePick;
    FillOutBracketGUI bracket;
    String poolName;
    String[] correctPicks = new String[31];
    String thirdPlaceCorrectPick;
    boolean done = false;
    
    public Participant(String[] roundPicks, String poolName, String name, int score, String thirdPlacePick) {
        
        this.roundPicks = roundPicks;
        this.poolName = poolName;
        this.name = name;
        this.score = score;
        this.thirdPlacePick = thirdPlacePick;
        
        for (int i = 0; i < correctPicks.length; i++)
            correctPicks[i] = "";
    }
   
    public Participant(String[] teams, String poolName, boolean master, JFrame sender) throws IOException {

        if (!master) {
            name = JOptionPane.showInputDialog(null, "Enter Participant's Name", "New Participant", 1);

            while (name.equals("Master")) {
                name = JOptionPane.showInputDialog(null, "Enter Participant's Name (Master is not a valid name)", "New Participant", 1);
            }
            
            for (int i = 0; i < correctPicks.length; i++)
                correctPicks[i] = "";
        }
        
        else this.name = "Master";
        
        this.poolName = poolName;

        bracket = new FillOutBracketGUI(teams, this, master, sender);
    }

    @Override
    public int compareTo(Object o) {
        
        Participant other = (Participant) o;
        if (this.score == other.score) return 0;
        else if (this.score > other.score) return -1;
        else return 1;
    }
    
    @Override
    public String toString() {
        
        String spaces = " ";
        
        for (int i = 0; i < 12 - name.length(); i++)
            spaces += "-";
        
        spaces += " ";
        
        return name + spaces + score;
    }
    
    public void closeBracketWindow() {

        bracket.dispose();
        done = true;
    }
}
