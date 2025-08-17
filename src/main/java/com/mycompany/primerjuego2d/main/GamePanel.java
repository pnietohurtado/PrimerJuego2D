/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Battle.Battle;
import CreacionDeSubVentanas.SubWindow;
import Entity.Entity;
import Entity.Player;
import Funciones.CargarDatosPokemon;
import Funciones.Funciones_sobre_tiles;
import Funciones.GuardadoDeLosObjetos;
import Objects.SuperObject;
import Pokemon.Pokemon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import tile.TileManager;

/**
 *
 * @author pablo
 */
public class GamePanel extends JPanel implements Runnable{
    
    // -------------------------------------------------------------------------
    
    // SCREEN SETTINGS (NO TOCAR) 
    final int originalTileSize = 16; // 16x16 Tile (player size) (and any NPC) 
    final int scale = 3; // It in fact looks like 48x48 
    
    public final int tileSize = originalTileSize * scale; // Actual Tile size 48x48
    public final int maxScreenCol = 16; 
    public final int maxScreenRow = 12; 
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels 
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels 
    
    // World settings 
    public int maxWorldCol;
    public int maxWorldRow; 
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeigth = tileSize * maxWorldRow;
    
    // Skin del jugador 
    public int skinAppereance = 0; 
    
    // FPS 
    final int FPS = 60;
  
    // -------------------------------------------------------------------------
    
    // ------------------- Variable para el sonido -----------------------------
    
    public Sound sonido = new Sound(); 
    
    // ---------- Variable donde cargamos los nombres de los pokemones ---------
    
    public CargarDatosPokemon nombres = new CargarDatosPokemon(this); 
    public String[] nombres_pokemon = new String[152]; 
    
    // --------- Variables sobre el equipo pokemon -----------------------------
    
    public ArrayList<Pokemon> equipo_pokemones = new ArrayList<>(); 
    
    
    // ------------------- Variables generales (NO TOCAR) ----------------------
    
    // Variables para el inventario 
    public boolean showInventory = false; 
    
    //Instanciate the keyHandler 
    public KeyHandler keyHandler = new KeyHandler(this); 
    
    // Añadimos un Lector de "Mouse"
    MouseClicker mClicker = new MouseClicker(this); 
    
    //Incatnce of the tile
    public TileManager tileManager = new TileManager(this, keyHandler); 
    
    // Here's de "Game Clock" 
    Thread gameThread; 
    
    // Entity of the player 
    public Player player = new Player(this, keyHandler); 
    
    // Collisions
    public CollisionChecker cH = new CollisionChecker(this); 
    
    // Interactuable objects
    public SuperObject obj[] = new SuperObject[100]; 
    
    public AssetSetter aSetter = new AssetSetter(this); 
    
    public AssetObject aObjects = new AssetObject(this); 
    
    public SuperObject object[] = new SuperObject[100]; 

    // Instance UI class 
    public UI ui = new UI(this); 
    
    // NPC 
    public Entity[] npc = new Entity[999]; 
    
    // Aquí vamos a cargar las funciones que queremos que lleven a cabo alguna acción 
    public Funciones_sobre_tiles fst = new Funciones_sobre_tiles(this); 
    
    // Cargar los turnos de las batalla pokemon 
    public Battle turnos = new Battle(this); 
    
    // Guardar los objetos 
    public GuardadoDeLosObjetos guardado = new GuardadoDeLosObjetos(this); 
    
    // -------------------------------------------------------------------------
    
    
    
    // GameState 
    public int gameState; // Estado en el que se puede encontrar el juego
    public final int titleState = 0; // Opciones del menú 
    public final int playState = 1; // Modo normal del juego 
    public final int pauseState = 2; // Pausar el estado del juego
    public final int dialogueState = 3; // Opciones de dialogo dentro del juego
    public final int inventoryState = 4; // Mostrar el inventario del jugador
    public final int battleState = 5; // Luchar con los pokemons 
    public final int shopMenu = 6; // Menu de la tienda 
    public final int teamPokemon = 7; // Mostrar equipo pokemon 
    
    
    // -------------------------------------------------------------------------
    
    
    // Constructor of our game panel 
    
