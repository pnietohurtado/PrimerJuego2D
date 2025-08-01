/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;


import Entity.NPC_CocheJuan;
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
        gp.obj[1].worldX = 56 * gp.tileSize; 
        gp.obj[1].worldY = 215 * gp.tileSize; 
        gp.obj[1].collision = true;
        
        gp.obj[2] = new OBJ_Pokeball(); 
        gp.obj[2].worldX = 57 * gp.tileSize; 
        gp.obj[2].worldY = 215 * gp.tileSize; 
        gp.obj[2].collision = true;
        
        gp.obj[3] = new OBJ_Pokeball(); 
        gp.obj[3].worldX = 58 * gp.tileSize; 
        gp.obj[3].worldY = 215 * gp.tileSize; 
        gp.obj[3].collision = true;
        
        gp.obj[4] = new OBJ_Pokeball(); 
        gp.obj[4].worldX = 59 * gp.tileSize; 
        gp.obj[4].worldY = 215 * gp.tileSize; 
        gp.obj[4].collision = true;
        
        gp.obj[6] = new OBJ_Pokeball(); 
        gp.obj[6].worldX = 49 * gp.tileSize; 
        gp.obj[6].worldY = 176 * gp.tileSize; 
        gp.obj[6].collision = true;
        
        gp.obj[7] = new OBJ_CentroPokemon(); 
        gp.obj[7].worldX = 53 * gp.tileSize; 
        gp.obj[7].worldY = 162 * gp.tileSize; 
        gp.obj[7].collision = true;
        gp.obj[7].multiplicadorSizeX = 3; 
        gp.obj[7].multiplicadorSizeY = 3; 
        
        
    }
    
    public void setNPC(){ // Aquí es donde vamos a posicionar los NPC 
        /*
        gp.npc[0] = new NPC_CocheJuan(gp); 
        gp.npc[0].worldX = gp.tileSize * 54; 
        gp.npc[0].worldY = gp.tileSize * 207; 
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
