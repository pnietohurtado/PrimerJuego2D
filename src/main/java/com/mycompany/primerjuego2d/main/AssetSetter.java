/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Objects.OBJ_Key;
import Objects.OBJ_Pokeball;

/**
 *
 * @author pablo
 */
public class AssetSetter {
    GamePanel gp; 
    
    public AssetSetter(GamePanel gp){
        this.gp = gp; 
    }
    
    public void setObject(){
        gp.obj[1] = new OBJ_Key(); 
        gp.obj[1].worldX = 26 * gp.tileSize; 
        gp.obj[1].worldY = 2 * gp.tileSize; 
        gp.obj[1].collision = true;
        
        gp.obj[2] = new OBJ_Key(); 
        gp.obj[2].worldX = 2 * gp.tileSize; 
        gp.obj[2].worldY = 38 * gp.tileSize; 
        gp.obj[2].collision = true;
        
        gp.obj[3] = new OBJ_Pokeball(); 
        gp.obj[3].worldX = 36 * gp.tileSize; 
        gp.obj[3].worldY = 409 * gp.tileSize; 
        gp.obj[3].collision = true;
    }
}
