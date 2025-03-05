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
public class OBJ_Key extends SuperObject{
    
    public OBJ_Key(){
        name = "Llave"; 
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objetos/Llave.png")); 
        }catch(IOException e){
            e.printStackTrace(); 
        }
        
    }
    
    
}
