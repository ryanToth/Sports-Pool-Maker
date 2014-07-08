/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sports.pool;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Ryan
 */
public class Icon {

    public static ImageIcon Icon() {

        URL url =  Icon.class.getClassLoader().getResource("sports/pool/icon.png");
        ImageIcon image = new ImageIcon(url);

        Image tmp = image.getImage();
        Image resizedTmp = tmp.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        image = new ImageIcon(resizedTmp);
        
        return image;
    }
    
}
