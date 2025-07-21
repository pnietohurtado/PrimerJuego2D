/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Entity.NPC_Bulbasur;
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
        
        /*
        gp.obj[0] = new OBJ_Key(); 
        gp.obj[0].worldX = 47 * gp.tileSize; 
        gp.obj[0].worldY = 212 * gp.tileSize; 
        gp.obj[0].collision = true;
        gp.obj[0].multiplicadorSizeX = 8; // Se encarga de asignar un número de pixeles determinado en la pantalla
        gp.obj[0].multiplicadorSizeY = 4;
        */
        
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
       
        gp.obj[5] = new OBJ_Hacha(); 
        gp.obj[5].worldX = 56 * gp.tileSize; 
        gp.obj[5].worldY = 195 * gp.tileSize; 
        gp.obj[5].collision = true;
        
        
        
        gp.obj[6] = new OBJ_Hierba(); 
        gp.obj[6].worldX = 57 * gp.tileSize; 
        gp.obj[6].worldY = 203 * gp.tileSize; 
        gp.obj[6].collision = true;
       
        gp.obj[7] = new OBJ_Hierba(); 
        gp.obj[7].worldX = 56 * gp.tileSize; 
        gp.obj[7].worldY = 203 * gp.tileSize; 
        gp.obj[7].collision = true;
        
        gp.obj[8] = new OBJ_Hierba(); 
        gp.obj[8].worldX = 55 * gp.tileSize; 
        gp.obj[8].worldY = 203 * gp.tileSize; 
        gp.obj[8].collision = true;
    }
    
    public void setNPC(){ // Aquí es donde vamos a posicionar los NPC 
        
        gp.npc[0] = new NPC_CocheJuan(gp); 
        gp.npc[0].worldX = gp.tileSize * 54; 
        gp.npc[0].worldY = gp.tileSize * 207; 
        gp.npc[0].actionCountNPC = 1; 
        
        
        gp.npc[1] = new NPC_Bulbasur(gp); 
        gp.npc[1].worldX = gp.tileSize * 62; 
        gp.npc[1].worldY = gp.tileSize * 215; 
        gp.npc[1].actionCountNPC = 1; 
        gp.npc[1].duplicarSize = 2; 
        
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
