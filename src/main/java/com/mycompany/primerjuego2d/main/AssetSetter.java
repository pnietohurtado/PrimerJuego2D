/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Objects.OBJ_Key;

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
        gp.obj[0] = new OBJ_Key(); 
        gp.obj[0].worldX = 2 * gp.tileSize; 
        gp.obj[0].worldY = 20 * gp.tileSize; 
        
        gp.obj[1] = new OBJ_Key(); 
        gp.obj[1].worldX = 2 * gp.tileSize; 
        gp.obj[1].worldY = 23 * gp.tileSize; 
        
    }
}
