/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import com.mycompany.primerjuego2d.main.GamePanel;
import java.awt.Graphics2D;
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
        tile = new Tile[10]; // The number of tile that we will have 
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow]; 
        
        getTileImage(); 
        loadMap("/maps/Mapa2.txt"); 
    }
    
    public void getTileImage()
    {
        try{
            
            /*
            tile[0] = new Tile(); 
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Lose.png")); 
            */
            
            /*Hierba*/
            tile[0] = new Tile(); 
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Hierba/HierbaNormal0.png")); 
            
            tile[1] = new Tile(); 
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Hierba/HierbaFlores1.png")); 
            
            tile[2] = new Tile(); 
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Hierba/HierbaAlta32.png")); 
            
            tile[3] = new Tile(); 
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Hierba/HierbaAlta23.png")); 
            
            tile[4] = new Tile(); 
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Hierba/HierbaAlta4.png")); 
            
            /*Arboles*/
            
            tile[5] = new Tile(); 
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Arboles/Arbol5.png")); 
            tile[5].collision = true; // Makes this tile unreacheble
            
            tile[6] = new Tile(); 
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Arboles/Arbol26.png")); 
            tile[6].collision = true; // Makes this tile unreacheble
            
            tile[7] = new Tile(); 
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Arboles/Arbol37.png")); 
            tile[7].collision = true; // Makes this tile unreacheble
            
            tile[8] = new Tile(); 
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Arboles/Arbol48.png")); 
            tile[8].collision = true; // Makes this tile unreacheble
            
            
            
            /*Agua*/
            
            tile[9] = new Tile(); 
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Water1.png")); 
            tile[9].collision = true; // Makes this tile unreacheble
            

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
                    String numbers[] = line.split(" "); 
                    
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
