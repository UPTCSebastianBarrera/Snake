/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import com.sun.java.swing.plaf.windows.WindowsLabelUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Delayed;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author JoanS
 */
public class SnakePanel extends JPanel implements KeyListener ,ActionListener{

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension scSize = tk.getScreenSize();
        Random random = new Random();
        ScoreboardManager sc = new ScoreboardManager();
        
        int height = (int) scSize.width/2;
        int width = (int) scSize.width/2;
        int gridSize= 20;
        int unitsize=(int) width/gridSize;
        int applex;
        int appley;
        int bodypart=3;
        int motionx[] = new int[(width*height)/unitsize];
        int motiony[] = new int[(width*height)/unitsize];
        boolean up=false,down=false,left=false,right=true;
        boolean move= false;
        Timer timer;
        int Delayed = 100;
        JLabel loseLabel = new JLabel();
        JLabel score = new JLabel();
        JLabel winLabel = new JLabel();
        
        int scorepoints =0;
    
        
        
    public SnakePanel(){
        
        loseLabel.setBounds(width/3,height/3,width/3,height/3);
        loseLabel.setOpaque(false);
        loseLabel.setFont(new Font("Arial Black",Font.BOLD,(int)width/25 ));
        loseLabel.setText("  YOU LOSE");
        loseLabel.setForeground(Color.white);
        loseLabel.setVisible(false);
        
        
        score.setText("Puntuación: "+String.valueOf(scorepoints));
        score.setOpaque(false);
        score.setFont(new Font("Arial Black", Font.BOLD,(int) width/35));
        score.setBounds(0,0,width/3,width/20);
        score.setVisible(true);
        score.setForeground(Color.white);
        
        winLabel.setBounds(width/3,height/3,width/3,height/3);
        winLabel.setOpaque(false);
        winLabel.setFont(new Font("Arial Black",Font.BOLD,(int)width/25 ));
        winLabel.setText("  YOU WIN");
        winLabel.setForeground(Color.white);
        winLabel.setVisible(false);
        
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.addKeyListener(this);
        this.add(loseLabel);
        this.add(score);
        this.add(winLabel);
        
        start();
        
        
    }
    public void start(){
        randomizer();
        move = true;
        timer = new Timer (Delayed,this);
        timer.start();
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        
        
    }
   
    public void draw(Graphics g ){
        
        
        
        g.setColor(new Color(255,102,102));
        g.fillOval(applex, appley, unitsize, unitsize);
        for(int i = 0; i<bodypart;i++){
            if(i == 0){
                g.setColor(new Color(255,128,0));
                g.fillRect(motionx[i], motiony[i], unitsize, unitsize);
            }
            else{
                g.setColor(new Color(255,204,153));
                g.fillRect(motionx[i], motiony[i], unitsize, unitsize);
                
            }
        }
    }
    

    
    public void randomizer(){
        
        applex = (int) ((random.nextInt(width/unitsize))*unitsize);
        appley = (int) ((random.nextInt(height/unitsize))*unitsize);
        if(applex== motionx[0]&& appley == motiony[0]){
            randomizer();
        }
    }
   
    public void score(){
        score.setText("Puntuación: "+String.valueOf(scorepoints));
    }
    public void motion(){
        for(int i=bodypart;i>0;i--){
            motionx[i]=motionx[i-1];
            motiony[i]=motiony[i-1];
        }
        
        if(up == true){
            motiony[0]=motiony[0] - unitsize;//up;
        }
        if(down == true){
            motiony[0]=motiony[0] + unitsize;
        }
        if(left == true){
            motionx[0]=motionx[0] - unitsize;
        }
        if(right == true){
            motionx[0]=motionx[0] + unitsize;
            
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {

        if(move){
            motion();
            if(motiony[0]==appley && motionx[0]==applex){
                randomizer();
                bodypart++;
                scorepoints=scorepoints+10;
                score();
            }
            for(int i=1; i<bodypart;i++){
                if(motiony[i]==appley && motionx[i]==applex){
                    randomizer();   
                }
                if(motiony[0]==motiony[i] && motionx[0]==motionx[i]){
                    lost();
                }

            }
            
            
            if(motiony[0]>=unitsize*gridSize || motiony[0]<0){
             lost();
            }
            if(motionx[0]>=unitsize*gridSize || motionx[0]<0){
             lost();
            }
            
            if(bodypart >=gridSize*gridSize){
                win();
                
            }
        }
        
        repaint();
        

        
    }
    
    public void lost(){
        move=false;
        loseLabel.setVisible(true);
        
            try {
                sc.printScore("player "+String.valueOf(scorepoints));
            } catch (IOException ex) {
                System.out.println("score error");
            }
        
    }
    
    public void win(){
        move=false;
        winLabel.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    
        
    }

   

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
            switch(ke.getKeyCode()){
                case 37:
                if(right==false){
                  left=true;
                  right=false;
                  up=false;
                  down=false;  
                }
                //IZquierda 37
                break;
                
                case 38:
                if(down==false){
                    left=false;
                    right=false;
                    up=true;
                    down=false; 
                }
                //Arriba 38
                break;
                
                case 39:
                if(left==false){
                    left=false;
                    right=true;
                    up=false;
                    down=false; 
                }//Derecha 39
               
                break;
                
                
                case 40 :
                if(up==false){
                    left=false;
                    right=false;
                    up=false;
                    down=true; 
                }
                //Abajo 40
                break;
              
            }
            
    }
    
    
    
    
}
