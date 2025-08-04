/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import com.mycompany.primerjuego2d.main.GamePanel;
import java.util.Random;

/**
 *
 * @author pablo
 */
public class NPC_MewTwo extends Entity{
    
    
    
    public NPC_MewTwo(GamePanel gp) {
        super(gp);
        super.name = "MewTwo"; 
        
        direction = "down"; 
        speed = 1; 
        
        super.collision = true; 
        getPlayerImage();
    }
    
    public void getPlayerImage(){
        f1 = setUp("/Generacion1/150");
        f2 = setUp("/Generacion1/150");
        r1 = setUp("/Generacion1/150");
        r2 = setUp("/Generacion1/150");
        l1 = setUp("/Generacion1/150");
        l2 = setUp("/Generacion1/150");
        b1 = setUp("/Generacion1/150");
        b2 = setUp("/Generacion1/150");
    }
    
    public void setAction(){
        
        actionLockCounter++; 
        
        
        
        
    }
    
}
