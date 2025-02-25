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
    Tile[] tile; 
    int mapTileNum[][]; 
    
    public TileManager( GamePanel gp){
        this.gamePanel = gp; 
        tile = new Tile[10]; // The number of tile that we will have 
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow]; 
        
        getTileImage(); 
        loadMap("/maps/Map1.txt"); 
    }
    
    public void getTileImage()
    {
        try{
            
            tile[0] = new Tile(); 
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Lose.png")); 
            
            tile[1] = new Tile(); 
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Grass.png")); 
            
            tile[2] = new Tile(); 
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Water.png")); 
            
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
            
            while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow ){
                String line = br.readLine(); 
                
                while(col < gamePanel.maxScreenCol ){
                    String numbers[] = line.split(" "); 
                    
                    int num = Integer.parseInt(numbers[col]); 
                    mapTileNum[col][row] = num; 
                    col++; 
                }
                
                if(col == gamePanel.maxScreenCol){
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
        int col = 0; 
        int row = 0; 
        int x = 0; 
        int y = 0; 
        
        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow)
        {
            int tileNum = mapTileNum[col][row]; 
            
            g2.drawImage(tile[tileNum].image, x,y,gamePanel.tileSize,gamePanel.tileSize,null); 
            col++; 
            x += gamePanel.tileSize; 
            
            if(col == gamePanel.maxScreenCol){
                col = 0; 
                x = 0; 
                row++; 
                y += gamePanel.tileSize; 
            }
        }
        
        
    }
}
