/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import com.mycompany.primerjuego2d.main.GamePanel;
import java.util.Random;

/**
 *
 * @author pablo
 */
public class Apariciones_en_hierba {
    private GamePanel gp; 
    private Random random = new Random(); 
    public int i1,i2,i3,i4,i5,i6,i7; 
    
    public Apariciones_en_hierba(GamePanel gp){
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
                gp.nombres.cargar_vida_pokemon_restante(1); 
                
                
                // Turno 
                gp.turnos.battle_turn = true; 
                
                gp.sonido.stop(1);
                gp.gameState = gp.battleState; 


                gp.sonido.play(5,true, "music"); // Para poder poner la música
            }
           
        }else{
            gp.ui.message = "No es seguro entrar sin pokemones..."; 
            gp.gameState = gp.dialogueState; 
        }
    }
    
    
}
