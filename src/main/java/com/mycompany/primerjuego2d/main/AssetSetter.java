/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Entity.NPC_CocheJuan;
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
        /*
        gp.obj[1] = new OBJ_Key(); 
        gp.obj[1].worldX = 26 * gp.tileSize; 
        gp.obj[1].worldY = 2 * gp.tileSize; 
        gp.obj[1].collision = true;
        
        gp.obj[2] = new OBJ_Key(); 
        gp.obj[2].worldX = 2 * gp.tileSize; 
        gp.obj[2].worldY = 38 * gp.tileSize; 
        gp.obj[2].collision = true;
        */
        gp.obj[0] = new OBJ_Pokeball(); 
        gp.obj[0].worldX = 56 * gp.tileSize; 
        gp.obj[0].worldY = 215 * gp.tileSize; 
        gp.obj[0].collision = true;
        
        gp.obj[1] = new OBJ_Pokeball(); 
        gp.obj[1].worldX = 57 * gp.tileSize; 
        gp.obj[1].worldY = 215 * gp.tileSize; 
        gp.obj[1].collision = true;
        
        gp.obj[2] = new OBJ_Pokeball(); 
        gp.obj[2].worldX = 58 * gp.tileSize; 
        gp.obj[2].worldY = 215 * gp.tileSize; 
        gp.obj[2].collision = true;
        
        gp.obj[3] = new OBJ_Pokeball(); 
        gp.obj[3].worldX = 59 * gp.tileSize; 
        gp.obj[3].worldY = 215 * gp.tileSize; 
        gp.obj[3].collision = true;
    }
    
    public void setNPC(){ // Aquí es donde vamos a posicionar los NPC 
        
        gp.npc[0] = new NPC_CocheJuan(gp); 
        gp.npc[0].worldX = gp.tileSize * 54; 
        gp.npc[0].worldY = gp.tileSize * 207; 
        
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
