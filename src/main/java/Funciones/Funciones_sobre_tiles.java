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
    
    private int actionCount1 = 1; 
    
    
    
    public void Encontrar(){
        posicionX = gp.player.worldX / gp.tileSize; 
        posicionY = gp.player.worldY / gp.tileSize; 
        
        if((posicionX == 54 && posicionY == 207)|| (posicionX == 55 && posicionY == 207) || (posicionX == 56 && posicionY == 207) || (posicionX == 57 && posicionY == 207)){
//            if(actionCount1 > 0){
//                gp.gameState = gp.dialogueState; 
//                actionCount1 = 0; 
//            }
        }
    }
}
