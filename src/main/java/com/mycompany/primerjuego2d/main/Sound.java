/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author pablo
 */
public class Sound {
    
    Clip clip; 
    URL soundURL[] = new URL[10]; 
    
    
    public Sound(){
        soundURL[1] = getClass().getResource("/sonido/Music.wav"); 
    }
    
    
    public void setFile(int i){
        try{
            try(AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i])){
                System.out.println("Funciona");
            } catch(Exception e){
                System.out.println("No va");
            }
            
            clip = AudioSystem.getClip(); 
            System.out.println("clip -> "+ clip);
            
            //clip.open(ais); 
        }catch(Exception e){
            
        }
    }
    
    public void play(){
        if(clip != null ){
            clip.start();
        }
    }
    
    public void loop(){
        if(clip != null){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    public void stop(){
        clip.stop();
    }
    
}
