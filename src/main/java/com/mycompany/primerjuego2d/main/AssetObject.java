/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Objects.*;

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
        
        gp.object[1] = new OBJ_Hacha(); 
        gp.object[2] = new OBJ_Pokeball(); 
        gp.object[3] = new OBJ_Key(); 
        
        
        
    }
    
}
