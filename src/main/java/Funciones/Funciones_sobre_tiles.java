/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import Entity.NPC_MewTwo;
import Objects.OBJ_PokeballPokemon;
import Objects.SuperObject;
import com.mycompany.primerjuego2d.main.GamePanel;
import java.util.Random;

/**
 *
 * @author pablo
 */
public class Funciones_sobre_tiles {
    private GamePanel gp; 
 
    // --------------------------- Variables -----------------------------------
    
    public Funciones_sobre_tiles(GamePanel gp){
        this.gp = gp; 
    }
    
    
   
    
    
    
    // ----------------------- Entrada a contrucciones -------------------------
    
    public void enterBuilding01(){

        gp.player.worldX = 72* gp.tileSize; 
        gp.player.worldY = 205* gp.tileSize; 
        
        gp.guardado.cargar_objetos();
        
        gp.obj = new SuperObject[99]; // Volver a añadir objetos en el array
            
        gp.obj[1] = new OBJ_PokeballPokemon(); 
        gp.obj[1].worldX = 71 * gp.tileSize; 
        gp.obj[1].worldY = 202 * gp.tileSize; 
        gp.obj[1].collision = true;
        
        gp.obj[2] = new OBJ_PokeballPokemon(); 
        gp.obj[2].worldX = 72 * gp.tileSize; 
        gp.obj[2].worldY = 202 * gp.tileSize; 
        gp.obj[2].collision = true;
        
        gp.obj[3] = new OBJ_PokeballPokemon(); 
        gp.obj[3].worldX = 73 * gp.tileSize; 
        gp.obj[3].worldY = 202 * gp.tileSize; 
        gp.obj[3].collision = true;
        
    }
    
    public void enterPokemonCenter(){
        
        
        if(gp.player.worldX <= (14 * gp.tileSize) && gp.player.worldY > (147 * gp.tileSize)){
            gp.player.worldX = 9 * gp.tileSize; 
            gp.player.worldY = 153 * gp.tileSize;
        }else{
            gp.player.worldX = 64 * gp.tileSize; 
            gp.player.worldY = 158 * gp.tileSize;
        }
    }
    
    
    // ----------------------- Salida de la construcción -----------------------
    
   
    
    public void exitBuilding03(){
        
        gp.player.worldX = 72* gp.tileSize; 
        gp.player.worldY = 207* gp.tileSize; 
        gp.guardado.poner_objetos();
    }
    
    public void exitPokemonCenter(){
        
        if(gp.player.worldX <= (14 * gp.tileSize) && gp.player.worldY > (147 * gp.tileSize)){
            gp.player.worldX = 9 * gp.tileSize; 
            gp.player.worldY = 155 * gp.tileSize;
        }else{
            gp.player.worldX = 64 * gp.tileSize; 
            gp.player.worldY = 160 * gp.tileSize;
        }
    }

    
    
   
    
    
    
    
    
}
