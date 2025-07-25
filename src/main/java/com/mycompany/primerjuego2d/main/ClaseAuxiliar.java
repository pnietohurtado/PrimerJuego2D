/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class ClaseAuxiliar {
    
    private GamePanel gp; 
    
    public ClaseAuxiliar(GamePanel gp){
        this.gp = gp; 
    }
    
    
    public void guardarDatosObjetos(){
        try {
            
            int i = 1; 
            
            BufferedReader br = new BufferedReader(new FileReader("/Datos/DatosObjetos.txt"));
            String linea; 
            
            while((linea = br.readLine()) != null){
                String[] partes = linea.split(" "); 
                
                gp.obj[i].worldX = Integer.parseInt(partes[0]); 
                gp.obj[i].worldX = Integer.parseInt(partes[1]); 
                
                i++; 
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClaseAuxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException e){
            
        }
        
        
    }
    
    
}
