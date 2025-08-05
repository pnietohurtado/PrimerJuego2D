/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

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
}
