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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryan
 */
public class PoolGUI extends JFrame {
    
    Pool pool;
    LoadPoolGUI sender;
    JButton checkBrackets = new JButton("Check Brackets");
    JButton updateMaster = new JButton("Update Master Bracket");
    JButton checkStandings = new JButton("Check Standings");
    
    public PoolGUI(String poolName, LoadPoolGUI sender) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        
        this.sender = sender;
        
        FileInputStream input = new FileInputStream("files/" + poolName + ".txt");
        BufferedReader configReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
        
        String nameOfPool = configReader.readLine();
        int numOfParticipants = Integer.parseInt(configReader.readLine());
        int numOfTeams = Integer.parseInt(configReader.readLine());
        int numOfRounds = Integer.parseInt(configReader.readLine());
        String[] teams = new String[numOfTeams];
        
        teams[0] = "";
        
        for (int i = 1; i < teams.length; i++)
            teams[i] = configReader.readLine();
        
        String[] participantNames = new String[numOfParticipants];
        
        for (int i = 0; i < participantNames.length; i++)
            participantNames[i] = configReader.readLine();
        
        LinkedList<Participant> participants = new LinkedList<>();
        
        for (int i = 0; i < participantNames.length; i++) {

            input = new FileInputStream("files/" + poolName + "_" + participantNames[i] + ".txt");
            configReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            String[] roundPicks = new String[31];

            for (int j = 0; j < 31; j++) {
                roundPicks[j] = configReader.readLine();
            }
            
            participants.add(new Participant(roundPicks, poolName, participantNames[i], Integer.parseInt(configReader.readLine())));
        }

        input = new FileInputStream("files/" + poolName + "_Master.txt");
        configReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
        String[] roundPicks = new String[31];

        for (int j = 0; j < 31; j++) {
            roundPicks[j] = configReader.readLine();
        }

        pool = new Pool(nameOfPool, numOfParticipants, numOfTeams, numOfRounds, teams, participants, new Participant(roundPicks, poolName, "Master", 0));
        initFrame();
    }

    private final void initFrame() {

        setSize(500, 200);
        setTitle(pool.nameOfPool);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridLayout(1,3));
        
        initButtons();
        
        add(checkBrackets);
        add(updateMaster);
        add(checkStandings);
        sender.dispose();
    }
    
    private final void initButtons() {
        
        checkBrackets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                
                try {
                    new LoadBracketGUI(pool.participants);
                } catch (IOException ex) {
                    Logger.getLogger(PoolGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        updateMaster.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {

                try {
                    pool.master = new Participant(pool.teams, pool.nameOfPool, true);
                    
                } catch (IOException ex) {
                    Logger.getLogger(PoolGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
        });
        
        checkStandings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                
                calculateScores();
                
                Collections.sort(pool.participants);
                
                String standings = "";
                
                for (int i = 0; i < pool.numberOfParticipants; i++) 
                    standings += "#" + (i+1) + ". " + pool.participants.get(i) + "\n";
                
                Object[] options = {"Return"};
                
                JOptionPane.showOptionDialog(null, standings, pool.nameOfPool + " Standings",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
            }
        });
    }
    
    private void calculateScores() {
        
        int[] layout = bracketLayout();
        int[] masterLayout = layout;

        for (int i = 0; i < pool.participants.size(); i++) {
            pool.participants.get(i).score = 0;
            for (int j = 0, l = 0; j < 31; l++) {
                for (int k = 0, m = 0 ; k < 31 && layout[l] != 0 && m < 80; m++) {

                    if (masterLayout[m] != 0) {
                        
                        if (layout[l] == 1 && masterLayout[m] == 1
                                && !pool.participants.get(i).roundPicks[j].equals("") && pool.participants.get(i).roundPicks[j].equals(pool.master.roundPicks[k])) {
                            pool.participants.get(i).score++;
                            if (j == k) pool.participants.get(i).score++;
                        } else if (layout[l] == 2 && masterLayout[m] == 2
                                && !pool.participants.get(i).roundPicks[j].equals("")
                                && pool.participants.get(i).roundPicks[j].equals(pool.master.roundPicks[k])) {
                            pool.participants.get(i).score += 3;
                        } else if (layout[l] == 3 && masterLayout[m] == 3
                                && !pool.participants.get(i).roundPicks[j].equals("")
                                && pool.participants.get(i).roundPicks[j].equals(pool.master.roundPicks[k])) {
                            pool.participants.get(i).score += 5;
                        } else if (layout[l] == 4 && masterLayout[m] == 4
                                && !pool.participants.get(i).roundPicks[j].equals("")
                                && pool.participants.get(i).roundPicks[j].equals(pool.master.roundPicks[k])) {
                            pool.participants.get(i).score += 6;
                        } else if (layout[l] == 5 && masterLayout[m] == 5
                                && !pool.participants.get(i).roundPicks[j].equals("")
                                && pool.participants.get(i).roundPicks[j].equals(pool.master.roundPicks[k])) {
                            pool.participants.get(i).score += 8;
                        }
                        k++;
                    }
                }
                if (layout[l] != 0) j++;
            }
        }
    }

    private final int[] bracketLayout() {
        
        int[] layout = new int[]{1,0,0,0,0,
                                 1,2,0,0,0,
                                 1,2,3,0,0,
                                 1,0,0,0,0,
                                 1,0,0,0,0,
                                 1,2,3,4,0,
                                 1,2,0,0,0,
                                 1,0,0,0,0,
                                 1,0,0,0,5,
                                 1,2,0,0,0,
                                 1,2,3,4,0,
                                 1,0,0,0,0,
                                 1,0,0,0,0,
                                 1,2,3,0,0,
                                 1,2,0,0,0,
                                 1,0,0,0,0};
        
            return layout;
        }
    
}
