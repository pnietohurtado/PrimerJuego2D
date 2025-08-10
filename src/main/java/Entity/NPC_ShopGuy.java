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
public class NPC_ShopGuy extends Entity{
    
    public NPC_ShopGuy(GamePanel gp) {
        super(gp);
        name = "ShopGuy"; 
        
        direction = "down"; 
        speed = 1; 
        
        super.collision = true; 
        super.move = true;  // No se puede mover 
        getPlayerImage();
    }
    
    public void getPlayerImage(){
        f1 = setUp("/Azul/Azul");
        f2 = setUp("/Azul/Azul");
        r1 = setUp("/Azul/Azul");
        r2 = setUp("/Azul/Azul");
        l1 = setUp("/Azul/Azul");
        l2 = setUp("/Azul/Azul");
        b1 = setUp("/Azul/Azul");
        b2 = setUp("/Azul/Azul");
    }
    
    public void setAction(){
        
        actionLockCounter++; 
        
    }
    
}
