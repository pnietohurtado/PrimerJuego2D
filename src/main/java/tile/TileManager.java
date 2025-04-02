/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import com.mycompany.primerjuego2d.main.GamePanel;
import com.mycompany.primerjuego2d.main.UtilityTool;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author pablo
 */
public class TileManager {
    
    GamePanel gamePanel; 
    public Tile[] tile; 
    public int mapTileNum[][]; 
    
    public TileManager( GamePanel gp){
        this.gamePanel = gp; 
        tile = new Tile[30]; // The number of tile that we will have 
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow]; 
        
        getTileImage(); 
        loadMap("/maps/Mapa.txt"); 
    }
    
    public void getTileImage()
    {

            
            /*Hierba*/
            
            setUp(0, "Cesped", false); 
            setUp(1, "Cesped", false); 
            setUp(2, "Cesped", false); 
            setUp(3, "Cesped", false); 
            setUp(4, "Cesped", false); 
            setUp(5, "Cesped", false); 
            setUp(6, "Cesped", false); 
            setUp(7, "Cesped", false); 
            setUp(8, "Cesped", false); 
            setUp(9, "Cesped", false); 
            setUp(10, "Cesped", false); 
            
            
            /*Arboles*/
            
            setUp(15, "ArbolManzanas", true); 
            
            setUp(16, "ArbolNormal", true); 
            
            setUp(17, "ArbolManzanasCaidas", true); 
            
            
            
            
            /*Camino*/
            
            setUp(19, "Camino", false); 
            

            
            
            
            // Césped con Camino a la izquierda
            setUp(21, "CespedCaminoIzq", false); 
            setUp(22, "CespedCaminoEntero", false); 
            setUp(23, "CespedCaminoDer", false); 
       
    }
    
    public void setUp(int index, String imagePath, boolean collsion){
        UtilityTool uTool = new UtilityTool(); 
        
        try{
            
            tile[index] = new Tile(); 
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/"+imagePath+".png")); 
            tile[index].image = uTool.scaleImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize); 
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
            
            while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow ){
                String line = br.readLine(); 
                
                while(col < gamePanel.maxWorldCol ){
                    String numbers[] = line.split("   "); 
                    
                    int num = Integer.parseInt(numbers[col]); 
                    mapTileNum[col][row] = num; 
                    col++; 
                }
                
                if(col == gamePanel.maxWorldCol){
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

        while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow]; 
            
            int worldX = worldCol * gamePanel.tileSize; 
            int worldY = worldRow * gamePanel.tileSize; 
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX; 
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY; 
            
            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                    
                g2.drawImage(tile[tileNum].image, screenX,screenY,gamePanel.tileSize,gamePanel.tileSize,null); 
            }

            worldCol++; 
            
            if(worldCol == gamePanel.maxWorldCol){
                worldCol = 0; 
                worldRow++;  
            }
        }
        
        
    }
}
