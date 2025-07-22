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
public class NPC_Blatoise extends Entity{
    
    public NPC_Blatoise(GamePanel gp) {
        super(gp);
        name = "Blastoise"; 
        
        direction = "down"; 
        speed = 1; 
        
        getPlayerImage(); 
    }
    
    
    public void getPlayerImage()
    {
       
        f1 = setUp("/Generacion1/blastoise");
        f2 = setUp("/Generacion1/blastoise");
        r1 = setUp("/Generacion1/blastoise");
        r2 = setUp("/Generacion1/blastoise");
        l1 = setUp("/Generacion1/blastoise");
        l2 = setUp("/Generacion1/blastoise");
        b1 = setUp("/Generacion1/blastoise");
        b2 = setUp("/Generacion1/blastoise");
       
    }
    
}
