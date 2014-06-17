/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sports.pool;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Ryan
 */
public class ViewBracketGUI extends JFrame {
    
    JLabel[] teamOptions;
    String[] roundPicks = new String[31];
    Participant participant;
    
    public ViewBracketGUI(Participant participant) {
        
        setSize(850,500);
        setTitle(participant.poolName + ": " + participant.name + "'s Bracket");
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(18,5));
        
        this.participant = participant;
        
        teamOptions = new JLabel[31];
        
        int tableSize = 80;
        
        for (int j = 0; j < teamOptions.length; j++) {
            teamOptions[j] = new JLabel(participant.roundPicks[j]);
            teamOptions[j].setHorizontalAlignment(JLabel.CENTER);
            teamOptions[j].setOpaque(true);
            teamOptions[j].setBorder(BorderFactory.createLineBorder(Color.black));
            
            if (participant.correctPicks[j].equals("green"))
                teamOptions[j].setBackground(Color.green);
            else if (participant.correctPicks[j].equals("yellow"))
                teamOptions[j].setBackground(Color.yellow);
            else if (participant.correctPicks[j].equals("red"))
                teamOptions[j].setBackground(Color.red);
            else
                teamOptions[j].setBackground(Color.white);
        }
        
        int i = 0;
        int j = 0;
        
        add(new JLabel("Round of 16", JLabel.CENTER));
        add(new JLabel("Quarter Finals", JLabel.CENTER));
        add(new JLabel("Semi Finals", JLabel.CENTER));
        add(new JLabel("Finals", JLabel.CENTER));
        add(new JLabel("Winner", JLabel.CENTER));
        
        while (i < tableSize) {
            
            if (bracketLayout()[i] == 1) {
                add(teamOptions[j]);
                j++;
            }
            else add(new JLabel());
            
            i++;
        }
        
        add(new JLabel());
        
        JLabel colorExplanation1 = new JLabel("Correct Placement");
        colorExplanation1.setOpaque(true);
        colorExplanation1.setBackground(Color.green);
        colorExplanation1.setHorizontalAlignment(JLabel.CENTER);
        colorExplanation1.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JLabel colorExplanation2 = new JLabel("Incorrect Placement");
        colorExplanation2.setOpaque(true);
        colorExplanation2.setBackground(Color.yellow);
        colorExplanation2.setHorizontalAlignment(JLabel.CENTER);
        colorExplanation2.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JLabel colorExplanation3 = new JLabel("Incorrect Pick");
        colorExplanation3.setOpaque(true);
        colorExplanation3.setBackground(Color.red);
        colorExplanation3.setHorizontalAlignment(JLabel.CENTER);
        colorExplanation3.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JLabel colorExplanation4 = new JLabel("Awaiting Results");
        colorExplanation4.setOpaque(true);
        colorExplanation4.setBackground(Color.white);
        colorExplanation4.setHorizontalAlignment(JLabel.CENTER);
        colorExplanation4.setBorder(BorderFactory.createLineBorder(Color.black));
        
        add(colorExplanation4);
        add(colorExplanation1);
        add(colorExplanation2);
        add(colorExplanation3);
        
        validate();
    }
    
    private final int[] bracketLayout() {
        
        int[] layout = new int[]{1,0,0,0,0,
                                 1,1,0,0,0,
                                 1,1,1,0,0,
                                 1,0,0,0,0,
                                 1,0,0,0,0,
                                 1,1,1,1,0,
                                 1,1,0,0,0,
                                 1,0,0,0,0,
                                 1,0,0,0,1,
                                 1,1,0,0,0,
                                 1,1,1,1,0,
                                 1,0,0,0,0,
                                 1,0,0,0,0,
                                 1,1,1,0,0,
                                 1,1,0,0,0,
                                 1,0,0,0,0};
        
            return layout;
        }
}
