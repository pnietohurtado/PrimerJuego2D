/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import Pokemon.Pokemon;
import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.KeyHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tile.TileManager;

/**
 *
 * @author pablo
 */
public class CargarDatosPokemon {
    
    private GamePanel gp; 
    
    public CargarDatosPokemon(GamePanel gp){
        this.gp = gp; 
    }
    
    public void cargar(){
        InputStream is = getClass().getResourceAsStream("/DatosPokemon/pokemon_151.txt"); 
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
        
        String linea; 
        
        try {
            while((linea = br.readLine()) != null){
                String partes[] = linea.split(" "); 
                String numero = partes[0]; 
                String nombre = partes[1]; 
                gp.nombres_pokemon[Integer.parseInt(numero)] = nombre;
                
            }
            br.close(); 
        } catch (IOException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void cargar_pokemones_equipo(){
        
        BufferedReader br; 
        
        try {
            br = new BufferedReader(new FileReader("EquipoPokemon.txt"));
            
            String linea; 
            
            try {
                while((linea = br.readLine()) != null){
                    linea = linea.trim();
                    if (linea.isEmpty()) continue;  

                    String partes[] = linea.split(" "); 
                    if (partes.length < 7) continue; 

                    int lvl = Integer.parseInt(partes[0]); 
                    int pokedex = Integer.parseInt(partes[1]); 
                    String nombre = partes[2]; 
                    int vida = Integer.parseInt(partes[3]); 
                    int ataque = Integer.parseInt(partes[4]); 
                    int defensa = Integer.parseInt(partes[5]); 
                    boolean objeto = Boolean.parseBoolean(partes[6]); 

                    Pokemon po = new Pokemon(lvl, pokedex, nombre, vida, ataque, defensa, objeto); 
                    gp.equipo_pokemones.add(po); 
}
 
            } catch (IOException ex) {
                Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CargarDatosPokemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
    private Random random = new Random(); 
    
    public void cargar_pokemon_capturado(){
        try { 
                     
            BufferedWriter br = new BufferedWriter(new FileWriter("EquipoPokemon.txt", true));
              
            int hp = 12 + ((random.nextInt(2) + 1) * gp.ui.lvl); 
            int attack = 12 + ((random.nextInt(2) + 1) * gp.ui.lvl); 
            int defense = 12 + ((random.nextInt(2) + 1) * gp.ui.lvl); 
            
            String linea = gp.ui.lvl + " " + gp.player.sprite_bicho_attack + " " + 
                    gp.nombres_pokemon[gp.player.sprite_bicho_attack ] + " " + String.valueOf(hp) + " " + String.valueOf(attack) +  " " + 
                    String.valueOf(defense) + " " + "true"; 
            
            //System.out.println("Datos " + linea);
            
            br.write(linea);
            br.newLine();
            
            //br.flush();
            br.close();
            
            gp.sonido.stop(5);
            
            gp.gameState = gp.playState; 
                        
        } catch (IOException ex) {
            Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
