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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class CargarRadonmizador {
    private GamePanel gp; 
    private Random random; 
    private int rutas = 3; 
    
    public CargarRadonmizador(GamePanel gp){
        this.gp = gp; 
        this.random = new Random(); 
    }
    
    public void cargar_pokemoenes_random(){
        try { 
            BufferedWriter bw = new BufferedWriter(new FileWriter("randomizar.txt"));
            String linea = ""; 
            int i1, i2, i3, i4, i5, i6, i7; 
            for(int i = 0; i < rutas; i++){
                i1 = random.nextInt(151) + 1; 
                i2 = random.nextInt(151) + 1; 
                i3 = random.nextInt(151) + 1; 
                i4 = random.nextInt(151) + 1; 
                i5 = random.nextInt(151) + 1; 
                i6 = random.nextInt(151) + 1; 
                i7 = random.nextInt(151) + 1; 
                
                linea = i + " " + i1 + " " + i2 + " " + i3 + " " + i4 + " " + i5 + " " + i6 + " " + i7 ; 
                bw.write(linea);
                bw.newLine();
            }
            bw.close(); 
        } catch (IOException ex) {
            Logger.getLogger(CargarRadonmizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void leer_pokemons_random(int x){
        try { 
            BufferedReader br = new BufferedReader(new FileReader("randomizar.txt"));
            String linea;     
            
            try {
                while((linea = br.readLine()) != null){
                    
                    linea = linea.trim();
                    if (linea.isEmpty()) continue;  

                    String partes[] = linea.split(" "); 
                    if (partes.length < 9) continue; 
                    
                    int numero = Integer.parseInt(partes[0]); 
                    
                    if(numero == x){
                        gp.fst.i1 = Integer.parseInt(partes[1]);
                        System.out.println("Primero " + partes[1]);
                        gp.fst.i2 = Integer.parseInt(partes[2]);
                        gp.fst.i3 = Integer.parseInt(partes[3]);
                        gp.fst.i4 = Integer.parseInt(partes[4]);
                        gp.fst.i5 = Integer.parseInt(partes[5]);
                        gp.fst.i6 = Integer.parseInt(partes[6]);
                        gp.fst.i7 = Integer.parseInt(partes[7]);
                    }
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(CargarRadonmizador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargarRadonmizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
