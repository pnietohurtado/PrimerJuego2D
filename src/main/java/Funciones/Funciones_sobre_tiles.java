/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import com.mycompany.primerjuego2d.main.GamePanel;

/**
 *
 * @author pablo
 */
public class Funciones_sobre_tiles {
    private GamePanel gp; 
    private int posicionX; 
    private int posicionY; 
    
    public Funciones_sobre_tiles(GamePanel gp){
        this.gp = gp; 
    }
    
    
    
    public void Encontrar(){
        posicionX = gp.player.worldX / gp.tileSize; 
        posicionY = gp.player.worldY / gp.tileSize; 
        
        if((posicionX == 29 && posicionY == 399)|| (posicionX == 28 && posicionY == 399) || (posicionX == 27 && posicionY == 399)){
                //gp.player.worldX = 29; 
                //gp.player.worldY = 500; 
        }
    }
}
