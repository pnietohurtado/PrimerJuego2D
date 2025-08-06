/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import Pokemon.Pokemon;
import com.mycompany.primerjuego2d.main.GamePanel;
import java.io.BufferedReader;
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
        InputStream is = getClass().getResourceAsStream("/DatosPokemon/EquipoPokemon.txt"); 
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
        
        String linea; 
        
        try {
            while((linea = br.readLine()) != null){
                String partes[] = linea.split(" "); 
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
            br.close(); 
        } catch (IOException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
