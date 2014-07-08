/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sports.pool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryan
 */
public class LoadPoolGUI {
    
    JFrame mainGUI;
    
    public LoadPoolGUI(JFrame frame) throws UnsupportedEncodingException, IOException {
        
        mainGUI = frame;
        
        FileInputStream input = new FileInputStream("files/list_of_pools.txt");
        BufferedReader configReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
        
        LinkedList<String> poolNames = new LinkedList<>();
        
        while(configReader.ready()) {
            
            String tmp = configReader.readLine();
            poolNames.add(tmp);
        }
        
        String name = (String)JOptionPane.showInputDialog(null, "Select a Pool", "Load Pool",
                        JOptionPane.INFORMATION_MESSAGE,
                        Icon.Icon(), poolNames.toArray(), poolNames.toArray()[0]);
        
        if (name != null && !name.equals("")) {
            new PoolGUI(name);
            mainGUI.dispose();
        }

    }
}
