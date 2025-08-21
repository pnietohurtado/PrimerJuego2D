/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Conexion.Conexion;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pablo
 */
public class KeyHandler implements KeyListener{

    // Conexion para poder guardar la partida
    private Connection getConnection() throws SQLException{
        return Conexion.getConnection(); 
    }
    
    
    // ------------------------- Variables de esta clase -----------------------
    
    // Variables de movimiento del personaje
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    
    // Varible sobre Entorno
    public boolean showCollisions;
    public boolean drawTime;
    
    // Interacción con los objetos del mapa 
    public boolean interactEntity; 
    
    // Pausar el juego
    public boolean pauseGame = false; 
    
    // Mostrar objetos de el inventario
    public boolean showData; 
    
    public boolean rightCorner; // Para comprobar que no haya más de dos elementos en la parte superior derecha como los FPS y las coordenadas
    
    public boolean tileCollision; 
    
    public GamePanel gp; 
    
    public boolean playMusic ; // Para poder poner la música 
    
    public float hp_enemy ; 
    
    private Random random; 
    private int aleatorio ; 
    int posibilidad_de_captura; 
    
    // -------------------------------------------------------------------------
    
    
    // Contructor de la clase 
    public KeyHandler(GamePanel gp){
        this.gp = gp; 
        this.random = new Random(); 
        this.posibilidad_de_captura = 0; 
    }
    
