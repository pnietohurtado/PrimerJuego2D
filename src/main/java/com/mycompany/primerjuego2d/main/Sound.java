/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author pablo
 */
public class Sound {
    
    Clip clip; 
    URL soundURL[] = new URL[10]; 
    public float volume; 
    
    public Sound(){
        soundURL[1] = getClass().getResource("/sonido/ThemeSong.wav"); 
        soundURL[2] = getClass().getResource("/sonido/Coger.wav"); 
        soundURL[3] = getClass().getResource("/sonido/EntrarMenu.wav"); 
        soundURL[4] = getClass().getResource("/sonido/Hablando.wav"); 
        this.volume = -20.0f; 
    }
    
    
    public void setFile(int i){
        try{
            try(AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i])){
            clip = AudioSystem.getClip(); 
            clip.open(ais); 
            
            FloatControl audioVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); 
            audioVolume.setValue(volume);
            
            } catch(Exception e){

            }

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

    @Override
    public String toString() {
        return "Sound{" + "soundURL=" + clip + '}';
    }
    
    
    
}
