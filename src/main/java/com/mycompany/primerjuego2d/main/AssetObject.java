/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Objects.OBJ_Hacha;
import Objects.OBJ_Pokeball;

/**
 *
 * @author pablo
 */
public class AssetObject {
    
    GamePanel gp; 
    
    public AssetObject(GamePanel gp){
        this.gp = gp; 
    }
    
    public void setObject(){
        
        gp.object[0] = new OBJ_Hacha(); 
        gp.object[1] = new OBJ_Pokeball(); 
        
        
        
    }
    
}