    // NO TOCAR 
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); 
        
        // En el caso de que el juego este pausado podemos seguir pulsando teclas
        
        if (gp.gameState == gp.playState ){
                    if(code == KeyEvent.VK_W)
                {
                    this.upPressed = true; 
                }
                if(code == KeyEvent.VK_S)
                {
                    this.downPressed = true; 
                }
                if(code == KeyEvent.VK_A)
                {
                    this.leftPressed = true; 
                    /* Tried to make the player sprint when the key "SHIFT" is pressed
                    if(code == KeyEvent.VK_SHIFT){
                        System.out.println("FIUMMMMMM");
                        gp.player.speed = 17; 
                    }
                    */
                }
                if(code == KeyEvent.VK_D)
                {
                    this.rightPressed = true; 
                }if(code == KeyEvent.VK_K)
                {
                    if(showCollisions == false){
                        this.showCollisions = true; 
                    }else if(showCollisions == true){
                        this.showCollisions = false; 
                    }
                }  
                if(code == KeyEvent.VK_F3){
                    
                    if(showData == true){
                        showData = false; 
                    }else{
                        showData = true; 
                    }
                }
                if(code == KeyEvent.VK_ESCAPE) // In order to pause the game
                {
                    gp.sonido.volume = -10.0f; 
                    gp.sonido.play(3, false); // Para poder poner la música 
                    
                    gp.gameState = gp.pauseState; 

                }
                
                // ---------------- Cambiar al modo Dios -----------------------
                if(code == KeyEvent.VK_L){
                    if(gp.tileManager.hayColision == false){
                        gp.tileManager.hayColision = true; 
                    }else{
                        gp.tileManager.hayColision = false; 
                    }
                    gp.tileManager.getTileImage();
                    //System.out.println("Valor en GP: " + gp.tileManager.hayColision);
                   
                }

                if(code  == KeyEvent.VK_T)
                {
                    if(drawTime == false){
                        drawTime = true; 
                    }else if(drawTime == true){
                        drawTime = false; 
                    }
                }

                if(code == KeyEvent.VK_E){ // To catch any object on the floor 
                    this.interactEntity = true; 
                }

                // In order to enter the menu State 
                if(code == KeyEvent.VK_CONTROL){
                    gp.gameState = gp.titleState; 
                }
                
                if(code == KeyEvent.VK_I){
                    gp.gameState = gp.inventoryState; 
                }
                
                if(code == KeyEvent.VK_H && gp.gameState == gp.playState ){
                    if(tileCollision == false){
                        this.tileCollision = true; 
                    }else if(tileCollision == true){
                        this.tileCollision = false; 
                    }
                }
                
                if(code == KeyEvent.VK_ENTER){
                    if(enterPressed == false){
                        this.enterPressed = true; 
                    }else if(enterPressed == true){
                        this.enterPressed = false; 
                    }
                }
                
                if(code == KeyEvent.VK_Q){
                    gp.gameState = gp.teamPokemon; 
                }
        }
        
        
        
        
        
        
        
        // ----------------------- Battle --------------------------------------
        
        if(gp.gameState == gp.battleState){
            if(code == KeyEvent.VK_ESCAPE){
                gp.sonido.stop(5);
                gp.gameState = gp.playState; 
            }
            if(code == KeyEvent.VK_UP)
                {
                    gp.sonido.volume = -20.0f; 
                    gp.sonido.play(3,false); // Para poder poner la música 
                    
                    gp.ui.commandNumber--; 
                    if(gp.ui.commandNumber < 0){
                        gp.ui.commandNumber = 3; 
                    }
                }
            if(code == KeyEvent.VK_DOWN)
                {
                    gp.sonido.volume = -20.0f; 
                    gp.sonido.play(3,false); // Para poder poner la música 
                    
                    gp.ui.commandNumber++; 
                    if(gp.ui.commandNumber > 3){
                        gp.ui.commandNumber = 0; 
                    }

                }
                
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNumber == 0 && gp.turnos.battle_turn == true){
                    this.aleatorio = random.nextInt(1000) +1 ; 
                    
                    if(this.hp_enemy <= 0){
                        gp.sonido.stop(5); 
                        // Se encarga de actualizar la vida en el .txt 
                        gp.nombres.actualizar_vida_compañero();
                        gp.gameState = gp.playState; 
                        
                        gp.nombres.actualizar_xp("xp"); 
                        
                    }else if(this.aleatorio == 1){
                        gp.sonido.volume = -20.0f; 
                        gp.sonido.play(9, false);
                        hp_enemy -= 0.3f; 
                        // Se encarga de actualizar la vida en el .txt 
                        gp.nombres.actualizar_vida_compañero();
                    }else{
                        gp.sonido.volume = -20.0f; 
                        gp.sonido.play(8, false);
                        hp_enemy -= 0.2f; 
                        // Se encarga de actualizar la vida en el .txt 
                        gp.nombres.actualizar_vida_compañero();
                    }
                    gp.turnos.battle_turn = false; // Cambiar turno
                    
                    
                }else if(gp.ui.commandNumber == 1 && gp.turnos.battle_turn == true) {
                    
                    gp.turnos.battle_turn = false; // Cambiar turno
                    /*
                    // Seteamos la vida pokemon aliado; 
                    gp.nombres.cargar_vida_pokemon_actual(gp.equipo_pokemones.get(0).getNombre()); // Obtenemos vida maxima; 
                    gp.player.vida_pokemon_restante = (int) gp.player.vida_pokemon_compañero; // Ajustamos la vida
                    */
                }else if(gp.ui.commandNumber == 2 && gp.turnos.battle_turn == true){
                    
                    if(gp.player.hasPokeball > 0){
                        this.posibilidad_de_captura = random.nextInt((int)(gp.ui.lvl * (this.hp_enemy + 1) ) ) + 1; 
                        
                        if(this.posibilidad_de_captura == 1){
                            String nombre = null; 
                            nombre = JOptionPane.showInputDialog("Elige un nombre"); 
                            gp.nombres.cargar_pokemon_capturado(nombre); 
                        }
                        
                        gp.player.hasPokeball--; 
                    }else{
                        System.out.println("No tienes pokeballs");
                    }
                    /*
                    // Seteamos la vida pokemon aliado; 
                    gp.nombres.cargar_vida_pokemon_actual(gp.equipo_pokemones.get(0).getNombre()); // Obtenemos vida maxima; 
                    gp.player.vida_pokemon_restante = (int) gp.player.vida_pokemon_compañero; // Ajustamos la vida
                    */
                    gp.turnos.battle_turn = false; // Cambiar turno
                    
                }else if(gp.ui.commandNumber == 3 && gp.turnos.battle_turn == true){
                    gp.sonido.stop(5);
                    gp.gameState = gp.playState; 
                    /*
                    // Seteamos la vida pokemon aliado; 
                    gp.nombres.cargar_vida_pokemon_actual(gp.equipo_pokemones.get(0).getNombre()); // Obtenemos vida maxima; 
                    gp.player.vida_pokemon_restante = (int) gp.player.vida_pokemon_compañero; // Ajustamos la vida
                    */
                    gp.turnos.battle_turn = false; // Cambiar turno
                }
            }
            
        }
        
        
        
        
        
        
        // ------------------------ Pause Game ---------------------------------
        
        if(gp.gameState == gp.pauseState){
            
            
            if(code == KeyEvent.VK_W ||code == KeyEvent.VK_UP){
                gp.sonido.volume = -20.0f; 
                gp.sonido.play(3,false); // Para poder poner la música 
                
                gp.ui.commandNumber--; 
                if(gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = 4; 
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.sonido.volume = -20.0f; 
                gp.sonido.play(3,false); // Para poder poner la música 
                
                gp.ui.commandNumber++; 
                if(gp.ui.commandNumber > 4){
                    gp.ui.commandNumber = 0; 
                }
            }
            
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNumber == 0){
                    
                    try { 
                        BufferedWriter bw = new BufferedWriter(new FileWriter("data_game.txt", false));
                        String linea =gp.player.worldX + " " + gp.player.worldY + " " + gp.player.pokemon_inicial + " " + gp.player.dineroPlayer + " " +  gp.player.hasPokeball
                                + " " + gp.player.hasKey; 
                        bw.write(linea);
                        bw.newLine(); 
                        bw.close();
                          
                    } catch (IOException ex) {
                        Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    gp.guardado.cargar_objetos();
                    
                    gp.gameState = gp.playState; 
                }else if(gp.ui.commandNumber == 1){
                    gp.gameState = gp.playState; 
                }else if(gp.ui.commandNumber == 2){
                    if(this.playMusic == false){
                        gp.sonido.play(1,true);
                        gp.sonido.volume = -20.0f; 
                        this.playMusic = true; 
                    }else if(this.playMusic == true){
                        gp.sonido.stop(1);
                        this.playMusic = false; 
                    }
                }else if(gp.ui.commandNumber == 3){
                    System.exit(0); 
                }
            }
            
        }
        
    
        
        
        
        
        // ------------------------ Dialogue State -----------------------------
        if(gp.gameState == gp.dialogueState){
            this.showData = false; // Para que los datos de las coordenadas no interrumpan el texto del dialogo
            if(code == KeyEvent.VK_ENTER){
                //gp.sonido.stop();
                gp.gameState = gp.playState; 
            }
        }
        
        
        
        
        // ------------------------ Inventario ---------------------------------
        if(gp.gameState == gp.inventoryState){
            
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState; 
            }
            
            if(code == KeyEvent.VK_ENTER ){
                gp.gameState = gp.playState; 
            }
        }
        
        
        
        
        
        
        
        
        
        // ------------------------ Title statement ----------------------------
        
        if(gp.gameState == gp.titleState){
            
            if(gp.ui.titleScreenState == 0){

                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
                {
                    gp.sonido.volume = -20.0f; 
                    gp.sonido.play(3,false); // Para poder poner la música 
                    
                    gp.ui.commandNumber--; 
                    if(gp.ui.commandNumber < 0){
                        gp.ui.commandNumber = 0; 
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
                {
                    gp.sonido.volume = -20.0f; 
                    gp.sonido.play(3,false); // Para poder poner la música 
                    
                    gp.ui.commandNumber++; 
                    if(gp.ui.commandNumber > 0){
                        gp.ui.commandNumber = 0; 
                    }

                }

                if(code == KeyEvent.VK_ENTER){
                    
                    if(gp.ui.commandNumber == 0){
                        gp.sonido.stop(7);
                        gp.ui.titleScreenState = 1; 
                        
                    }else if(gp.ui.commandNumber == 1){
                        System.exit(0); 
                    }
                }
            }else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
                {
                    gp.sonido.volume = -10.0f; 
                    gp.sonido.play(3,false); // Para poder poner la música 
                    
                    gp.ui.commandNumber--; 
                    if(gp.ui.commandNumber < 0){
                        gp.ui.commandNumber = 1; 
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
                {
                    gp.sonido.volume = -10.0f; 
                    gp.sonido.play(3,false); // Para poder poner la música 
                    
                    gp.ui.commandNumber++; 
                    if(gp.ui.commandNumber > 1){
                        gp.ui.commandNumber = 0; 
                    }

                }

                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNumber == 0){
                        try { 
                            BufferedReader br = new BufferedReader( new FileReader("data_game.txt"));
                            String linea; 
                            
                            while((linea = br.readLine()) != null){
                                String partes[] = linea.split(" "); 
                                gp.player.worldX = Integer.parseInt(partes[0]); 
                                gp.player.worldY = Integer.parseInt(partes[1]); 
                                gp.player.pokemon_inicial = Boolean.parseBoolean(partes[2]); 
                                gp.player.dineroPlayer = Integer.parseInt(partes[3]); 
                                if(Integer.parseInt(partes[4]) > 0){
                                    gp.player.hasPokeball = Integer.parseInt(partes[4]); 
                                    gp.player.inventario.add(gp.object[1]); 
                                }
                                if(Integer.parseInt(partes[5]) > 0){
                                    gp.player.hasKey = Integer.parseInt(partes[5]); 
                                    gp.player.inventario.add(gp.object[2]); 
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        gp.nombres.cargar_pokemones_equipo();
                        gp.guardado.poner_objetos();
                        gp.sonido.play(1, true);
                        gp.gameState = gp.playState;
                    }else if(gp.ui.commandNumber == 1){
                        gp.equipo_pokemones.clear(); // No tiene pokemones 
                        gp.aSetter.setObject(); // Setteamos todos los objetos 
                        gp.cargar_random.cargar_pokemoenes_random(); // Vamos a poner todos los pokemons de ruta random 
                        gp.sonido.play(1, true);
                        gp.gameState = gp.playState; 
                        
                    }
                }
            }
        }
        
        
        // ----------------------------- Menu Shop -----------------------------
        if(gp.gameState == gp.shopMenu){
            
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState; 
            }
            
            if(code == KeyEvent.VK_W ||code == KeyEvent.VK_UP){
                gp.sonido.volume = -20.0f; 
                gp.sonido.play(3,false); // Para poder poner la música 
                
                gp.ui.commandNumber--; 
                if(gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = 3; 
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.sonido.volume = -20.0f; 
                gp.sonido.play(3,false); // Para poder poner la música 
                
                gp.ui.commandNumber++; 
                if(gp.ui.commandNumber > 3){
                    gp.ui.commandNumber = 0; 
                }
            }
            
            if(code == KeyEvent.VK_ENTER){
                gp.sonido.play(2, false);
                if(gp.ui.commandNumber == 0 && gp.player.dineroPlayer >= 300){
                    gp.player.hasPokeball++; 
                    gp.player.dineroPlayer = gp.player.dineroPlayer - 300; 
                    gp.player.inventario.add(gp.object[1]); 
                    //System.out.println("Pokeball " + gp.player.hasPokeball);
                }else if(gp.ui.commandNumber == 0 && gp.player.dineroPlayer < 300){
                    System.out.println("No tienes suficiente dinero");
                } 
                if(gp.ui.commandNumber == 1 && gp.player.dineroPlayer >= 100){
                    gp.player.hasKey++; 
                    gp.player.dineroPlayer = gp.player.dineroPlayer - 100; 
                    gp.player.inventario.add(gp.object[2]); 
                }else if(gp.ui.commandNumber == 1 && gp.player.dineroPlayer < 100){
                    System.out.println("No tienes suficiente dinero");
                }
                if(gp.ui.commandNumber == 2 && gp.player.dineroPlayer >= 1000){
                    gp.player.hasMTHp++; 
                    gp.player.dineroPlayer = gp.player.dineroPlayer - 1000; 
                    gp.player.inventario.add(gp.object[3]); 
                }else if(gp.ui.commandNumber == 2 && gp.player.dineroPlayer < 1000){
                    System.out.println("No tienes suficiente dinero");
                }
                if(gp.ui.commandNumber == 3 && gp.player.dineroPlayer >= 1000){
                    gp.player.hasMTAttack++; 
                    gp.player.dineroPlayer = gp.player.dineroPlayer - 1000; 
                    gp.player.inventario.add(gp.object[4]); 
                }else if(gp.ui.commandNumber == 3 && gp.player.dineroPlayer < 1000){
                    System.out.println("No tienes suficiente dinero");
                }
            }
            
        }
        
        
        
        if(gp.gameState == gp.teamPokemon){
            if(code == KeyEvent.VK_ESCAPE ){
                gp.gameState = gp.playState; 
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode(); 
        
        
        if(code == KeyEvent.VK_W)
        {
            this.upPressed = false; 
        }
        if(code == KeyEvent.VK_S)
        {
            this.downPressed = false; 
        }
        if(code == KeyEvent.VK_A)
        {
            this.leftPressed = false; 
        }
        if(code == KeyEvent.VK_D)
        {
            this.rightPressed = false; 
        }
        if(code == KeyEvent.VK_E){
            this.interactEntity = false; 
        }
    }
    
    
    
}
