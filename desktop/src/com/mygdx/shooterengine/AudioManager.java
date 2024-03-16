package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;

// Import Audio Library from LibGDX
import com.badlogic.gdx.backends.lwjgl3.audio.Mp3.Music;
import com.badlogic.gdx.backends.lwjgl3.audio.Mp3.Sound;

public class AudioManager implements AudioAsset{

    private Music currentMusic;
    private Sound soundEffect;
    
    private static AudioManager instance = null;

    // Function for play music
    @Override
    public void PlayMusic(String filePath)
     {
        if(currentMusic != null){
            currentMusic.stop();
        }
        currentMusic = (Music) Gdx.audio.newMusic(Gdx.files.internal(filePath));
        currentMusic.setLooping(true);  
        currentMusic.play();     
    }

    // Function for stop music
    @Override
    public void StopMusic() {
        if(currentMusic.isPlaying()){
            currentMusic.stop();
        }
    }

    // Function for audio to play on different instances
    public static AudioManager GetInstance(){
        if(instance == null){
            instance = new AudioManager();
        }
        return instance;
    }

    public void AdjustVolume(float volume){
        if(currentMusic != null)
        {
            currentMusic.setVolume(volume);
        }
    }

    public void GetVolume()
    {
        if(currentMusic != null)
        {
            currentMusic.getVolume();
        }
    }


    // Function for path of the audio file
    @Override
    public void PlaySound(String filePath) 
    {
        soundEffect = (Sound) Gdx.audio.newSound(Gdx.files.internal(filePath));
        soundEffect.play();
    }

    // Dispose of the Audio on different instances 
    public static void Dispose(){
        if(instance != null){
            if(instance.currentMusic != null){
                instance.currentMusic.stop();
                instance.currentMusic.dispose();
            }

            if(instance.soundEffect != null){
                instance.soundEffect.stop();
                instance.soundEffect.dispose();
            }
        }

    }
    
}
