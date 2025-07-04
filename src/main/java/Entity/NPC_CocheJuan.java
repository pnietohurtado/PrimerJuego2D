/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.UtilityTool;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


/**
 *
 * @author pablo
 */
public class NPC_CocheJuan extends Entity{
   
    
    public NPC_CocheJuan(GamePanel gp) {
        super(gp);
        
        direction = "down"; 
        speed = 1; 
        
        getPlayerImage(); 
    }
    
   
    
    public void getPlayerImage()
    {
       
        f1 = setUp("/NPC/CocheFront");
        f2 = setUp("/NPC/CocheFront");
        r1 = setUp("/NPC/CocheR1");
        r2 = setUp("/NPC/CocheR2");
        l1 = setUp("/NPC/CocheL1");
        l2 = setUp("/NPC/CocheL2");
        b1 = setUp("/NPC/CocheB");
        b2 = setUp("/NPC/CocheB");
       
    }
    
    public void setAction(){
        
        actionLockCounter++; 
        
        if(actionLockCounter == 120){
            Random random = new Random(); 
            int i = random.nextInt(100) + 1; // Elige un número desde 1 a 100 

            if(i > 0 && i <= 50){
                direction = "left"; 
            }
            else if(i > 50 && i <= 100){
                direction = "right"; 
            }
            //System.out.println(i);

            actionLockCounter = 0; 
        }
        
        
        
    }
    
    
    
    
}
