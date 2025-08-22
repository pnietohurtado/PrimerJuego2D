/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author pablo
 */
public class Hierba_Alta extends SuperObject{
    
    public Hierba_Alta(){
        name = "HierbaAlta"; 
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objetos/Hierba_Alta.png")); 
        }catch(IOException e){
            e.printStackTrace(); 
        }
        collision = true; 
    }
    
}
