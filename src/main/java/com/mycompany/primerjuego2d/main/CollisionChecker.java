/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import Entity.Entity;
import java.awt.Rectangle;

/**
 *
 * @author pablo
 */
public class CollisionChecker {

    public GamePanel gp; 
    
    public CollisionChecker(GamePanel gp){
        this.gp = gp; 
    }
    
    
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x; 
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width; 
        int entityTopWorldY = entity.worldY + entity.solidArea.y; 
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; 
        
        int entityLeftCol = entityLeftWorldX/gp.tileSize; 
        int entityRightCol = entityRightWorldX/gp.tileSize; 
        int entityTopRow = entityTopWorldY/gp.tileSize; 
        int entityBottomRow = entityBottomWorldY/gp.tileSize; 
        
        int tileNum1, tileNum2; 
        
        switch(entity.direction){
            case "up": 
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize; 
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow]; 
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow]; 
                if(gp.tileManager.tile[tileNum1].collision == true ||gp.tileManager.tile[tileNum2].collision == true){
                    entity.collision = true; 
                    //System.out.println("Choque con un tile arriba");
                    
                    
                    
                }
                break; 
            case "down": 
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize; 
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; 
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow]; 
                if(gp.tileManager.tile[tileNum1].collision == true ||gp.tileManager.tile[tileNum2].collision == true){
                    entity.collision = true; 
                }
                break;
            case "left": 
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize; 
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow]; 
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; 
                if(gp.tileManager.tile[tileNum1].collision == true ||gp.tileManager.tile[tileNum2].collision == true){
                    entity.collision = true; 
                }
                break; 
            case "right": 
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize; 
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow]; 
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow]; 
                if(gp.tileManager.tile[tileNum1].collision == true ||gp.tileManager.tile[tileNum2].collision == true){
                    entity.collision = true; 
                }
                break; 
        }
    }
    
    
    
    
    // Collisions for the objects 
    public int checkObject(Entity entity, boolean player ){
        int index = 999; 
        
        for(int i = 0; i < gp.obj.length ; i++){
            if(gp.obj[i] != null){
                // Get entity's solid area position 
                entity.solidArea.x = entity.worldX + entity.solidArea.x; 
                entity.solidArea.y = entity.worldY + entity.solidArea.y; 
                
                // Get the object's solid position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                

                
                switch(entity.direction){
                    case "up" : 
                        entity.solidArea.y -= entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea) /*|| (entity.nearSolidArea = new Rectangle (0,0,500,500)).intersects(gp.obj[i].solidArea)*/){
                            if(gp.obj[i].collision == true){
                                entity.collision = true ;
                            }
                            if(player == true){
                                index = i; 
                            }
                        }
                        break; 
                    case "down" : 
                        entity.solidArea.y += entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collision = true ;
                            }
                            if(player == true){
                                index = i; 
                            }
                        }
                        break; 
                    case "left" : 
                        entity.solidArea.x -= entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collision = true ;
                            }
                            if(player == true){
                                index = i; 
                            }
                        }
                        break; 
                    case "right" : 
                        entity.solidArea.x += entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collision = true ;
                            }
                            if(player == true){
                                index = i; 
                            }
                        }
                        break; 
                }
                entity.solidArea.x = entity.solidAreaDefaultX; 
                entity.solidArea.y = entity.solidAreaDefaultY; 
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        
        return index; 
    }
    
    // Colisión del NPC con el jugador
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999; 
        
        for(int i = 0; i < target.length ; i++){
            if(target[i] != null){
                // Get entity's solid area position 
                entity.solidArea.x = entity.worldX + (entity.solidArea.x); 
                entity.solidArea.y = entity.worldY + (entity.solidArea.y); 
                
                // Get the object's solid position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
                

                
                switch(entity.direction){
                    case "up" : 
                        entity.solidArea.y -= entity.speed; 
                        if(entity.solidArea.intersects(target[i].solidArea) /*|| (entity.nearSolidArea = new Rectangle (0,0,500,500)).intersects(gp.obj[i].solidArea)*/){
                            entity.collision = true ;
                            index = i; 
                            
                        }
                        break; 
                    case "down" : 
                        entity.solidArea.y += entity.speed; 
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true ;
                            index = i; 
                            
                        }
                        break; 
                    case "left" : 
                        entity.solidArea.x -= entity.speed; 
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true ;
                            index = i; 
                            
                        }
                        break; 
                    case "right" : 
                        entity.solidArea.x += entity.speed; 
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true ;
                            index = i; 
                            
                        }
                        break; 
                }
                entity.solidArea.x = entity.solidAreaDefaultX; 
                entity.solidArea.y = entity.solidAreaDefaultY; 
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        
        return index; 
    }
    
    
    
    
    // Colisión del jugador con el NPC
    public boolean collisionPlayer = false; 
    public void checkPlayer(Entity entity){
   
                // Get entity's solid area position 
                entity.solidArea.x = entity.worldX + entity.solidArea.x; 
                entity.solidArea.y = entity.worldY + entity.solidArea.y; 
                
                // Get the object's solid position
                gp.player.solidArea.x = (gp.player.worldX + gp.player.solidArea.x);
                gp.player.solidArea.y = (gp.player.worldY + gp.player.solidArea.y);

                
                switch(entity.direction){
                    case "up" : 
                        entity.solidArea.y -= entity.speed; 
                        if(entity.solidArea.intersects(gp.player.solidArea)  /*|| (entity.nearSolidArea = new Rectangle (0,0,500,500)).intersects(gp.obj[i].solidArea)*/){
                            entity.collision = true ;
                            collisionPlayer = true;
                           
                            
                        }
                        break; 
                    case "down" : 
                        entity.solidArea.y += entity.speed; 
                        if(entity.solidArea.intersects(gp.player.solidArea)){
                            entity.collision = true ;
                            collisionPlayer = true;
                            
                        }
                        break; 
                    case "left" : 
                        entity.solidArea.x -= entity.speed; 
                        if(entity.solidArea.intersects(gp.player.solidArea)){
                            entity.collision = true ;
                            collisionPlayer = true;
                            
                        }
                        break; 
                    case "right" : 
                        entity.solidArea.x += entity.speed; 
                        if(entity.solidArea.intersects(gp.player.solidArea)){
                            entity.collision = true ;
                            collisionPlayer = true;
                            
                        }
                        break; 
                }
                entity.solidArea.x = entity.solidAreaDefaultX; 
                entity.solidArea.y = entity.solidAreaDefaultY; 
                gp.player.solidArea.x = gp.player.solidAreaDefaultX;
                gp.player.solidArea.y = gp.player.solidAreaDefaultY;
                
              
    }
    

}
