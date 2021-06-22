/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author JoanS
 */
public class ScoreboardManager {
 
        FileWriter fw;
        FileReader fr;
        Scanner s;
  
    
    public void printScore(String st) throws IOException{
        fw = new FileWriter("src/scoreboard/Scoreboard.txt",true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(st);
        pw.close();
          
    }
    
    public String readScore() throws FileNotFoundException{
        s= new Scanner("src/scoreboard/Scoreboard.txt");
        
        return s.nextLine();
        
    }
    
}
