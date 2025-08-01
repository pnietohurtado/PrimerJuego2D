/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import Objects.OBJ_Pokeball;
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
        int numero[] = {1,4,7,14,82,23}; 
                    
                    
        int numeroAleatorio = random.nextInt(200) + 1;  
        int aparicion = random.nextInt(5); // Hay que poner exactamente el número de 
        
        if(numeroAleatorio == 1){
            gp.player.sprite_bicho_attack = numero[aparicion]; 
                       
            gp.gameState = gp.battleState; 


            gp.sonido.volume = -30.0f; 
            gp.sonido.play(5,true); // Para poder poner la música
        }
    }
    
    
    // ----------------------- Entrada a contrucciones -------------------------
    
    public void enterBuilding01(){
        
        gp.obj = new SuperObject[0]; // Para vaciar todos los objetos 
        gp.player.worldX = 24* gp.tileSize; 
        gp.player.worldY = 24* gp.tileSize; 
        gp.tileManager.loadMap(gp.tileManager.mapas[1]); // Para poder cambiar el mapa 
            
            
        gp.obj = new SuperObject[99]; // Volver a añadir objetos en el array
            
        gp.obj[0] = new OBJ_Pokeball(); 
        gp.obj[0].worldX = 25 * gp.tileSize; 
        gp.obj[0].worldY = 22 * gp.tileSize; 
        gp.obj[0].collision = true;
            
        gp.obj[1] = new OBJ_Pokeball(); 
        gp.obj[1].worldX = 24 * gp.tileSize; 
        gp.obj[1].worldY = 22 * gp.tileSize; 
        gp.obj[1].collision = true;
            
        gp.obj[2] = new OBJ_Pokeball(); 
        gp.obj[2].worldX = 23 * gp.tileSize; 
        gp.obj[2].worldY = 22 * gp.tileSize; 
        gp.obj[2].collision = true;
        
    }
    
    
    public void enternBuilding02(){
        
        gp.obj = new SuperObject[0]; // Para vaciar todos los objetos 
        gp.player.worldX = 24* gp.tileSize; 
        gp.player.worldY = 24* gp.tileSize; 
        gp.tileManager.loadMap(gp.tileManager.mapas[2]); // Para poder cambiar el mapa
        
    }
    
    
    
    // ----------------------- Salida de la construcción -----------------------
    
    public void exitBuilding01(){
        
        gp.player.worldX = 55* gp.tileSize; 
        gp.player.worldY = 166* gp.tileSize; 
            
        gp.tileManager.loadMap(gp.tileManager.mapas[0]);
            
        gp.aSetter.setObject();
            
        gp.gameState = gp.playState;
        
    }
    
    
    
}
