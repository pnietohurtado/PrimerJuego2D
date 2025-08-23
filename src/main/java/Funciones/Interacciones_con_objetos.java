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
public class Interacciones_con_objetos {
    private GamePanel gp; 
    
    public Interacciones_con_objetos(GamePanel gp){
        this.gp = gp; 
    }
    
    public void showDialogue(String messague){
        gp.ui.message = messague; 
        gp.gameState = gp.dialogueState; 
    }
}
