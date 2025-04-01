/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import com.mycompany.primerjuego2d.main.GamePanel;
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
        try{
            
            /*
            tile[0] = new Tile(); 
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Lose.png")); 
            */
            
            /*Hierba*/
            /*
            BufferedImage scaleImage = new BufferedImage(gamePanel.tileSize, gamePanel.tileSize, tile[0].image.getType()); 
            Graphics2D g2 = scaleImage.createGraphics(); 
            g2.drawImage(tile[0].image , 0,0, gamePanel.tileSize, gamePanel.tileSize, null); 
            tile[0].image = scaleImage; 
            */
            tile[0] = new Tile(); 
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[1] = new Tile(); 
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[2] = new Tile(); 
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[3] = new Tile(); 
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[4] = new Tile(); 
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[5] = new Tile(); 
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[6] = new Tile(); 
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[7] = new Tile(); 
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[8] = new Tile(); 
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[9] = new Tile(); 
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[10] = new Tile(); 
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            
            tile[11] = new Tile(); 
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[12] = new Tile(); 
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[13] = new Tile(); 
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            tile[14] = new Tile(); 
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Cesped.png")); 
            
            /*Arboles*/
            
            tile[15] = new Tile(); 
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/ArbolManzanas.png")); 
            tile[15].collision = true; // Makes this tile unreacheble
            
            tile[16] = new Tile(); 
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/ArbolNormal.png")); 
            tile[16].collision = true; // Makes this tile unreacheble
            
            tile[17] = new Tile(); 
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/ArbolManzanasCaidas.png")); 
            tile[17].collision = true; // Makes this tile unreacheble
            
            
            
            
            /*Agua*/
            
            tile[19] = new Tile(); 
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/Entorno/Camino.png")); 
            tile[19].collision = true; // Makes this tile unreacheble
            

            tile[20] = new Tile(); 
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/Cosas/castle.png")); 
            tile[20].collision = true; // Makes this tile unreacheble
            
            
            
            // Césped con Camino a la izquierda
            tile[21] = new Tile(); 
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/Cesped/CespedCaminoIzq.png")); 
            tile[21].collision = false; 
            
            tile[22] = new Tile(); 
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/Cesped/CespedCaminoEntero.png")); 
            tile[22].collision = false; 
            
            tile[23] = new Tile(); 
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/Cesped/CespedCaminoDer.png")); 
            tile[23].collision = false; 
            
        }catch(IOException e){
            
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
