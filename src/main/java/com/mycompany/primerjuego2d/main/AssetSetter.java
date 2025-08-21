/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;


import Entity.NPC_ShopGuy;
import Objects.*;

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
       
        
        gp.obj[1] = new OBJ_Pokeball(); 
        gp.obj[1].worldX = 68 * gp.tileSize; 
        gp.obj[1].worldY = 202 * gp.tileSize; 
        gp.obj[1].collision = true;
        
        /*
        gp.obj[2] = new OBJ_Pokeball(); 
        gp.obj[2].worldX = 69 * gp.tileSize; 
        gp.obj[2].worldY = 202 * gp.tileSize; 
        gp.obj[2].collision = true;
        
        gp.obj[3] = new OBJ_Pokeball(); 
        gp.obj[3].worldX = 70 * gp.tileSize; 
        gp.obj[3].worldY = 202 * gp.tileSize; 
        gp.obj[3].collision = true;
        
        gp.obj[4] = new OBJ_Pokeball(); 
        gp.obj[4].worldX = 71 * gp.tileSize; 
        gp.obj[4].worldY = 202 * gp.tileSize; 
        gp.obj[4].collision = true;
        */
        
        gp.obj[6] = new OBJ_Pokeball(); 
        gp.obj[6].worldX = 59 * gp.tileSize; 
        gp.obj[6].worldY = 171 * gp.tileSize; 
        gp.obj[6].collision = true;
        
        gp.obj[7] = new OBJ_Pokeball(); 
        gp.obj[7].worldX = 82 * gp.tileSize; 
        gp.obj[7].worldY = 161 * gp.tileSize; 
        gp.obj[7].collision = true;
        
        gp.obj[8] = new OBJ_Pokeball(); 
        gp.obj[8].worldX = 61 * gp.tileSize; 
        gp.obj[8].worldY = 202 * gp.tileSize; 
        gp.obj[8].collision = true;
        /*
        gp.obj[8] = new OBJ_Hacha(); 
        gp.obj[8].worldX = 64 * gp.tileSize; 
        gp.obj[8].worldY = 157 * gp.tileSize; 
        gp.obj[8].collision = true;
        */
        
    }
    
    public void setNPC(){ // Aquí es donde vamos a posicionar los NPC 
        /*
        gp.npc[0] = new NPC_CocheJuan(gp); 
        gp.npc[0].worldX = gp.tileSize * 51; 
        gp.npc[0].worldY = gp.tileSize * 172; 
        gp.npc[0].actionCountNPC = 1; 
        */
        
        /*
        gp.npc[0] = new NPC_ShopGuy(gp); 
        gp.npc[0].worldX = gp.tileSize * 61; 
        gp.npc[0].worldY = gp.tileSize * 158; 
        gp.npc[0].actionCountNPC = 1; 
        */
        
        /*
        gp.npc[1] = new NPC_CocheJuan(gp); 
        gp.npc[1].worldX = gp.tileSize * 28; 
        gp.npc[1].worldY = gp.tileSize * 399; 
        */
        /*
        gp.npc[2] = new NPC_CocheJuan(gp); 
        gp.npc[2].worldX = gp.tileSize * 25; 
        gp.npc[2].worldY = gp.tileSize * 411; 
        */
    }
    
  
}
