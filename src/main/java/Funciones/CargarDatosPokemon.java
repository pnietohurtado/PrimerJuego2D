/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import Entity.NPC_Pokemon;
import Pokemon.Pokemon;
import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.KeyHandler;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    
    // ----------------- Cargar los sprites de todos los pokemones -------------
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
    
    // -------------------------------------------------------------------------
    
    
    
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
                    if (partes.length < 9) continue; 

                    int lvl = Integer.parseInt(partes[0]); 
                    int pokedex = Integer.parseInt(partes[1]); 
                    String nombre = partes[2]; 
                    int vidaMax = Integer.parseInt(partes[3]); 
                    int vida = Integer.parseInt(partes[4]); 
                    int ataque = Integer.parseInt(partes[5]); 
                    int defensa = Integer.parseInt(partes[6]); 
                    boolean objeto = Boolean.parseBoolean(partes[7]); 
                    int id = Integer.parseInt(partes[8]);
                    int xp = Integer.parseInt(partes[9]);

                    Pokemon po = new Pokemon(lvl, pokedex, nombre,vidaMax, vida, ataque, defensa, objeto, id, xp); 
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
    
    public void cargar_pokemon_capturado(String nombre){
        try { 
                     
            BufferedWriter br = new BufferedWriter(new FileWriter("EquipoPokemon.txt", true));
              
            int hp = 12 + ((random.nextInt(2) + 1) * gp.ui.lvl); 
            int attack = 12 + ((random.nextInt(2) + 1) * gp.ui.lvl); 
            int defense = 12 + ((random.nextInt(2) + 1) * gp.ui.lvl); 
            String linea; 
            
            if(nombre == null){
                linea = gp.ui.lvl + " " + gp.player.sprite_bicho_attack + " " + 
                    gp.nombres_pokemon[gp.player.sprite_bicho_attack ] + " " + (gp.ui.lvl + gp.player.sprite_bicho_attack) + " "+  (gp.ui.lvl + gp.player.sprite_bicho_attack) + " " + String.valueOf(attack) +  " " + 
                    String.valueOf(defense) + " " + "true " + (gp.equipo_pokemones.size()+1) +" " +  0; 
            }else{
                linea = gp.ui.lvl + " " + gp.player.sprite_bicho_attack + " " + 
                    nombre + " " + (gp.ui.lvl + gp.player.sprite_bicho_attack) + " "+  (gp.ui.lvl + gp.player.sprite_bicho_attack) + " " + String.valueOf(attack) +  " " + 
                    String.valueOf(defense) + " " + "true " + (gp.equipo_pokemones.size()+1) +" " + 0; 
            }
            
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
    
    
    
    public void cargar_vida_pokemon_actual(String nombre_pokemon){
        BufferedReader br; 
        
        try {
            br = new BufferedReader(new FileReader("EquipoPokemon.txt"));
            
            String linea; 
            
            try {
                while((linea = br.readLine()) != null){
                    String partes[] = linea.split(" "); 
                    
                    if(partes[2].equals(nombre_pokemon)){
                        gp.player.vida_pokemon_compañero = Float.parseFloat(partes[3]); 
}                   }
 
            } catch (IOException ex) {
                Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CargarDatosPokemon.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    
    public void actualizar_vida_compañero(int ID){
        BufferedReader br;
        BufferedWriter bw; 
        try { 
        
            br = new BufferedReader(new FileReader("EquipoPokemon.txt"));
            ArrayList<String> lineas = new ArrayList<>(); 
            String linea;     
            
            while((linea = br.readLine()) != null){
                        
                    linea = linea.trim();
                    if (linea.isEmpty()) continue;  

                    String partes[] = linea.split(" "); 
                    if (partes.length < 10){
                        lineas.add(linea); 
                        continue;
                    } 
                    
                    int lvl = Integer.parseInt(partes[0]); 
                    int pokedex = Integer.parseInt(partes[1]); 
                    String nombre = partes[2]; 
                    int vidaMax = Integer.parseInt(partes[3]); 
                    int vida = Integer.parseInt(partes[4]); 
                    int ataque = Integer.parseInt(partes[5]); 
                    int defensa = Integer.parseInt(partes[6]); 
                    boolean objeto = Boolean.parseBoolean(partes[7]); 
                    int id = Integer.parseInt(partes[8]);
                    int xp = Integer.parseInt(partes[9]); 
                    
                    if(id == ID){
                        
                        linea = lvl + " " + pokedex + " " + nombre +" "+ vidaMax +  " " + gp.player.vida_pokemon_restante + " " + ataque
                                + " " + defensa + " " + objeto + " " + id + " " + xp; 
                        
                    }
                    
                    lineas.add(linea); 
                    
                    
            }
            
            br.close();
            bw = new BufferedWriter(new FileWriter("EquipoPokemon.txt", false));
            
            for(String l: lineas){
                bw.write(l);
                bw.newLine();
            }
            bw.close(); 
                        
        } catch (IOException ex) {
            Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
   

    public void actualizar_xp(String accion) {
        BufferedReader br;
        BufferedWriter bw; 
        try { 
        
            br = new BufferedReader(new FileReader("EquipoPokemon.txt"));
            ArrayList<String> lineas = new ArrayList<>(); 
            String linea;     
            
            while((linea = br.readLine()) != null){
                        
                    linea = linea.trim();
                    if (linea.isEmpty()) continue;  

                    String partes[] = linea.split(" "); 
                    if (partes.length < 10){
                        lineas.add(linea); 
                        continue;
                    } 
                    
                    int lvl = Integer.parseInt(partes[0]); 
                    int pokedex = Integer.parseInt(partes[1]); 
                    String nombre = partes[2]; 
                    int vidaMax = Integer.parseInt(partes[3]); 
                    int vida = Integer.parseInt(partes[4]); 
                    int ataque = Integer.parseInt(partes[5]); 
                    int defensa = Integer.parseInt(partes[6]); 
                    boolean objeto = Boolean.parseBoolean(partes[7]); 
                    int id = Integer.parseInt(partes[8]);
                    int xp = Integer.parseInt(partes[9]); 
                    
                    if(xp + 50 == 100){
                        lvl++; 
                        vidaMax = lvl + pokedex; 
                        linea = lvl + " " + pokedex + " " + nombre +" "+ vidaMax +  " " + vida + " " + ataque
                                + " " + defensa + " " + objeto + " " + id + " " + 0; 
                        
                    }else if(accion.equals("xp")){
                        xp += 50; 
                        linea = lvl + " " + pokedex + " " + nombre +" "+ vidaMax +  " " + vida + " " + ataque
                                + " " + defensa + " " + objeto + " " + id + " " + xp; 
                        
                    }else if(accion.equals("vida")){
                        vida = vidaMax; 
                        linea = lvl + " " + pokedex + " " + nombre +" "+ vidaMax +  " " + vida + " " + ataque
                                + " " + defensa + " " + objeto + " " + id + " " + xp; 
                    }
                    
                    lineas.add(linea); 
                    
                    
            }
            
            br.close();
            bw = new BufferedWriter(new FileWriter("EquipoPokemon.txt", false));
            
            for(String l: lineas){
                bw.write(l);
                bw.newLine();
            }
            bw.close(); 
                        
        } catch (IOException ex) {
            Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }


    public void borrar_datos_equipo_pokemon(){
        try { 
            BufferedWriter bw = new BufferedWriter(new FileWriter("EquipoPokemon.txt"));
            
            bw.write("");
            
        } catch (IOException ex) {
            Logger.getLogger(CargarDatosPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void cargar_vida_pokemon_restante(){
        try { 
            BufferedReader br = new BufferedReader(new FileReader("EquipoPokemon.txt"));
            
            String linea; 
            
            while((linea = br.readLine()) != null){
                String partes[] = linea.split(" "); 
                
                gp.player.vida_pokemon_restante = Integer.parseInt(partes[4]); 
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargarDatosPokemon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargarDatosPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }






}
