/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Objects.SuperObject;
import java.awt.image.BufferedImage;
import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.KeyHandler;
import com.mycompany.primerjuego2d.main.UtilityTool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**/
/**
 *
 * @author pablo
 */
public class Player extends Entity{
    
    KeyHandler keyHandler; 
    Random random = new Random(); 
    
    
    public final int screenX;
    public final int screenY; 
    
    //public int hasKey = 0; // How many key the player currently has 
    public int hasPokeball = 0; // It works as a "Inventory" 
    public int hasKey = 0;
    
    
    
    public ArrayList<SuperObject> inventario = new ArrayList<>(); 
    
    public Player(GamePanel gp, KeyHandler kh){
        super(gp); 
        
        this.keyHandler = kh; 
        
        screenX = gp.screenWidth/2 - (gp.tileSize / 2); 
        screenY = gp.screenHeight/2 - (gp.tileSize /2); 
        
        solidArea = new Rectangle();
        solidArea.x = 8; 
        solidArea.y = 16; 
        
        solidAreaDefaultX = solidArea.x; 
        solidAreaDefaultY = solidArea.y; 
        
        solidArea.width = 32; 
        solidArea.height = 32; 
        
        setDefaultValues(); 
        getPlayerImage(); 
    }
    
    public void setDefaultValues()
    {
        worldX = gp.tileSize * 55 ; 
        worldY = gp.tileSize * 215 ; 
        speed = 4; 
        direction = "down"; 
    }
    
    // ---------------- Variables de prueba sobre las skins player -------------
    
    String front1[] = {"/player/Front1","/NPC/CocheFront"}; 
    String front2[] = {"/player/Front2","/NPC/CocheFront"}; 
    String right1[] = {"/player/Rigth1","/NPC/CocheR1"};  
    String right2[] = {"/player/Rigth1","/NPC/CocheR2"}; 
    String left1[] = {"/player/Left1","/NPC/CocheL1"}; 
    String left2[] = {"/player/Left2","/NPC/CocheL2"}; 
    String back1[] = {"/player/Up1","/NPC/CocheB"}; 
    String back2[] = {"/player/Up2","/NPC/CocheB"}; 
    
    // -------------------------------------------------------------------------
    
    public void getPlayerImage()
    {
       
        f1 = setUp(front1[gp.skinAppereance]);
        f2 = setUp(front2[gp.skinAppereance]);
        r1 = setUp(right1[gp.skinAppereance]);
        r2 = setUp(right2[gp.skinAppereance]);
        l1 = setUp(left1[gp.skinAppereance]);
        l2 = setUp(left2[gp.skinAppereance]);
        b1 = setUp(back1[gp.skinAppereance]);
        b2 = setUp(back2[gp.skinAppereance]);
       
    }
   
    
    public void update()
    {
        if(keyHandler.upPressed == true || keyHandler.downPressed == true ||
                keyHandler.leftPressed == true || keyHandler.rightPressed == true ){
            
            if(keyHandler.upPressed == true)
            {
                direction = "up"; 
            }
            else if(keyHandler.downPressed == true)
            {
                direction = "down";               
            }
            else if(keyHandler.leftPressed == true)
            {
                direction="left";                
            }
            else if(keyHandler.rightPressed == true)
            {
                direction="right";                
            }

            // Check tile collision 
            collision = false; 
            gp.cH.checkTile(this);
            
            // Check object Collision 
            int objIndex = gp.cH.checkObject(this, true); 
            pickUpObject (objIndex); 
            
            int npcIndex = gp.cH.checkEntity(this, gp.npc); 
            //int playerIndex = gp.cH.checkEntity(this, gp.player); 
            interactNPC(npcIndex); 
          
            
            // If collision is false, player can't move 
            if(collision == false){
                switch(direction ){
                    case "up": 
                        worldY -= speed;  
                        break; 
                    case "down": 
                        worldY += speed; 
                        break; 
                    case "left": 
                        worldX -= speed;
                        break; 
                    case "right": 
                        worldX += speed; 
                        break; 
                }
            }
            
            spriteCounter++; 
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2; 
                }else if (spriteNum == 2){
                    spriteNum = 1; 
                }
                spriteCounter = 0; 
            }    
            
        }
    }
    
    // ------------------- Interacción del jugador con un objeto ---------------
    
    public void pickUpObject(int i){
        if(i != 999){
            System.out.println("obj -> " +i);
            
            String objectName = gp.obj[i].name; 
            
            switch(objectName){
                
                case "Llave": 
                    if(keyHandler.interactEntity == true){
                
                        hasKey++; 
                        inventario.add(gp.obj[i]);
                        gp.obj[i] = null; 
                        gp.ui.showMessage("You got a key!"); 
                   
                    }
                    break; 
                case "Pokeball": 
                    //if(keyHandler.interactEntity == true){ // En el caso de que sea necesario pulsar para recoger
                        hasPokeball++; 
                        inventario.add(gp.obj[i]);
                        //gp.showInventory = true;  Para poder mostrar por pantalla el objeto que se ha recogido
                        gp.obj[i] = null; 
                        //gp.gameState = gp.dialogueState;  
                        System.out.println(inventario);
                    //}
                    break; 
            
            }
        }
    }
    
    // -------------------------------------------------------------------------
    
    
    // ------------------- Interacciones del Jugador con el NPC ----------------
    public int numero = 0; 
    public void interactNPC(int i){
        if(i != 999){
            
            if(keyHandler.interactEntity == false){
                
                gp.gameState = gp.dialogueState; 
                int numeroAleatorio = random.nextInt(11) + 1;  
                
                if(numeroAleatorio == 1){ // Existe una posibilidad de que desaparezca el NPC 
                    
                    gp.npc[i] = null; 
                    gp.skinAppereance = 1; 
                    gp.player.speed = 18; 
                }
            
            }
            
         numero = i;    
        }
    }
    
    // -------------------------------------------------------------------------
    

    
    // ------------ Función para dibujar las características del player --------
    
    public void draw(Graphics2D g2) 
    {
        /*
        g2.setColor(Color.white); 
        g2.fillRect(this.x, this.y, gamePanel.tileSize, gamePanel.tileSize);  
        */
        
        getPlayerImage(); // Ayuda a actualizar la skin del personaje y este pueda cambiar
        
        BufferedImage image = null; 
        switch(direction) {
            case "up": 
                if(spriteNum == 1) {
                    image = b1; 
                }else if(spriteNum == 2){
                    image = b2; 
                }
                break;
            case "down": 
                if(spriteNum == 1) {
                    image = f1; 
                }else if(spriteNum == 2){
                    image = f2; 
                }
                break;
            case "left": 
                if(spriteNum == 1) {
                    image = l1; 
                }else if(spriteNum == 2){
                    image = l2; 
                }
                break;
            case "right": 
                if(spriteNum == 1) {
                    image = r1; 
                }else if(spriteNum == 2){
                    image = r2; 
                }
                break;
        }
        // ------------ Usamos estas declaraciones para cambiar opacidad -------
            /*
            float alpha = 0.5f; 
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha); 
            g2.setComposite(ac);
            */
        // ---------------------------------------------------------------------
        
            g2.drawImage(image,screenX,screenY,gp.tileSize, gp.tileSize,null); 
            
            
            // ----------- Para poder enseñar las colisiones del personaje -----
            if(this.keyHandler.showCollisions == true){
                g2.setColor(Color.red); 
                g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            }
            // -----------------------------------------------------------------
    }
    
}
