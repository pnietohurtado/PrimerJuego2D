/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import CreacionDeSubVentanas.SubWindow;
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
    private Random random;  
    private int aleatorio; 
    
    public int lvl; // Para poder darle un "level" específico a un pokemon 
    
    private BufferedImage image; 
    int x; 
    int y; 
    
    Color c1; 
    Color c2; 
    
    public SubWindow sb ; 
    
    // --------------------- Constructor de la clase ---------------------------
    
    
    public UI(GamePanel gp){
        this.gp = gp; 
        
        random = new Random(); 
        
        this.x = 0; 
        this.y = 0; 
        
        arial_40 = new Font("Arial", Font.PLAIN, 20); 
        c1 = new Color(0,0,0); 
        c2 = new Color(0,0,0);
        
        
    }
    
    // -------------------------- Funciones de  asistencia ---------------------
    
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
            sb = new SubWindow(gp,g2); 
            drawPauseScreen(); 
        }else if(gp.gameState == gp.titleState){
            sb = new SubWindow(gp,g2); 
            drawTitleScreen(); 
        }else if(gp.gameState == gp.dialogueState){
            sb = new SubWindow(gp,g2); 
            sb.drawDialogueSubWindow(this.message);
        }else if(gp.gameState == gp.inventoryState){
            sb = new SubWindow(gp,g2); 
            drawInventoryScreen(); 
        }else if(gp.gameState == gp.battleState){
            sb = new SubWindow(gp,g2); 
            battleScreen(); 
        }else if(gp.gameState == gp.shopMenu){
            sb = new SubWindow(gp,g2); 
            shopScreen(); 
        }else if(gp.gameState == gp.teamPokemon){
            sb = new SubWindow(gp,g2); 
            pokemonTeam(); 
        }
    }
    
    // ------------------------- Cambio a escena de batallas -------------------
    
    
    
    
    public void battleScreen(){
        this.x = gp.tileSize * 2; 
        this.y = gp.tileSize / 2; 
       
        
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
        
        c1 = new Color(247, 239, 163);
        c2 = new Color(57, 97, 71);
        sb.SubWindow(x,y,width, height,c1,c2); 
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F)); 
        this.x += gp.tileSize; 
        this.y += gp.tileSize; 
        g2.drawString(gp.nombres_pokemon[gp.player.sprite_bicho_attack], x, y); 
        g2.drawString(" lvl. " + String.valueOf(this.lvl), x * 3, y); 
        
        
        if(gp.turnos.battle_turn == false){
            this.aleatorio = random.nextInt(1000) + 1; 
            if(this.aleatorio == 1 && gp.player.vida_pokemon_restante > 0){
                gp.sonido.play(9, false, "effect"); 
                gp.player.vida_pokemon_restante -= 3; 
                
                // Se encarga de actualizar la vida en el .txt 
                gp.nombres.actualizar_vida_compañero(1);
            }else if(gp.player.vida_pokemon_restante > 0){
                gp.sonido.play(8, false, "effect");
                gp.player.vida_pokemon_restante -= 1; 
                
                // Se encarga de actualizar la vida en el .txt 
                gp.nombres.actualizar_vida_compañero(1);
            }else{
                // Se encarga de actualizar la vida en el .txt 
                gp.nombres.actualizar_vida_compañero(1);
                
                // Encargado de limpiar el array y cargarlo de nuevo con los nuevos valores 
                gp.equipo_pokemones.clear(); 
                gp.nombres.cargar_pokemones_equipo(); 
                
                gp.sonido.stop(5);
                gp.gameState = gp.playState; 
            }
            gp.turnos.battle_turn = true; 
        }
        
        
        sb.healthBar(x , y + gp.tileSize, gp.player.vida_enemigo_restante / gp.player.vida_enemigo, "enemy"); // Barra de vida de los pokemones enemigos
        
        // ------------------------ Compañero de batalla -----------------------
        
        this.x = gp.tileSize; 
        this.y = gp.tileSize / 2; 
        
        gp.npc[997] = new NPC_Pokemon(gp, gp.equipo_pokemones.get(0).getPokedex()); 
        g2.drawImage(gp.npc[997].f1, x + (gp.tileSize * 3), y + (gp.tileSize * 5), gp.tileSize*4, gp.tileSize * 4, null);
        
        width = gp.screenWidth - (gp.tileSize * 8); 
        height = gp.tileSize * 2;
        
        c1 = new Color(247, 239, 163);
        c2 = new Color(57, 97, 71);
        sb.SubWindow(x * 8 ,y * 12,width, height,c1,c2); 
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F)); 
        this.x += gp.tileSize; 
        this.y += gp.tileSize; 
        g2.drawString(gp.equipo_pokemones.get(0).getNombre(), x * 5, y * 5); 
        g2.drawString(" lvl. " + gp.player.lvl_compero, x * 7, y * 5 ); 
        
        
        sb.healthBar(x * 5 , y * 5, gp.player.vida_pokemon_restante / gp.player.vida_pokemon_compañero, "ally"); // Barra de vida de los pokemones enemigos 
                                    // vida que le queda                    // vida maxima 
        
      
        
        
        
        // ------------------ Menu dentro de la batalla ------------------------
        
        this.x = gp.tileSize; 
        this.y = gp.tileSize / 2; 
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla1.png"));
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        
        
        if(commandNumber == 0 ){
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla2.png"));
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        if(commandNumber == 1){
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla3.png"));
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        if(commandNumber == 2 ){
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla4.png"));
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        if(commandNumber == 3){
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Menu/MenuBatalla5.png"));
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        g2.drawImage(image, x - (gp.tileSize * (1 + (1/2))), y + (gp.tileSize * 8), gp.tileSize* (16 + 1/2), gp.tileSize * 4, null); 
        
        this.x = gp.tileSize; 
        this.y = gp.tileSize / 2; 
        
        g2.setColor(Color.WHITE); 
        g2.drawString("Que va a hacer"+" "+gp.equipo_pokemones.get(0).getNombre(), x , y * 21); 
        
        
        // ---------------------------------------------------------------------
    }
   
  
    
    
    // ----------------------- Pausa del juego con un dialogo ------------------
    public void drawPauseScreen(){
        this.x = gp.tileSize * 3; 
        this.y = gp.tileSize / 2; 
        int width = gp.screenWidth - (gp.tileSize * 6); 
        int height = gp.tileSize * 10;
        String text = ""; 
        
        c1 = new Color(0, 0,0);
        c2 = new Color(135, 206, 250);
        sb.SubWindow(x, y, width, height, c1, c2);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F)); 
        x += gp.tileSize * 0.5; 
        y += gp.tileSize; 
        g2.drawString("Juego Pausado" , x, y);

        text = "SAVE"; 
        x = getXForCenteredText(text) - (gp.tileSize * 2); 
        y = gp.tileSize * 4; 
        g2.drawString(text, x, y); 
        if(commandNumber == 0){
            g2.drawString(">",x - gp.tileSize,y); 
        }

        text = "BACK"; 
        x = getXForCenteredText(text) - (gp.tileSize * 2); 
        y = gp.tileSize * 5; 
        g2.drawString(text, x, y); 
        if(commandNumber == 1){
            g2.drawString(">",x - gp.tileSize,y); 
        }
        
        text = "MUSIC Vol."; 
        x = getXForCenteredText(text) - (gp.tileSize * 2); 
        y = gp.tileSize * 6; 
        g2.drawString(text, x, y); 
        sb.volumeBar(x + (gp.tileSize * 4), y, gp.sonido.volumeMusic);
        if(commandNumber == 2){
            g2.drawString(">",x - gp.tileSize,y); 
        }
        
        text = "EFFECT Vol."; 
        x = getXForCenteredText(text) - (gp.tileSize * 2); 
        y = gp.tileSize * 7; 
        g2.drawString(text, x, y); 
        sb.volumeBar(x + (gp.tileSize * 4), y, gp.sonido.volumeEffect);
        if(commandNumber == 3){
            g2.drawString(">",x - gp.tileSize,y); 
        }
        
        text = "QUIT GAME"; 
        x = getXForCenteredText(text) - (gp.tileSize * 2); 
        y = gp.tileSize * 8; 
        g2.drawString(text, x, y); 
        if(commandNumber == 4){
            g2.drawString(">",x - gp.tileSize,y); 
        }
        
    }
    // ------------------------Dibujar Inventario ------------------------------
    
    
     
    public void drawInventoryScreen(){
        
        // Hay que tener en cuenta que esta función se va a repetir constantemente 
        
        this.x = gp.tileSize ; 
        this.y = gp.tileSize / 2; 
        int width = gp.screenWidth - (gp.tileSize * 2); 
        int height = gp.tileSize * 11;
        
        
        c1 = new Color(0, 0,0);
        c2 = new Color(135, 206, 250);
        sb.SubWindow(x, y, width, height, c1, c2);
        
        
        // Parte en la que añado a la vez que abro el inventario la colisión de las tiles 
        // y lo limito al tamaño de la pantalla 
        c1 = new Color(0,0,0,210);
        c2 = new Color(135,206,250); 
        
        for(int i = 3; i <= 12 ; i++){
            for(int j = 2; j <= 9; j++){
                sb.drawInventoryCase(gp.tileSize * i, gp.tileSize * j, c1, c2); 
            }
        }
        
        objetosUnicos = new LinkedHashSet<>(this.gp.player.inventario); 
        inventario = new ArrayList<>(objetosUnicos);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F)); 
        
        int xk = 3;
        int yk = 2;
        
        for(int i = 0; i < inventario.size(); i++){
                    g2.drawImage(inventario.get(i).image, gp.tileSize * xk, gp.tileSize * yk, gp.tileSize, gp.tileSize , null); 

                    g2.setFont(arial_40);
                    g2.setColor(Color.WHITE);

                    switch(inventario.get(i).name){
                        case "Pokeball" : 
                            g2.drawString("x"+gp.player.hasPokeball, gp.tileSize * xk  ,gp.tileSize * (yk + 1) );
                            break; 
                        case "Llave": 
                            g2.drawString("x"+gp.player.hasKey, gp.tileSize * xk  ,gp.tileSize * (yk + 1) );
                            break; 
                        case "Hacha": 
                            break; 
                        case "MTHp": 
                            g2.drawString("x"+gp.player.hasMTHp, gp.tileSize * xk  ,gp.tileSize * (yk + 1) );
                            break; 
                        case "MTAttack": 
                            g2.drawString("x"+gp.player.hasMTAttack, gp.tileSize * xk  ,gp.tileSize * (yk + 1) );
                            break; 
                            
                    }
                    
                    xk++; 
        }
    }
    
    
    
    
    
    
    // ------------------- Opciones gráficas del menú --------------------------
    
    private BufferedImage backgroundImage = null;
    
    public void drawTitleScreen(){
        
        if(titleScreenState == 0){
            
            
            // ------------------------- Fondo de pantalla del inicio ----------
            try{

                backgroundImage = ImageIO.read(getClass().getResource("/Fondo/Pokémon_Esmeralda.png"));

            }catch(IOException e){

            }
            
            g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null); 
            
            
            
        
        }else if(titleScreenState == 1){ // Seleccionar la clase del personaje 
            
            
            
            
            
            if(commandNumber == 0){
                try{
                    backgroundImage = ImageIO.read(getClass().getResource("/Fondo/Partida_Nueva.png"));
                }catch(IOException e){
                }
                g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);  
            }
            if(commandNumber == 1){
                try{
                    backgroundImage = ImageIO.read(getClass().getResource("/Fondo/Fondo_Opciones.png"));
                }catch(IOException e){
                }
                g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);  
            }
            
            
        }

            
        
    }
    
    // -------------------------------------------------------------------------
    
    public String item = ""; 
    
    public void shopScreen(){
        
        x = gp.tileSize  ; 
        y = gp.tileSize / 2; 
        int width = (gp.tileSize *6); 
        int height = gp.tileSize * 11;
        
        c1 = new Color(0, 0,0);
        c2 = new Color(135, 206, 250);
        sb.SubWindow(x, y, width, height, c1, c2);
        
        this.item = "Dinero Jugador: "; 
        x = gp.tileSize * 2; 
        y = gp.tileSize * 2; 
        g2.drawString(this.item, x, y); 
        g2.drawString(String.valueOf(gp.player.dineroPlayer), x + (gp.tileSize * 3),y); 
        
        this.item = "Pokeball"; 
        x = gp.tileSize * 3; 
        y = gp.tileSize * 3; 
        g2.drawString(this.item, x , y); 
        g2.drawString("$300", x + (gp.tileSize * 2), y); 
        if(commandNumber == 0){
            g2.drawString(">", x - gp.tileSize, y); 
        }
        
        this.item = "Llave"; 
        x = gp.tileSize * 3; 
        y = gp.tileSize * 4; 
        g2.drawString(this.item, x, y);
        g2.drawString("$100", x + (gp.tileSize * 2), y); 
        if(commandNumber == 1){
            g2.drawString(">", x - gp.tileSize, y); 
        }
        
        this.item = "MT01"; 
        x = gp.tileSize * 3; 
        y = gp.tileSize * 5; 
        g2.drawString(this.item, x, y);
        g2.drawString("$1000", x + (gp.tileSize * 2), y); 
        if(commandNumber == 2){
            g2.drawString(">", x - gp.tileSize, y); 
        }
        
        this.item = "MT02"; 
        x = gp.tileSize * 3; 
        y = gp.tileSize * 6; 
        g2.drawString(this.item, x, y);
        g2.drawString("$1000", x + (gp.tileSize * 2), y); 
        if(commandNumber == 3){
            g2.drawString(">", x - gp.tileSize, y); 
        }
        
    }
    
    
    
    
    
    // --------------------- Ventana de el equipo pokemon -------------------
    
    public void pokemonTeam(){
        
        x = gp.tileSize  ; 
        y = gp.tileSize / 2; 
        int width = (gp.tileSize *6); 
        int height = gp.tileSize * 11;
        
        c1 = new Color(0, 0,0);
        c2 = new Color(135, 206, 250);
        sb.SubWindow(x, y, width, height, c1, c2);
        
        float vida; 
        
        for(int i = 0; i < gp.equipo_pokemones.size(); i++){
            
            vida = gp.equipo_pokemones.get(i).getHP(); 
            
            gp.npc[997] = new NPC_Pokemon(gp, gp.equipo_pokemones.get(i).getPokedex()); 
            g2.drawImage(gp.npc[997].f1, x, y, null); 
            
            // -- Cambio de color respeco a la vida 
            
            if(vida <= 0){
                g2.setColor(Color.RED);
                g2.drawString(gp.equipo_pokemones.get(i).getNombre(), x + (gp.tileSize * 2), y + gp.tileSize);
                g2.drawString(String.valueOf(gp.equipo_pokemones.get(i).getHP()), x + (gp.tileSize * 4), y + gp.tileSize);
                g2.drawString(String.valueOf(gp.equipo_pokemones.get(i).getId()), x + (gp.tileSize * 5), y + gp.tileSize);
            }else if(vida< (gp.player.vida_pokemon_compañero / 2)){
                g2.setColor(Color.ORANGE); 
                g2.drawString(gp.equipo_pokemones.get(i).getNombre(), x + (gp.tileSize * 2), y + gp.tileSize);
                g2.drawString(String.valueOf(gp.equipo_pokemones.get(i).getHP()), x + (gp.tileSize * 4), y + gp.tileSize);
                g2.drawString(String.valueOf(gp.equipo_pokemones.get(i).getId()), x + (gp.tileSize * 5), y + gp.tileSize);
            }else{
                g2.drawString(gp.equipo_pokemones.get(i).getNombre(), x + (gp.tileSize * 2), y + gp.tileSize);
                g2.drawString(String.valueOf(gp.equipo_pokemones.get(i).getHP()), x + (gp.tileSize * 4), y + gp.tileSize);
                g2.drawString(String.valueOf(gp.equipo_pokemones.get(i).getId()), x + (gp.tileSize * 5), y + gp.tileSize);
            }
            g2.setColor(c2); 
            
            // --
             
            y = gp.tileSize * (i + 2); 
                    
        }
    }
    
}
