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
    int i1,i2,i3,i4,i5,i6,i7; 
    public Funciones_sobre_tiles(GamePanel gp){
        this.gp = gp; 
        i1 = 1; 
        i2 = 1; 
        i3 = 1; 
        i4 = 1; 
        i5 = 1; 
        i6 = 1; 
        i7 = 1; 
        
    }
    
    
   
    
    // ----------------------- Apariciones Pokemones en ruta -------------------
    
    public void grassBattle01(int i){
        
        if(!(gp.equipo_pokemones.isEmpty())){
            gp.cargar_random.leer_pokemons_random(i);
            int numero[] = {i1, i2, i3, i4, i5, i6, i7}; 
            
            int numeroAleatorio = random.nextInt(200) + 1;  
            int aparicion = random.nextInt(5); // Hay que poner exactamente el número de 
            
            gp.keyHandler.hp_enemy = 1.0f; // Vida del pokemon rival 
            
            if(numeroAleatorio == 1){
                gp.player.sprite_bicho_attack = numero[aparicion]; 
               
                // Seteamos el lvl del pokemon en cuestión 
                gp.ui.lvl = random.nextInt(10) + 1; 

                // Seteamos la vida pokemon aliado; 
                gp.nombres.cargar_vida_pokemon_actual(gp.equipo_pokemones.get(0).getNombre()); // Obtenemos vida maxima; 
                gp.player.vida_pokemon_restante = (int) gp.player.vida_pokemon_compañero; // Ajustamos la vida
                
                // Turno 
                gp.turnos.battle_turn = true; 
                
                gp.sonido.stop(1);
                gp.gameState = gp.battleState; 


                gp.sonido.volume = -30.0f; 
                gp.sonido.play(5,true); // Para poder poner la música
            }
           
        }else{
            gp.ui.message = "No es seguro entrar sin pokemones..."; 
            gp.gameState = gp.dialogueState; 
        }
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
