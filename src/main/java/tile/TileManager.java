/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.KeyHandler;
import com.mycompany.primerjuego2d.main.UtilityTool;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author pablo
 */
public class TileManager {
    
    GamePanel gp; 
    KeyHandler kH; 
    public Tile[] tile; 
    public int mapTileNum[][]; 
    ArrayList<String> fileNames = new ArrayList<>(); 
    ArrayList<String> collisionStatus = new ArrayList<>(); 
    
    
    // Modificar las colisiones de los pixeles
    public boolean hayColision = true; 
    public boolean noHayColision = false; 
    
    
    public TileManager( GamePanel gp, KeyHandler kH){
        this.gp = gp; 
        this.kH = kH; 
        
        // Leer información del "tile" 
        InputStream is = getClass().getResourceAsStream("/maps/collision.txt"); 
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
        
        String linea; 
        
        try {
            while((linea = br.readLine()) != null){
                fileNames.add(linea); 
                collisionStatus.add(br.readLine()); 
                
            }
            br.close(); 
        } catch (IOException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tile = new Tile[fileNames.size()]; // The number of tile that we will have 
        getTileImage(); 
        
        
        is = getClass().getResourceAsStream("/maps/MapaVF.txt"); 
        br = new BufferedReader(new InputStreamReader(is)); 
        
        try{
            String linea2 = br.readLine(); 
            String maxTile[] = linea2.split(" "); 
            gp.maxWorldCol = maxTile.length; 
            gp.maxWorldRow = maxTile.length; 
            
            mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; 
            br.close(); 
        }catch(IOException e){
            
        }
        
        
        
        
        loadMap("/maps/MapaVF.txt"); 
    }
    
    public void getTileImage()
    {
        for(int i = 0; i < fileNames.size(); i++){
            
            String fileName; 
            boolean collision; 
            
            fileName = fileNames.get(i); 
            if(collisionStatus.get(i).equals("true")){
                collision = true; 
            }else{
                collision = false; 
            }
            
            setUp(i, fileName, collision); 
            
        }
            
    }
    
    public void setUp(int index, String imagePath, boolean collsion){
        UtilityTool uTool = new UtilityTool(); 
        
        try{
            
            tile[index] = new Tile(); 
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/"+imagePath)); 
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize); 
            tile[index].collision = collsion; 
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(String path)
    {
        try{
            
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
            
            int col = 0; 
            int row = 0; 
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow ){
                String line = br.readLine(); 
                
                while(col < gp.maxWorldCol ){
                    String numbers[] = line.split(" "); 
                    
                    int num = Integer.parseInt(numbers[col]); 
                    mapTileNum[col][row] = num; 
                    col++; 
                }
                
                if(col == gp.maxWorldCol){
                    col = 0; 
                    row++; 
                }
            }
            
            br.close(); 
        }catch(Exception e){
            
        }
    }
    
    
    public void draw(Graphics2D g2)
    {
        int worldCol = 0; 
        int worldRow = 0; 

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow]; 
            
            int worldX = worldCol * gp.tileSize; 
            int worldY = worldRow * gp.tileSize; 
            int screenX = worldX - gp.player.worldX + gp.player.screenX; 
            int screenY = worldY - gp.player.worldY + gp.player.screenY; 
            
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                    
                g2.drawImage(tile[tileNum].image, screenX,screenY,gp.tileSize,gp.tileSize,null); 
            }
            
            // ----------- Para poder enseñar las colisiones del personaje -----
            if(this.kH.tileCollision == true){
                //System.out.println("hola");
                g2.setColor(Color.white); 
                g2.drawRect(screenX , screenY , gp.screenHeight, gp.screenWidth);
            }
            // -----------------------------------------------------------------

            worldCol++; 
            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0; 
                worldRow++;  
            }
        }
        
        
    }
}
