/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import com.mycompany.primerjuego2d.main.GamePanel;


/**
 *
 * @author pablo
 */
public class NPC_CocheJuan extends Entity{
    
    public NPC_CocheJuan(GamePanel gp) {
        super(gp);
        
        direction = "down"; 
        speed = 5; 
        
        getPlayerImage(); 
    }
    
    public void getPlayerImage()
    {
       
        f1 = setUp("/NPC/CocheFront.png");
        f2 = setUp("/NPC/CocheFront.png");
        r1 = setUp("/NPC/CocheR1.png");
        r2 = setUp("/NPC/CocheR2.png");
        l1 = setUp("/NPC/CocheL1.png");
        l2 = setUp("/NPC/CocheL2.png");
        b1 = setUp("/NPC/CocheB.png");
        b2 = setUp("/NPC/CocheB.png");
       
    }
    
    
    
    
}
