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
public class NPC_Pokemon extends Entity{
    
    public NPC_Pokemon(GamePanel gp, int n) {
        super(gp);
        
        if(n != 0){
            name = String.valueOf(n); 
        }
        
        direction = "down"; 
        speed = 1; 
        
        getPlayerImage(); 
    }
    
    
    public void getPlayerImage()
    {
       
        f1 = setUp("/Generacion1/"+name);
        f2 = setUp("/Generacion1/"+name);
        r1 = setUp("/Generacion1/"+name);
        r2 = setUp("/Generacion1/"+name);
        l1 = setUp("/Generacion1/"+name);
        l2 = setUp("/Generacion1/"+name);
        b1 = setUp("/Generacion1/"+name);
        b2 = setUp("/Generacion1/"+name);
       
    }
    
}
