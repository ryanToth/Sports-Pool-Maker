/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sports.pool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryan
 */
public class Pool {
    
    int numberOfTeams;
    String nameOfPool;
    int numberOfParticipants;
    int numberOfRounds = 4;
    String[] teams;
    LinkedList<Participant> participants = new LinkedList<>();
    Participant master;
    
    public Pool(String nameOfPool, int numberOfParticipants, int numberOfTeams, int numberOfRounds, String[] teams, LinkedList<Participant> participants, Participant master) {
        
        this.numberOfParticipants = numberOfParticipants;
        this.nameOfPool = nameOfPool;
        this.numberOfRounds = numberOfRounds;
        this.numberOfTeams = numberOfTeams;
        this.teams = teams;
        this.participants =  participants;
        this.master = master;
    }
    
    public Pool() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        
        nameOfPool = JOptionPane.showInputDialog(null, "What's the Name of the Pool?", "New Pool",1);
        
        boolean cancel = false;
        
        PrintWriter writer = new PrintWriter(new File("files/" + nameOfPool + ".txt"));
        BufferedWriter poolList = new BufferedWriter(new FileWriter("files/list_of_pools.txt", true));
        
        poolList.append("\n" + nameOfPool);
        poolList.close();
        
        writer.println(nameOfPool);
        
        boolean valid = true;
        String tmp;
        
        while (valid && !cancel) {
            
            tmp = JOptionPane.showInputDialog(null, "How Many People are Participating?", "New Pool",1);

            try {
                numberOfParticipants = Integer.parseInt(tmp);
                valid = false;
                writer.println(numberOfParticipants);
            }
            
            catch(Exception e) {}
            
            if (tmp == null) cancel = true;
        }
        
        valid = true;
        
        while (valid && !cancel) {
            
            tmp = JOptionPane.showInputDialog(null, "How Many Teams are in the Tournement?", "New Pool",1);
            
            try {
                numberOfTeams = Integer.parseInt(tmp) + 1;
                valid = false;
                teams = new String[numberOfTeams];
                writer.println(numberOfTeams);
            }
            catch(Exception e) {
                if (tmp == null) cancel = true;
            }
        }
        
        valid = true;
        
        //Will add this feature later
        /*
        while (valid && !cancel) {
            
            tmp = JOptionPane.showInputDialog(null, "How Many Rounds are in the Tournement?", "New Pool",1);
            
            try {
                numberOfRounds = Integer.parseInt(tmp);
                valid = false;
                writer.println(numberOfRounds);
            }
            catch(Exception e) {
                if (tmp == null) cancel = true;
            }
        }
        */
        writer.println(4);
        
        for (int i = 1; i < numberOfTeams && !cancel; i++) {
            teams[0] = "";
            teams[i] = JOptionPane.showInputDialog(null, "Enter Team #" + (i), "New Pool",1);
            if (teams[i] == null) {
                cancel = true;
                break;
            }
            writer.println(teams[i]);
        }
        
        
        for (int i = 0; i < numberOfParticipants; i++) {
            
            Participant temp = new Participant(teams, nameOfPool, false);
            participants.add(temp);
            writer.println(temp.name);
        }
        
        //Make the master bracket that all others are compared to
        PrintWriter masterWriter = new PrintWriter(new File("files/" + nameOfPool + "_Master.txt"));
        
        for (int i = 0; i < 31; i++)
            masterWriter.println("");
        masterWriter.println("0");
        
        masterWriter.close();
        //---------------------------------------------------------
        
        Collections.sort(participants);
        writer.close();
    }
}
