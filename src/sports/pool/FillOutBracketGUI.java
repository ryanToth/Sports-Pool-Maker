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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Ryan
 */
public class FillOutBracketGUI extends JFrame {
    
    JComboBox[] teamOptions;
    JComboBox thirdPlaceOptions;
    String thirdPlacePick;
    JButton done = new JButton("Done");
    String[] roundPicks = new String[31];
    Participant participant;
    boolean master;
    JFrame sender;
    
    public FillOutBracketGUI(String[] teams, Participant participant, boolean master, JFrame sender) throws UnsupportedEncodingException, IOException {
        
        setSize(800,500);
        
        if (!master) 
            setTitle(participant.poolName + ": " + participant.name + "'s Bracket");
        else setTitle(participant.poolName + ": " + participant.name + " Bracket");
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(18,5));
        
        this.participant = participant;
        this.sender = sender;
        this.master = master;
        
        teamOptions = new JComboBox[31];
        
        int tableSize = 80;
        
        LinkedList<String> sortedTeams = new LinkedList<>();
        
        for (int i = 0; i < teams.length; i++)
            sortedTeams.add(teams[i]);
        
        Collections.sort(sortedTeams);
        
        for (int i = 0; i < teams.length; i++)
            teams[i] = sortedTeams.get(i);
        
        for (int j = 0; j < teamOptions.length; j++)
            teamOptions[j] = new JComboBox(teams);
        
        thirdPlaceOptions = new JComboBox(teams);
        
        int i = 0;
        int j = 0;
        
        add(new JLabel("         " + "    Round of 16"));
        add(new JLabel("         " + "   Quarter Finals"));
        add(new JLabel("         " + "    Semi Finals"));
        add(new JLabel("         " + "      Finals"));
        add(new JLabel("         " + "      Winner"));
        
        while (i < tableSize) {
            
            if (bracketLayout()[i] == 1) {
                add(teamOptions[j]);
                j++;
            }
            else if (bracketLayout()[i] == 2) {
                JLabel tmp = new JLabel("Third Place");
                tmp.setHorizontalAlignment(JLabel.CENTER);
                add(tmp);
            }
            else if (bracketLayout()[i] == 3)
                add(thirdPlaceOptions);
            else add(new JLabel());
            
            i++;
        }
        
        
        if (master) {

            FileInputStream input = new FileInputStream("files/" + participant.poolName + "_Master.txt");
            BufferedReader configReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));

            for (int k = 0; k < 31; k++) {
                
                roundPicks[k] = configReader.readLine();
                
                for (int l = 0; l < teams.length; l++) {
                    if (roundPicks[k].equals(teams[l])) {
                        teamOptions[k].setSelectedItem(roundPicks[k]);
                    } 
                }
            }
            
            thirdPlacePick = configReader.readLine();
            if (!thirdPlacePick.equals(""))
                thirdPlaceOptions.setSelectedItem(thirdPlacePick);
        }

        initButton();

        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(done);
        
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
                                 1,0,0,0,2,
                                 1,1,1,0,3,
                                 1,1,0,0,0,
                                 1,0,0,0,0};
        
            return layout;
        }
        
        private final void initButton() {
            
            done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                
                PrintWriter writer = null;
                        
                try {
                    writer = new PrintWriter(new File("files/" + participant.poolName + "_" + participant.name + ".txt"));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FillOutBracketGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for (int i = 0; i < 31; i++) {
                    
                    roundPicks[i] = (String)teamOptions[i].getSelectedItem(); 
                    if (roundPicks[i].equals("")) writer.println("");
                    else writer.println(roundPicks[i]);
                }
                thirdPlacePick = (String)thirdPlaceOptions.getSelectedItem();
                writer.println(thirdPlacePick);
                writer.println("0");
                participant.roundPicks = roundPicks;
                writer.close();
                
                if (master) try {
                    new PoolGUI(participant.poolName);
                    sender.dispose();
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(FillOutBracketGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FillOutBracketGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Line below cause the IDE to break if commented out for some reason ------------------------------------------
                //if (!master)
                    participant.closeBracketWindow();
            }
        });
            
        }
}
