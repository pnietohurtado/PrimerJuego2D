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
public class OBJ_Nadar extends SuperObject{
    public OBJ_Nadar(){
        name = "Nadar"; 
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objetos/Nadar.png")); 
        }catch(IOException e){
            e.printStackTrace(); 
        }
        collision = true; 
    }
}
