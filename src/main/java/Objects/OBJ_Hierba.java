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
public class OBJ_Hierba extends SuperObject{
    
    public OBJ_Hierba(){
        name = "Hierba"; 
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objetos/Hierba.png")); 
        }catch(IOException e){
            e.printStackTrace(); 
        }
        collision = true; 
        
    }
    
}
