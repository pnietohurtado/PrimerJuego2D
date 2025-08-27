/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import com.mycompany.primerjuego2d.main.GamePanel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

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
                gp.player.seleccion_pokemon = i; 
                try {
                    InputStream is = getClass().getResourceAsStream("/Datos_evolucion/"+gp.equipo_pokemones.get(gp.player.seleccion_pokemon).getPokedex()+".txt"); 
                    br = new BufferedReader(new InputStreamReader(is)); 
                    String linea ; 

                    while((linea = br.readLine()) != null){
                        String partes[] = linea.split(" "); 

                        final int lvl_esperado = Integer.parseInt(partes[0]); 
                        final int pokedex_esperado = Integer.parseInt(partes[1]); 
                        final int indice = gp.player.seleccion_pokemon; 
                        
                        System.out.println("Derrotado!" + " " + pokedex_esperado);
                        SwingUtilities.invokeLater(() -> {
                            if(lvl_esperado <= gp.equipo_pokemones.get(gp.player.seleccion_pokemon).getLevel()){
                                gp.equipo_pokemones.get(gp.player.seleccion_pokemon).setPokedex(pokedex_esperado);
                                gp.player.pokedex_cambiada = pokedex_esperado; 
                                System.out.println("Pokedex " + pokedex_esperado);
                                gp.nombres.actualizar_xp("pokedex", gp.player.seleccion_pokemon);
                            }
                        });


                    }
                    br.close();


                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Evolucion_pokemon.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Evolucion_pokemon.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
    }
    
}
