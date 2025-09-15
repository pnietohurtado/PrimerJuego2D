/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;


import Entity.NPC_ShopGuy;
import Objects.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import tile.TileManager;

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
       int i = 1; 
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("data_newgame.txt"));
            String linea;
            try {
                while((linea = br.readLine()) != null){
                    String partes[] = linea.split(" ");
                    int X = Integer.parseInt(partes[0]);
                    int Y = Integer.parseInt(partes[1]);
                    String nombre = partes[2];
                    
                    switch(nombre){
                        case "Pokeball":
                            gp.obj[i] = new OBJ_Pokeball();
                            gp.obj[i].worldX = X;
                            gp.obj[i].worldY = Y;
                            gp.obj[i].collision = true;
                            break;
                        case "Hacha":
                            gp.obj[i] = new OBJ_Hacha();
                            gp.obj[i].worldX = X;
                            gp.obj[i].worldY = Y;
                            gp.obj[i].collision = true;
                            break;
                        case "Hierba" :
                            gp.obj[i] = new Hierba_Alta();
                            gp.obj[i].worldX = X; 
                            gp.obj[i].worldY = Y;
                            gp.obj[i].collision = true;
                            break;
                    }
                    i ++; 
                }
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AssetSetter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(AssetSetter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }
    
    public void setNPC(){ // Aquí es donde vamos a posicionar los NPC 
        /*
        gp.npc[0] = new NPC_CocheJuan(gp); 
        gp.npc[0].worldX = gp.tileSize * 51; 
        gp.npc[0].worldY = gp.tileSize * 172; 
        gp.npc[0].actionCountNPC = 1; 
        */
        
    }
    
  
}
