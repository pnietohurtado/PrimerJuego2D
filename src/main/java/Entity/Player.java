/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Objects.SuperObject;
import java.awt.image.BufferedImage;
import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
/**/
/**
 *
 * @author pablo
 */
public class Player extends Entity{
    
    KeyHandler keyHandler; 
    Random random = new Random(); 
    
    // ---------------------- Sobre los sprites en las batallas ----------------
    
    public int sprite_bicho_attack = 1; 
    
    // ---------------------- Vida del pokemon compañero -----------------------
    
    public float vida_pokemon_compañero = 0; 
    public int vida_pokemon_restante; 
    public float vida_enemigo = 0; // Vida Maxima 
    public int vida_enemigo_restante; 
    
    // --- 
    
    public int lvl_compero = 0; 
    public int seleccion_pokemon = 0; 
    
    // -------------------------------------------------------------------------
    
    // Indice del objeto que tenemos 
    public int objIndex; 
    
    private int actionLocker = 0; // Para controlar el numero de veces que se ejecuta una acción 
    
    public final int screenX;
    public final int screenY; 
    
    //public ArrayList<Integer> cantidades;
    public ArrayList<SuperObject> inventario; 
    
    // --------------------------- Variables iniciales de jugador --------------
    
    public boolean pokemon_inicial; // Si es false, es que no se ha elegido inicial 
    public int dineroPlayer; // Dinero del jugador
    public int hasPokeball = 0; 
    public int hasKey = 0; 
    public boolean changeSkin; // En caso de que haya un cambio
    public int hasMTHp = 0; 
    public int hasMTAttack = 0; 
     public int hasHacha = 0; 
    
    // --------------------------- Constructor de la clase ---------------------
    
    public Player(GamePanel gp, KeyHandler kh){
        super(gp); 
        
        this.keyHandler = kh;  
        
        inventario = new ArrayList<>(); 
        
        // -- Valores iniciales 
        this.pokemon_inicial = false; 
        this.dineroPlayer = 4000; 
        
        // -- Colisiones y ajustes 
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
        // -- 
    }
    
    public void setDefaultValues() // Posicion Inicial
    {
        worldX = gp.tileSize * 66 ; 
        worldY = gp.tileSize * 201 ; 
        speed = 4; 
        direction = "down"; 
    }
    
    // ---------------- Variables de prueba sobre las skins player -------------
    
