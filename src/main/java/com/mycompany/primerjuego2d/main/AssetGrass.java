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
        
        gp.obj[9] = new OBJ_Hierba(); 
        gp.obj[9].worldX = 57 * gp.tileSize; 
        gp.obj[9].worldY = 204 * gp.tileSize; 
        gp.obj[9].collision = false;
       
        gp.obj[10] = new OBJ_Hierba(); 
        gp.obj[10].worldX = 56 * gp.tileSize; 
        gp.obj[10].worldY = 204 * gp.tileSize; 
        gp.obj[10].collision = false;
        
        gp.obj[11] = new OBJ_Hierba(); 
        gp.obj[11].worldX = 55 * gp.tileSize; 
        gp.obj[11].worldY = 204 * gp.tileSize; 
        gp.obj[11].collision = false;
        
        gp.obj[12] = new OBJ_Hierba(); 
        gp.obj[12].worldX = 57 * gp.tileSize; 
        gp.obj[12].worldY = 205 * gp.tileSize; 
        gp.obj[12].collision = false;
       
        gp.obj[13] = new OBJ_Hierba(); 
        gp.obj[13].worldX = 56 * gp.tileSize; 
        gp.obj[13].worldY = 205 * gp.tileSize; 
        gp.obj[13].collision = false;
        
        gp.obj[14] = new OBJ_Hierba(); 
        gp.obj[14].worldX = 55 * gp.tileSize; 
        gp.obj[14].worldY = 205 * gp.tileSize; 
        gp.obj[14].collision = false;
        
        gp.obj[15] = new OBJ_Hierba(); 
        gp.obj[15].worldX = 57 * gp.tileSize; 
        gp.obj[15].worldY = 206 * gp.tileSize; 
        gp.obj[15].collision = false;
       
        gp.obj[16] = new OBJ_Hierba(); 
        gp.obj[16].worldX = 56 * gp.tileSize; 
        gp.obj[16].worldY = 206 * gp.tileSize; 
        gp.obj[16].collision = false;
        
        gp.obj[17] = new OBJ_Hierba(); 
        gp.obj[17].worldX = 55 * gp.tileSize; 
        gp.obj[17].worldY = 206 * gp.tileSize; 
        gp.obj[17].collision = false;
    }
    
}
