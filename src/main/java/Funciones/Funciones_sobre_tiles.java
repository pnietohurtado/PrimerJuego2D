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
    
    Random random = new Random(); 
    
    public Funciones_sobre_tiles(GamePanel gp){
        this.gp = gp; 
    }

   
    
    // ----------------------- Apariciones Pokemones en ruta -------------------
    
    public void grassBattle01(){
        
        if(!(gp.equipo_pokemones.isEmpty())){
            
            int numero[] = {37, 64, 83, 102, 111, 129, 143}; 

            int numeroAleatorio = random.nextInt(200) + 1;  
            int aparicion = random.nextInt(5); // Hay que poner exactamente el número de 
            
            gp.keyHandler.hp_enemy = 1.0f; // Vida del pokemon rival 
            
            if(numeroAleatorio == 1){
                gp.player.sprite_bicho_attack = numero[aparicion]; 

                // Seteamos el lvl del pokemon en cuestión 
                gp.ui.lvl = random.nextInt(10) + 1; 

                gp.gameState = gp.battleState; 


                gp.sonido.volume = -30.0f; 
                gp.sonido.play(5,true); // Para poder poner la música
            }
            
        }else{
            gp.gameState = gp.dialogueState; 
        }
    }
    
    
    // ----------------------- Entrada a contrucciones -------------------------
    
    public void enterBuilding01(){
        
        gp.tileManager.loadMap(gp.tileManager.mapas[1]); // Para poder cambiar el mapa 
        //gp.obj = new SuperObject[0]; // Para vaciar todos los objetos 
        gp.player.worldX = 24* gp.tileSize; 
        gp.player.worldY = 24* gp.tileSize; 
        
        gp.obj = new SuperObject[99]; // Volver a añadir objetos en el array
            
        gp.obj[1] = new OBJ_PokeballPokemon(); 
        gp.obj[1].worldX = 23 * gp.tileSize; 
        gp.obj[1].worldY = 22 * gp.tileSize; 
        gp.obj[1].collision = true;
        
        gp.obj[2] = new OBJ_PokeballPokemon(); 
        gp.obj[2].worldX = 24 * gp.tileSize; 
        gp.obj[2].worldY = 22 * gp.tileSize; 
        gp.obj[2].collision = true;
        
        gp.obj[3] = new OBJ_PokeballPokemon(); 
        gp.obj[3].worldX = 25 * gp.tileSize; 
        gp.obj[3].worldY = 22 * gp.tileSize; 
        gp.obj[3].collision = true;
        
    }
    
    
    public void enternBuilding02(){
        
        gp.tileManager.loadMap(gp.tileManager.mapas[2]); // Para poder cambiar el mapa
        //gp.obj = new SuperObject[0]; // Para vaciar todos los objetos
        
        
        
        gp.player.worldX = 24* gp.tileSize; 
        gp.player.worldY = 24* gp.tileSize; 
        
        
    }
    
    
    public void enterBuildingUnderground(){ // Entrada al subsuelo 
        
        gp.tileManager.loadMap(gp.tileManager.mapas[3]); // Para poder cambiar el mapa
        gp.player.worldX = 25* gp.tileSize; 
        gp.player.worldY = 29* gp.tileSize;
        
        
        gp.npc[0] = new NPC_MewTwo(gp); 
        gp.npc[0].worldX = gp.tileSize * 25; 
        gp.npc[0].worldY = gp.tileSize * 20; 
        
        
    }
    
    public void enterBuilding03() { // Entrada secundaria para la fabrica desde pueblo Paleta
        
        gp.tileManager.loadMap(gp.tileManager.mapas[2]); // Para poder cambiar el mapa
        gp.player.worldX = 25 * gp.tileSize; 
        gp.player.worldY = 8 * gp.tileSize;
        
    }
    
    
    // ----------------------- Salida de la construcción -----------------------
    
    public void exitBuilding01(){ // Segunda salida Fabrica01 
        
        gp.player.worldX = 55* gp.tileSize; 
        gp.player.worldY = 166* gp.tileSize; 
            
        gp.tileManager.loadMap(gp.tileManager.mapas[0]);
            
        //gp.aSetter.setObject();
            
        gp.gameState = gp.playState;
        
    }
    
    public void exitBuilding02(){ // Segunda salida Fabrica02 
        
        gp.player.worldX = 55* gp.tileSize; 
        gp.player.worldY = 171* gp.tileSize; 
            
        gp.tileManager.loadMap(gp.tileManager.mapas[0]);
            
        //gp.aSetter.setObject();
            
        gp.gameState = gp.playState;
        
    }
    
    
    public void exitBuilding03(){
        
        gp.player.worldX = 48* gp.tileSize; 
        gp.player.worldY = 215* gp.tileSize; 
            
        gp.tileManager.loadMap(gp.tileManager.mapas[0]);
            
        //gp.aSetter.setObject();
            
        gp.gameState = gp.playState;
        
    }

    
    
   
    
    
    
    
    
}
