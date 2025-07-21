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
public class NPC_Bulbasur extends Entity{
    
    public NPC_Bulbasur(GamePanel gp) {
        super(gp);
        name = "Bulbasur"; 
        
        direction = "down"; 
        speed = 1; 
        
        getPlayerImage(); 
    }
    
    
    public void getPlayerImage()
    {
       
        f1 = setUp("/Generacion1/bulbasaur");
        f2 = setUp("/Generacion1/bulbasaur");
        r1 = setUp("/Generacion1/bulbasaur");
        r2 = setUp("/Generacion1/bulbasaur");
        l1 = setUp("/Generacion1/bulbasaur");
        l2 = setUp("/Generacion1/bulbasaur");
        b1 = setUp("/Generacion1/bulbasaur");
        b2 = setUp("/Generacion1/bulbasaur");
       
    }
    
}
