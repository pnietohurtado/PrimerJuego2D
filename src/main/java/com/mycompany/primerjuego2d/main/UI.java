/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Entity.NPC_Pokemon;
import Objects.OBJ_Pokeball;
import Objects.SuperObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author pablo
 */
public class UI {

    // ----------------- Variables relevantes (NO TOCAR) -----------------------
    
    GamePanel gp; 
    Font arial_40; 
    Graphics2D g2; 
    BufferedImage pokeImage; 
    ArrayList<SuperObject> inventario; 
    Set<SuperObject> objetosUnicos; 
    
    // ---------------------- Variabes menos importantes -----------------------
    
    public boolean messageOn = false; 
    public String message = ""; 
    public int commandNumber = 0; 
    public int titleScreenState = 0; // 0 : First Screen 
    Random random = new Random(); 
    
    public int lvl; // Para poder darle un "level" específico a un pokemon 
    
    BufferedImage image; 
    
    public String textoNPC[] = new String[10]; // Array de Textos 
    
    int x; 
    int y; 
    
    // --------------------- Constructor de la clase ---------------------------
    
    
    public UI(GamePanel gp){
        this.gp = gp; 
        
        Random random = new Random(); 
        
        this.x = 0; 
        this.y = 0; 
        
        arial_40 = new Font("Arial", Font.PLAIN, 20); 
        //OBJ_Key key = new OBJ_Key(); 
        //keyImage = key.image; 
        
        OBJ_Pokeball poke = new OBJ_Pokeball(); 

        
        
        textoNPC[0] = "Hola que tal"; 
        textoNPC[1] = "Que dices chacho"; 
        
    }
    
    // -------------------------- Funciones de  asistencia ---------------------
    
    public void showMessage(String text){
        message = text; 
        messageOn = true; 
    }
    
    public int getXForCenteredText(String text){
         
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        this.x = gp.screenWidth/2 - length/2; 
        return x; 
    }
    
    // -------------------------------------------------------------------------
    
    
    public void draw(Graphics2D g2){
        this.g2 = g2; 
        
        g2.setFont(arial_40);
        g2.setColor(Color.white); 

        if(gp.gameState == gp.playState){
            
        }else if(gp.gameState == gp.pauseState){
            drawPauseScreen(); 
        }else if(gp.gameState == gp.titleState){
            drawTitleScreen(); 
        }else if(gp.gameState == gp.dialogueState){
            drawDialogueScreen(); 
        }else if(gp.gameState == gp.inventoryState){
            drawInventoryScreen(); 
        }else if(gp.gameState == gp.battleState){
            battleScreen(); 
        }
    }
    
