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
public class NPC_Charizard extends Entity{
    
    public NPC_Charizard(GamePanel gp) {
        super(gp);
        name = "Charizard"; 
        
        direction = "down"; 
        speed = 1; 
        
        getPlayerImage(); 
    }
    
    
    public void getPlayerImage()
    {
       
        f1 = setUp("/Generacion1/charizard");
        f2 = setUp("/Generacion1/charizard");
        r1 = setUp("/Generacion1/charizard");
        r2 = setUp("/Generacion1/charizard");
        l1 = setUp("/Generacion1/charizard");
        l2 = setUp("/Generacion1/charizard");
        b1 = setUp("/Generacion1/charizard");
        b2 = setUp("/Generacion1/charizard");
       
    }
    
}
