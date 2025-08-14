/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import Objects.OBJ_Hacha;
import Objects.OBJ_Pokeball;
import com.mycompany.primerjuego2d.main.GamePanel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class GuardadoDeLosObjetos {
    
    private GamePanel gp; 
    private BufferedReader br; 
    private BufferedWriter bw; 
    
    public GuardadoDeLosObjetos(GamePanel gp){
        this.gp = gp; 
    }
    
    public void cargar_objetos(){
        try { 
            bw = new BufferedWriter(new FileWriter("datos_objetos.txt", false));
            for(int i = 1; i < gp.obj.length; i++){
                if(gp.obj[i] != null){
                    String linea = gp.obj[i].worldX + " " + gp.obj[i].worldY
                            + " " + gp.obj[i].existe + " " + gp.obj[i].name; 
                    
                    bw.write(linea);
                    bw.newLine();
                }
               
            }
            
            bw.close(); 
        } catch (IOException ex) {
            Logger.getLogger(GuardadoDeLosObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void poner_objetos(){
        
        try {
            br = new BufferedReader(new FileReader("datos_objetos.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GuardadoDeLosObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        String linea; 
        int contador = 0;         
        try {
            while((linea = br.readLine()) != null){
                
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                
                String partes[] = linea.split(" ");
                if (partes.length < 4) continue;
                
                int X = Integer.parseInt(partes[0]);
                int Y = Integer.parseInt(partes[1]); 
                int existe = Integer.parseInt(partes[2]);
                String nombre = partes[3]; 
                
                if(existe == 1 && nombre.equals("Pokeball")){
                    gp.obj[contador] = new OBJ_Pokeball(); 
                    gp.obj[contador].worldX = X; 
                    gp.obj[contador].worldY = Y; 
                    gp.obj[contador].collision = true;
                }else if(existe == 1 && nombre.equals("Hacha")){
                    gp.obj[contador] = new OBJ_Hacha(); 
                    gp.obj[contador].worldX = X; 
                    gp.obj[contador].worldY = Y; 
                    gp.obj[contador].collision = true;
                }
                contador ++; 
                
            }
        } catch (IOException ex) {            
            Logger.getLogger(GuardadoDeLosObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    
}