    // ------------------------- Cambio a escena de batallas -------------------
    

    
    public void healthBar(int x, int y, float damage){
        int width = 200;
        int height = 30;

        Color backgroundColor = new Color(247, 239, 163);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        Color borderColor = new Color(57, 97, 71);
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

        // Calculamos el porcentaje de vida
        
        float healthPercent = damage; 
        int healthBarWidth = (int) ((width - 20) * healthPercent);

        // Color de la vida (verde en este caso)
        g2.setColor(Color.GREEN);
        g2.fillRoundRect(x + 10, y + 10, healthBarWidth, height - 20, 15, 15);

    }
    
    
    public void battleScreen(){
        this.x = gp.tileSize * 2; 
        this.y = gp.tileSize / 2; 
        BufferedImage backgroundImage = null;
        
        try{
            
            backgroundImage = ImageIO.read(getClass().getResource("/Fondo/Fondo_Ataque.png"));
            
        }catch(IOException e){
            
        }
        
        //g2.setColor(Color.black);
        g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        
        
        this.x = gp.screenWidth/2 - (gp.tileSize*2)/2 ; 
        this.y += gp.tileSize*2; 
        gp.npc[998] = new NPC_Pokemon(gp, gp.player.sprite_bicho_attack); 
        g2.drawImage(gp.npc[998].f1, x + (gp.tileSize * 4), y - (gp.tileSize * (1 + 1/2)), gp.tileSize*4, gp.tileSize * 4, null); 
        
        
        
        
        
        // -------------- Barra de vida de atacante ----------------------------
        
        this.x = gp.tileSize * 1; 
        this.y = gp.tileSize / 2; 
        int width = gp.screenWidth - (gp.tileSize * 7); 
        int height = gp.tileSize * 3;
        
        drawSubWindowBattle(x,y,width, height); 
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F)); 
        this.x += gp.tileSize; 
        this.y += gp.tileSize; 
        g2.drawString(gp.nombres_pokemon[gp.player.sprite_bicho_attack], x, y); 
        g2.drawString(" lvl. " + String.valueOf(this.lvl), x * 3, y); 
        
        gp.ui.healthBar(x , y + gp.tileSize, gp.keyHandler.hp_enemy); // Barra de vida de los pokemones enemigos
        
        // ------------------------ Compañero de batalla -----------------------
        
        this.x = gp.tileSize; 
        this.y = gp.tileSize / 2; 
        
        gp.npc[997] = new NPC_Pokemon(gp, gp.equipo_pokemones.get(0).getPokedex()); 
        g2.drawImage(gp.npc[997].f1, x + (gp.tileSize * 3), y + (gp.tileSize * 5), gp.tileSize*4, gp.tileSize * 4, null);
        
        
        width = gp.screenWidth - (gp.tileSize * 8); 
        height = gp.tileSize * 2;
        
        drawSubWindowBattle(x * 8 ,y * 12,width, height); 
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F)); 
        this.x += gp.tileSize; 
        this.y += gp.tileSize; 
        g2.drawString(gp.equipo_pokemones.get(0).getNombre(), x * 5, y * 5); 
        g2.drawString(" lvl. " + gp.equipo_pokemones.get(0).getLevel(), x * 7, y * 5 ); 
        
        healthBar(x * 5 , y * 5, 1.0f); // Barra de vida de los pokemones enemigos 
        
        
        
        
        
        
        // ------------------ Menu dentro de la batalla ------------------------
        
        this.x = gp.tileSize; 
        this.y = gp.tileSize / 2; 
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla1.png"));
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        
        
        if(commandNumber == 0){
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla2.png"));
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla1.png"));
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        if(commandNumber == 1){
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla3.png"));
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla1.png"));
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        if(commandNumber == 2){
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla4.png"));
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla1.png"));
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        if(commandNumber == 3){
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla5.png"));
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        
        
        
        // ---------------------------------------------------------------------
    }
    
    
    
    
    
    
    
    
    public void drawSubWindowBattle(int x, int y, int width, int height){
        
        Color c = new Color(247, 239, 163); // 220 is going to show the transparecy of the window  
        g2.setColor(c); 
        g2.fillRoundRect(x,y,width,height,35,35); 
        
        c = new Color(57, 97, 71); 
        g2.setColor(c); 
        g2.setStroke(new BasicStroke(5)); 
        g2.drawRoundRect(x+5, y+5, width-10, height-10 , 25, 25);
        
    }
    
    
    
    
    
    
    
    
    // -------------------------------------------------------------------------
    
    public String dialogueText = "No es seguro entrar sin pokemones..."; 
    
    public void drawDialogueScreen(){
        int x = gp.tileSize * 2; 
        int y = gp.tileSize / 2; 
        int width = gp.screenWidth - (gp.tileSize * 4); 
        int height = gp.tileSize * 4;
        
        drawSubWindow(x,y,width, height); 
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F)); 
        x += gp.tileSize; 
        y += gp.tileSize; 
        g2.drawString(dialogueText, x, y); 
        
        
    }
    
    
    
    public void drawSubWindow(int x, int y, int width, int height){
        
        Color c = new Color(0,0,0,210); // 220 is going to show the transparecy of the window  
        g2.setColor(c); 
        g2.fillRoundRect(x,y,width,height,35,35); 
        
        c = new Color(255,255,255); 
        g2.setColor(c); 
        g2.setStroke(new BasicStroke(5)); 
        g2.drawRoundRect(x+5, y+5, width-10, height-10 , 25, 25);
        
    }
    
    
    // ----------------------- Pausa del juego con un dialogo ------------------
    public void drawPauseScreen(){
        int x = gp.tileSize * 4; 
        int y = gp.tileSize / 2; 
        int width = gp.screenWidth - (gp.tileSize * 8); 
        int height = gp.tileSize * 10;
        int contador = 0; 
        String text = ""; 
        
        drawSubWindow(x,y,width, height); 
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F)); 
        x += gp.tileSize * 0.5; 
        y += gp.tileSize; 
        g2.drawString("Juego Pausado" , x, y);

        text = "SAVE GAME"; 
        x = getXForCenteredText(text); 
        y = gp.tileSize * 4; 
        g2.drawString(text, x, y); 
        if(commandNumber == 0){
            g2.drawString(">",x - gp.tileSize,y); 
        }

        text = "BACK TO THE GAME"; 
        x = getXForCenteredText(text); 
        y = gp.tileSize * 6; 
        g2.drawString(text, x, y); 
        if(commandNumber == 1){
            g2.drawString(">",x - gp.tileSize,y); 
        }
        
        text = "STOP MUSIC"; 
        x = getXForCenteredText(text); 
        y = gp.tileSize * 8; 
        g2.drawString(text, x, y); 
        if(commandNumber == 2){
            g2.drawString(">",x - gp.tileSize,y); 
        }
        
        
        text = "QUIT GAME"; 
        x = getXForCenteredText(text); 
        y = gp.tileSize * 10; 
        g2.drawString(text, x, y); 
        if(commandNumber == 3){
            g2.drawString(">",x - gp.tileSize,y); 
        }
        
    }
    //--------------------------------------------------------------------------
    
    public void drawInventoryCase(int x, int y){
         
        int width = gp.tileSize * (1 + (1/2)); 
        int height = gp.tileSize * (1 + (1/2)) ; 
        
        Color c = new Color(0,0,0,210); // 220 is going to show the transparecy of the window  
        g2.setColor(c); 
        g2.fillRoundRect(x,y,width,height,35,35); 
        
        c = new Color(255,255,255); 
        g2.setColor(c); 
        g2.setStroke(new BasicStroke(5)); 
        g2.drawRoundRect(x+5, y+5, width-10, height-10 , 25, 25);
    }
    
     
    public void drawInventoryScreen(){
        
        // Hay que tener en cuenta que esta función se va a repetir constantemente 
        
        int x = gp.tileSize ; 
        int y = gp.tileSize / 2; 
        int width = gp.screenWidth - (gp.tileSize * 2); 
        int height = gp.tileSize * 11;
        int contador = 0; 
        
        drawSubWindow(x,y,width, height); 
        
        
        // Parte en la que añado a la vez que abro el inventario la colisión de las tiles 
        // y lo limito al tamaño de la pantalla 
        
        for(int i = 3; i <= 12 ; i++){
            for(int j = 2; j <= 9; j++){
                drawInventoryCase(gp.tileSize * i, gp.tileSize * j); 
            }
        }
        
        
        
        objetosUnicos = new LinkedHashSet<>(this.gp.player.inventario); 
        inventario = new ArrayList<>(objetosUnicos);
        //Map<String, Integer> cantidades = new HashMap<>(); 
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F)); 
        int xk = 3;
        int yk = 2; 

        for(int i = 0; i < inventario.size(); i++){
                contador = 0; 
            
                g2.drawImage(inventario.get(i).image, gp.tileSize * xk, gp.tileSize * yk, gp.tileSize, gp.tileSize , null); 

                //g2.drawString(inventario.get(i).name, x + (2 * gp.tileSize), y + gp.tileSize);
                
                for(int j = 0; j < gp.player.inventario.size(); j++){
                    if(gp.player.inventario.get(j).name.equals(gp.player.inventario.get(i).name)){
                        
                        contador++; 
                    }
                }
                
                g2.setFont(arial_40);
                g2.setColor(Color.red); 
                g2.drawString("x"+String.valueOf(contador), gp.tileSize * xk  ,gp.tileSize * (yk + 1) );
                xk++; 
                /*
                if(xk <= 12){
                    xk += gp.tileSize;
                }else{
                    xk = 0; 
                    yk += gp.tileSize; 
                }
                */
                
         
        }
        
    }
    
    
    // ------------------- Opciones gráficas del menú --------------------------
    
    public void drawTitleScreen(){
        
        if(titleScreenState == 0){

            g2.setColor(Color.black);  // Set the background color 
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            String text = "Pokemón Rojo Fuego"; 
            int x = getXForCenteredText(text); 
            int y = gp.tileSize * 3; 

            g2.setColor(Color.white); 
            g2.drawString(text,x+2,y+2); 
            g2.setColor(Color.red); // Set the text color 
            g2.drawString(text,x,y); 



            x = gp.screenWidth/2 - (gp.tileSize*2)/2 ; 
            y += gp.tileSize*2; 
            g2.drawImage(gp.obj[3].image, x, y, gp.tileSize*2, gp.tileSize * 2, null); 


            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            g2.setColor(Color.white);
            text = "NEW GAME"; 
            x = getXForCenteredText(text); 
            y = gp.tileSize * 8; 
            g2.drawString(text, x, y); 
            if(commandNumber == 0){
                g2.drawString(">",x - gp.tileSize,y); 
            }

            text = "LOAD GAME"; 
            x = getXForCenteredText(text); 
            y = gp.tileSize * 9; 
            g2.drawString(text, x, y); 
            if(commandNumber == 1){
                g2.drawString(">",x - gp.tileSize,y); 
            }

            text = "QUIT GAME"; 
            x = getXForCenteredText(text); 
            y = gp.tileSize * 10; 
            g2.drawString(text, x, y); 
            if(commandNumber == 2){
                g2.drawString(">",x - gp.tileSize,y); 
            }
        
        
        }else if(titleScreenState == 1){ // Seleccionar la clase del personaje 
            
            g2.setColor(Color.red); 
            g2.setFont(g2.getFont().deriveFont(42F));
            
           
            String text = "Select your class!"; 
            int x = getXForCenteredText(text); 
            int y = gp.tileSize * 3; 
            g2.drawString(text,x,y); 
            
            g2.drawString(text,x+2,y+2); 
            g2.setColor(Color.red); // Set the text color 
            g2.drawString(text,x,y); 
            
            text = "Gipsy"; 
            x = getXForCenteredText(text); 
            y += gp.tileSize * 3; 
            g2.drawString(text,x,y); 
            if(commandNumber == 0){
                g2.drawString(">", x - gp.tileSize, y); 
                gp.gameState = gp.playState; 
                gp.player.speed = 18; 
            }
            
            
            text = "Fighter"; 
            x = getXForCenteredText(text); 
            y += gp.tileSize  ; 
            g2.drawString(text,x,y); 
            if(commandNumber == 1){
                g2.drawString(">", x - gp.tileSize, y); 
            }
            
            
            
            text = "RRHH"; 
            x = getXForCenteredText(text); 
            y += gp.tileSize; 
            g2.drawString(text,x,y); 
            if(commandNumber == 2){
                g2.drawString(">", x - gp.tileSize, y); 
            }
            
            text = "BACK"; 
            x = getXForCenteredText(text); 
            y += gp.tileSize; 
            g2.drawString(text,x,y); 
            if(commandNumber == 3){
                g2.drawString(">", x - gp.tileSize, y); 
            }
            
        }
        
    }
    
    // -------------------------------------------------------------------------
    
    
    
    
    
    
}
