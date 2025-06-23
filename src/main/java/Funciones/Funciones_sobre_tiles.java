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
    
    public Funciones_sobre_tiles(GamePanel gp){
        this.gp = gp; 
    }
    
    public void Encontrar(){
        if(gp.player.worldX == 28 && gp.player.worldY == 399){
            System.out.println("Has pisado un pixel trampa");
        }
    }
}
