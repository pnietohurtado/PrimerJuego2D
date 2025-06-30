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
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author pablo
 */
public class TileManager {
    
    GamePanel gamePanel; 
    public Tile[] tile; 
    public int mapTileNum[][]; 
    ArrayList<String> fileNames = new ArrayList<>(); 
    ArrayList<String> collisionStatus = new ArrayList<>(); 
    
    // Modificar las colisiones de los pixeles
    public boolean hayColision = true; 
    public boolean noHayColision = false; 
    
    
    public TileManager( GamePanel gp){
        this.gamePanel = gp; 
        
        // Leer información del "tile" 
        
        tile = new Tile[30]; // The number of tile that we will have 
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow]; 
        
        getTileImage(); 
        loadMap("/maps/MapaVF.txt"); 
    }
    
    public void getTileImage()
    {

            
            /*Hierba*/
            
            setUp(0, "Cesped", noHayColision); 
            setUp(1, "Cesped", noHayColision); 
            setUp(2, "Cesped", noHayColision); 
            setUp(3, "Cesped", noHayColision); 
            setUp(4, "Cesped", noHayColision); 
            setUp(5, "Cesped", noHayColision); 
            setUp(6, "Cesped", noHayColision); 
            setUp(7, "Cesped", noHayColision); 
            setUp(8, "Cesped", noHayColision); 
            setUp(9, "Cesped", noHayColision); 
            setUp(10, "Cesped", noHayColision); 
            
            
            /*Arboles*/
            
            setUp(15, "ArbolManzanas", hayColision); 
            
            setUp(16, "ArbolNormal", hayColision); 
            
            setUp(17, "ArbolManzanasCaidas", hayColision); 
            
            
            
            
            /*Camino*/
            
            setUp(19, "Camino", noHayColision); 
            

            
            
            
            // Césped con Camino a la izquierda
            setUp(21, "CespedCaminoIzq", noHayColision); 
            setUp(22, "CespedCaminoEntero", noHayColision); 
            setUp(23, "CespedCaminoDer", noHayColision); 
            
            
            // Montaña para poder añadir relieve en el mapa
            setUp(24, "MontañaFront", hayColision); 
       
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
