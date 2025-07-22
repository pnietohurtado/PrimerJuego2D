/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Objects.OBJ_Hierba;

/**
 *
 * @author pablo
 */
public class AssetGrass {
    
    GamePanel gp; 
    
    public AssetGrass(GamePanel gp){
        this.gp = gp; 
    }
    
    public void setObject(){
        gp.obj[6] = new OBJ_Hierba(); 
        gp.obj[6].worldX = 57 * gp.tileSize; 
        gp.obj[6].worldY = 203 * gp.tileSize; 
        gp.obj[6].collision = false;
       
        gp.obj[7] = new OBJ_Hierba(); 
        gp.obj[7].worldX = 56 * gp.tileSize; 
        gp.obj[7].worldY = 203 * gp.tileSize; 
        gp.obj[7].collision = false;
        
        gp.obj[8] = new OBJ_Hierba(); 
        gp.obj[8].worldX = 55 * gp.tileSize; 
        gp.obj[8].worldY = 203 * gp.tileSize; 
        gp.obj[8].collision = false;
    }
    
}
