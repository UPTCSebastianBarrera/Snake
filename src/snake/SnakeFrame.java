/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;
import java.awt.*;
import java.util.Random;
import javax.swing.*;
/**
 *
 * @author JoanS
 */
public class SnakeFrame extends JFrame {
    
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension scSize = tk.getScreenSize();
        Random random = new Random();
        Image icon  = tk.createImage("src/textures/icono.png");
        int height = scSize.height;
        int width = scSize.width;
       
        
            
        
    public SnakeFrame(){
       SnakePanel game = new SnakePanel();
       this.setTitle("SNAKE");
       this.setIconImage(icon);
       this.setLocation(width/4, height/24);
       this.setResizable(false);
       
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       game.setFocusable(true);
       game.addKeyListener(game);
       
       this.add(game);
       
       this.pack();
    }

    
    
    

}
