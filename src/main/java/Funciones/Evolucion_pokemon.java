/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import com.mycompany.primerjuego2d.main.GamePanel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class Evolucion_pokemon {
    private GamePanel gp; 
    
    
    public Evolucion_pokemon(GamePanel gp){
        this.gp = gp; 
    }
    
    public void evolucionar(){
        BufferedReader br; 
        BufferedWriter bw; 
        
        for(int i = 0; i < gp.equipo_pokemones.size(); i++){
            
            try { 
                System.out.println("Pokedex " + gp.equipo_pokemones.get(i).getPokedex());
                br = new BufferedReader(new FileReader(gp.equipo_pokemones.get(i).getPokedex()+".txt"));
                String linea ; 
                
                while((linea = br.readLine()) != null){
                    String partes[] = linea.split(" "); 
                    
                    int lvl_esperado = Integer.parseInt(partes[0]); 
                    int pokedex_esperado = Integer.parseInt(partes[1]); 
                    
                    if(gp.equipo_pokemones.get(i).getLevel() >= lvl_esperado){
                        gp.equipo_pokemones.get(i).setPokedex(pokedex_esperado);
                        
                        
                        br = new BufferedReader(new FileReader("EquipoPokemon.txt"));
                        ArrayList<String> lineas = new ArrayList<>(); 
                        String linea2 = "";     

                        while((linea = br.readLine()) != null){

                                linea = linea.trim();
                                if (linea.isEmpty()) continue;  

                                String partes2[] = linea.split(" "); 
                                if (partes.length < 10){
                                    lineas.add(linea); 
                                    continue;
                                } 
                                
                                int lvl = Integer.parseInt(partes2[0]); 
                                int pokedex = Integer.parseInt(partes2[1]); 
                                String nombre = partes2[2]; 
                                int vidaMax = Integer.parseInt(partes2[3]); 
                                int vida = Integer.parseInt(partes2[4]); 
                                int ataque = Integer.parseInt(partes2[5]); 
                                int defensa = Integer.parseInt(partes2[6]); 
                                boolean objeto = Boolean.parseBoolean(partes2[7]); 
                                int id = Integer.parseInt(partes2[8]);
                                int xp = Integer.parseInt(partes2[9]); 


                                if(gp.equipo_pokemones.get(i).getNombre().equals(nombre)){
                                    
                                    linea2 = lvl + " " + pokedex_esperado + " " + nombre +" "+ vidaMax +  " " + gp.player.vida_pokemon_restante + " " + ataque
                                            + " " + defensa + " " + objeto + " " + id + " " + xp; 

                                }

                                lineas.add(linea2); 


                        }

                        
                        bw = new BufferedWriter(new FileWriter("EquipoPokemon.txt", false));

                        for(String l: lineas){
                            bw.write(l);
                            bw.newLine();
                        }
                        bw.close(); 
                        
                        
                        
                        
                    }
                    
                }
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Evolucion_pokemon.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Evolucion_pokemon.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
