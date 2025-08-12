/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;


import Entity.NPC_CocheJuan;
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
        
        gp.obj[99] = new OBJ_Pokeball(); 
        gp.obj[99].worldX = 50 * gp.tileSize; 
        gp.obj[99].worldY = 176 * gp.tileSize; 
        gp.obj[99].collision = true;
        
        gp.obj[98] = new OBJ_Pokeball(); 
        gp.obj[98].worldX = 51 * gp.tileSize; 
        gp.obj[98].worldY = 176 * gp.tileSize; 
        gp.obj[98].collision = true;
        
        gp.obj[97] = new OBJ_Pokeball(); 
        gp.obj[97].worldX = 52 * gp.tileSize; 
        gp.obj[97].worldY = 176 * gp.tileSize; 
        gp.obj[97].collision = true;
        
        gp.obj[7] = new OBJ_Pokeball(); 
        gp.obj[7].worldX = 73 * gp.tileSize; 
        gp.obj[7].worldY = 167 * gp.tileSize; 
        gp.obj[7].collision = true;
        
        gp.obj[8] = new OBJ_Hacha(); 
        gp.obj[8].worldX = 56 * gp.tileSize; 
        gp.obj[8].worldY = 160 * gp.tileSize; 
        gp.obj[8].collision = true;
        
        gp.obj[11] = new OBJ_CentroPokemon(); 
        gp.obj[11].worldX = 53 * gp.tileSize; 
        gp.obj[11].worldY = 162 * gp.tileSize; 
        gp.obj[11].collision = true;
        gp.obj[11].multiplicadorSizeX = 3; 
        gp.obj[11].multiplicadorSizeY = 3; 
        
    }
    
    public void setNPC(){ // Aquí es donde vamos a posicionar los NPC 
        /*
        gp.npc[0] = new NPC_CocheJuan(gp); 
        gp.npc[0].worldX = gp.tileSize * 51; 
        gp.npc[0].worldY = gp.tileSize * 172; 
        gp.npc[0].actionCountNPC = 1; 
        */
        
        gp.npc[0] = new NPC_ShopGuy(gp); 
        gp.npc[0].worldX = gp.tileSize * 51; 
        gp.npc[0].worldY = gp.tileSize * 173; 
        gp.npc[0].actionCountNPC = 1; 
        
        
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
