/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.primerjuego2d.main;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author pablo
 */
public class Sound {
    
    //Clip clip; 
    URL soundURL[] = new URL[100]; 
    private final Map<Integer, Clip> clips = new HashMap<>();
    public float volume; 
    
    public Sound(){ 
        
        soundURL[1] = getClass().getResource("/sonido/ThemeSong.wav"); 
        soundURL[2] = getClass().getResource("/sonido/Coger.wav"); 
        soundURL[3] = getClass().getResource("/sonido/EntrarMenu.wav"); 
        soundURL[4] = getClass().getResource("/sonido/Hablando.wav"); 
        soundURL[5] = getClass().getResource("/sonido/pokemon_battle.wav"); // Batalla pokemon 
        soundURL[6] = getClass().getResource("/sonido/BattleMewTwo.wav");
        soundURL[7] = getClass().getResource("/sonido/MainTheme.wav");
        soundURL[8] = getClass().getResource("/sonido/Ataque_Normal.wav");
        soundURL[9] = getClass().getResource("/sonido/Ataque_Critico.wav");
        soundURL[10] = getClass().getResource("/sonido/Pisar_Hierba.wav");
        this.volume = -20.0f; 
    }
    
    
    public void play(int i, boolean loop){
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i])) {
            Clip newClip = AudioSystem.getClip();
            newClip.open(ais);

            FloatControl audioVolume = (FloatControl) newClip.getControl(FloatControl.Type.MASTER_GAIN);
            audioVolume.setValue(volume);

            if (loop) {
                newClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                newClip.start();
            }

            clips.put(i, newClip); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
    
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
    
    */
    public void stop(int i) {
        Clip c = clips.get(i);
        if (c != null && c.isRunning()) {
            c.stop();
            c.close();
        }
    }

    
    
}