    private BufferedImage backGroundImage = null; 
    
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.black); 
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyHandler);
        this.addMouseListener(mClicker);
        this.setFocusable(true);
        
        /*
        try {
            // Cargar el fondo de pantalla
            backGroundImage = ImageIO.read(getClass().getResourceAsStream("/Fondo/Fondo_Ataque.png"));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
    
    // -------------------------------------------------------------------------
    
    
    
    public void setUpGame()
            /*To print the object in the map*/
    {
        aSetter.setObject();
        aSetter.setNPC();
        aObjects.setObject();
        
        
        // Poner la música del principio 
        this.keyHandler.playMusic = true; 
        sonido.play(7, true); // Se pone la música de la intro (Main Theme) 
        
        // Cargar los nombres de los pokemones 
        nombres.cargar();
        nombres.cargar_pokemones_equipo();
        
        gameState = titleState; 
    }
    
    public void startGameThread() 
    {
        gameThread = new Thread(this); // Le estamos pasando esta clase como un runnable 
        gameThread.start(); 
    }
    
    @Override 
    public void run(){
        double drawInterval = 1000000000/FPS;  // 0.01666 seconds prints 
        double delta = 0; 
        long lastTime = System.nanoTime(); 
        long currentTime; 
        
        // Variable to count the frame per second to ensure it is 60. 
        long timer = 0; 
        int drawCount = 0; 
        
        // Here we will be initializing our game loop
        while(gameThread != null) // Will execute until we close the gamePanel. 
        {
            // System.out.println("Nano time: "+ System.nanoTime()); It returns the time of each nano time of the game running. 
            currentTime = System.nanoTime(); 
            
            delta += (currentTime - lastTime) / drawInterval; 
            timer += (currentTime - lastTime); 
            lastTime = currentTime; 
            
            if(delta >= 1){
                update(); 
                repaint();  
                delta--; 
                drawCount++; 
            }
            
            if(timer >= 1000000000){
                /*
                if(keyHandler.showFPS == true){
                    System.out.println("FPS:"+drawCount);
                }*/
                drawCount = 0; 
                timer = 0; 
            }
            //System.out.println(hayColision)
            //System.out.println(npc);
            
            
        }
    }
    
    public void update () 
    {
        
        if(gameState == playState){
            player.update();
            
            
            for(int i = 0; i < npc.length ; i++){
                if(npc[i] != null){
                    
                    npc[i].update(); 
                   
                }
            }
        }else if(gameState == pauseState){
            // El juego permanece parado 
        }
        
     
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // This way we could update the drawing as the player moves 
        
        
        
        Graphics2D g2 = (Graphics2D)g; // Add functions to the game
       
        
        
        
        // Title Screen 
        if(gameState == titleState){
            
            ui.draw(g2);
        }
        else{
                //Object 
            tileManager.draw(g2); // It's importante that the background comes before the player 
            for(int i = 0; i < obj.length ; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this); 
                }
            }
            
                //NPC 
            for(int i = 0; i < npc.length ; i++){
                if(npc[i] != null){
                    npc[i].draw(g2); 
                }
            }



            //Player 
            player.draw(g2); 
            mClicker.draw(g2);
            ui.draw(g2);
            
        
        }
        
        

        //Debug
        long drawStart = 0; 
        if(keyHandler.drawTime == true){
            drawStart = System.nanoTime();
        }
        
        if(showInventory == true){
            g2.setColor(Color.white); 
            g2.setFont(new Font("Serif", Font.PLAIN, 24)); 
            g2.drawString(player.inventario.toString(), 350, 65); 
        }
        
        if(keyHandler.showData == true){
            // Enseñamos los FPS del juego
            g2.setColor(Color.white); 
            g2.setFont(new Font("Serif", Font.PLAIN, 19)); 
            g2.drawString("FPS : "+FPS, 650, 50); 
            
            // Enseñamos la posición del jugador en todo el mapa
            g2.setColor(Color.white); 
            g2.setFont(new Font("Serif", Font.PLAIN, 19)); 
            g2.drawString("X : "+(player.worldX / this.tileSize)+ " Y : " + (player.worldY / this.tileSize), 650, 65); 
        }
        
        if(keyHandler.drawTime == true){
            long drawEnd = System.nanoTime(); 
            long passed = drawEnd - drawStart; 
            g2.setColor(Color.white); 
            g2.drawString("Draw Time : "+ passed, 10, 400); 
            System.out.println("Draw Time : "+ passed);
        }
        
        
        g2.dispose(); // Dispose the graphic context release any sys resource using 
    }
    
    

}