    String front1[] = {"/player/F1","/NPC/CocheFront", "/player/F1", "/Agua/Down"}; 
    String front2[] = {"/player/F2","/NPC/CocheFront", "/player/F2", "/Agua/Down"}; 
    String right1[] = {"/player/R1","/NPC/CocheR1",  "/player/R1", "/Agua/Right"};  
    String right2[] = {"/player/R2","/NPC/CocheR2", "/player/R2", "/Agua/Right"}; 
    String left1[] = {"/player/L1","/NPC/CocheL1", "/player/L1", "/Agua/Left"}; 
    String left2[] = {"/player/L2","/NPC/CocheL2", "/player/L2", "/Agua/Left"}; 
    String back1[] = {"/player/F1","/NPC/CocheB", "/player/F1", "/Agua/Up"}; 
    String back2[] = {"/player/F2","/NPC/CocheB", "/player/F2", "/Agua/Up"}; 
    
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
                keyHandler.leftPressed == true || keyHandler.rightPressed == true  ){
            
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
            detectTile(); 
            
            // Check object Collision 
            objIndex = gp.cH.checkObject(this, true); 
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
    
    
    
    // ------------------ Prueba sobre la detección de las tiles ---------------
    public void detectTile(){
        
        // --------------------- Apariciones en a hierba -----------------------
        
        if(collision == false && gp.cH.tile.nombre.equals("5")){
            this.actionLocker++; 
            
            if(this.actionLocker == 17){
                gp.sonido.play(10, false, "effect");
                this.actionLocker = 0; 
            }
            if((gp.player.worldY > (170 * gp.tileSize) && gp.player.worldY < (191 * gp.tileSize)) && (gp.player.worldX < (71 * gp.tileSize) && gp.player.worldX > (57 * gp.tileSize))){                
                gp.apariciones_pokemon.grassBattle01(0);
            }else if((gp.player.worldY > (142 * gp.tileSize) && gp.player.worldY < (170 * gp.tileSize)) && (gp.player.worldX < (57 * gp.tileSize) && gp.player.worldX > (23 * gp.tileSize))){
                gp.apariciones_pokemon.grassBattle01(1);
            }else if((gp.player.worldY > (155 * gp.tileSize) && gp.player.worldY < (166 * gp.tileSize)) && (gp.player.worldX < (106 * gp.tileSize) && gp.player.worldX > (73 * gp.tileSize))){
                gp.apariciones_pokemon.grassBattle01(2);
            }
        }else if(collision == false && (gp.cH.tile.nombre.equals("1") || gp.cH.tile.nombre.equals("34") || gp.cH.tile.nombre.equals("35"))){
            this.actionLocker++; 
            if(this.actionLocker == 17){
                gp.sonido.play(14, false, "effect");
                this.actionLocker = 0; 
            }
        }
        
        
        // Interacción con objetos 
        
        if(collision == true && gp.cH.tile.nombre.equals("29")){
            gp.interacciones_objetos.showDialogue("Te encuentras en pueblo Paleta!");
            
        }
        else if(collision == true && gp.cH.tile.nombre.equals("33") && gp.cH.direction.equals("down")){
            gp.sonido.play(12, false, "effect");
            gp.player.worldX = gp.player.worldX; 
            gp.player.worldY = gp.player.worldY + gp.tileSize; 
        }
        else if(collision == true && gp.cH.tile.nombre.equals("25")&& gp.cH.direction.equals("up")){
            
            this.actionLocker++; 
            
            if(this.actionLocker == 17){
                gp.sonido.play(11, false, "effect");
                this.actionLocker = 0; 
                gp.nombres.actualizar_xp("vida", gp.player.seleccion_pokemon);
            }
        }
        else if(collision == true && (gp.cH.tile.nombre.equals("32") || gp.cH.tile.nombre.equals("31") || gp.cH.tile.nombre.equals("33"))){
            gp.cH.tile.collision = false; 
            // ---------- Ambas variables son necesarias para cambiar skin
            gp.skinAppereance = 3; 
            changeSkin = true; 
        }
        
        // --------------------- Salir de edificios ----------------------------
        
        else if(collision == true && gp.cH.tile.nombre.equals("12") && gp.cH.direction.equals("down") ){
            
            gp.fst.exitBuilding03(); // Salida de el laboratorio del inicio
             
        }else if(collision == true && gp.cH.tile.nombre.equals("22") && gp.cH.direction.equals("down")){
            gp.fst.exitPokemonCenter(); // Salida del centro pokemon 
        }     
        // --------------------- Entrada en los edificios ----------------------
        
        else if(collision == true && gp.cH.tile.nombre.equals("12") && gp.cH.direction.equals("up")){ // En caso de que entre al laboratorio 
            
            gp.fst.enterBuilding01();
            
        }
        else if(collision == true && gp.cH.tile.nombre.equals("22") && gp.cH.direction.equals("up")){  // Entrada al centro pokemon
           gp.fst.enterPokemonCenter(); 
        }
        else if(collision == true){
            //System.out.println("Con colisión " + gp.cH.tile.nombre);
        }else if(collision == false){
            //System.out.println("Sin colisión " + gp.cH.tile.nombre);
        }
        
    }
    
    
    
    
    // ------------------- Interacción del jugador con un objeto ---------------
   
    public void pickUpObject(int i){
        if(i != 999){
            
            String objectName = gp.obj[i].name; 
            
            switch(objectName){
                case "Pokeball": 
                    
                    int randomObject = random.nextInt(4) + 1 ; 
                    
                    gp.sonido.play(2, false, "effect"); 
                    
                    /*El objeto de cada pokeball es aleatorio*/
                    gp.ui.message = "Has encontrado un " + gp.object[randomObject]; 
                    gp.gameState = gp.dialogueState; 
                    
                    if(gp.object[randomObject].name.equals("Pokeball")){
                        this.hasPokeball++; 
                    }else if(gp.object[randomObject].name.equals("Llave")){
                        this.hasKey++; 
                    }else if(gp.object[randomObject].name.equals("MTHp")){
                        this.hasMTHp++; 
                    }else if(gp.object[randomObject].name.equals("MTAttack")){
                        this.hasMTAttack++; 
                    }
                    inventario.add(gp.object[randomObject]); 
                    /*else if(gp.object[randomObject].name.equals("Hacha")){
                        this.hasHacha++; 
                    }*/
                    
                    gp.obj[i].existe = 0; 
                    gp.obj[i] = null;
                    
                    

                    break; 
                    
                
                case "Hacha": 
                    //if(keyHandler.interactEntity == true){ // En el caso de que sea necesario pulsar para recoger
                        hasHacha++; 
                        inventario.add(gp.obj[i]);
                        //gp.showInventory = true;  Para poder mostrar por pantalla el objeto que se ha recogido
                        gp.obj[i] = null; 
                        
                        // ---------- Ambas variables son necesarias para cambiar skin
                        //gp.skinAppereance = 2; 
                        //changeSkin = true; 
                        // -----------------------------------------------------
                        
                    //}
                    break; 
                
                case "HierbaAlta" : 
                    if(this.hasHacha > 0){
                        gp.sonido.play(9, false, "effect");
                        gp.obj[i] = null; 
                    }
                    break; 
                case "PokeballPokemon": 
                    if(pokemon_inicial == false){
                        int numero[] = {149,151, 150}; 
                        int aparicion = random.nextInt(3); // Hay que poner exactamente el número de 

                        gp.player.sprite_bicho_attack = numero[aparicion]; 
                        gp.ui.lvl = random.nextInt(10) + 1; 
                        
                        String nombre = null; 
                        nombre = JOptionPane.showInputDialog("Elige un nombre"); 
                        gp.nombres.cargar_pokemon_capturado(nombre);
                        gp.nombres.cargar_pokemones_equipo();

                        gp.sonido.play(13, false, "effect"); // Poner efecto especial 
                        
                        gp.obj[i] = null; 
                        this.pokemon_inicial = true; 
                    }
                    break; 
               
            
            }
        }
    }
    
    // -------------------------------------------------------------------------
    
    
    // ------------------- Interacciones del Jugador con el NPC ----------------
    public int numero = 0; 
    public void interactNPC(int i){
        if(i != 999){
            
            String name = gp.npc[i].name; 
            
            switch(name){
                
                case "CocheJuan" : {
                    if(keyHandler.interactEntity == false){

                        //gp.playMusicOnce(4); // Para poder poner la música

                        gp.gameState = gp.dialogueState; 
                        int numeroAleatorio = random.nextInt(5) + 1;  

                        if(numeroAleatorio == 1){ // Existe una posibilidad de que desaparezca el NPC 

                            gp.npc[i] = null; 

                            // ---------- Ambas variables son necesarias para cambiar skin
                            gp.skinAppereance = 1; 
                            changeSkin = true; 
                            // ---------------------------------------------------------

                            gp.player.speed = 18; 
                        }

                    }
                    
                    break; 
                }
                
                case "ShopGuy" : {
                    if(keyHandler.interactEntity == false){
                    
                        gp.gameState = gp.shopMenu; 
                        
                    }
                    
                    break; 
                }
        
                
            }
            numero  = i; 
        }
    }
    
    // -------------------------------------------------------------------------
    

    
    // ------------ Función para dibujar las características del player --------
    
    public void draw(Graphics2D g2) 
    {
        if(changeSkin == true){ // Si no hago esto se realentiza bastante el juego 
            getPlayerImage(); // Ayuda a actualizar la skin del personaje y este pueda cambiar
            changeSkin = false; 
        }
        
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
            g2.drawImage(image,screenX,screenY,gp.tileSize, gp.tileSize,null); 
            
            
            // ----------- Para poder enseñar las colisiones del personaje -----
            if(this.keyHandler.showCollisions == true){
                g2.setColor(Color.red); 
                g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            }
            // -----------------------------------------------------------------
    }
    
}
