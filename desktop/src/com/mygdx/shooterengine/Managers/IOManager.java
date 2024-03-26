package com.mygdx.shooterengine.Managers;
import com.badlogic.gdx.Gdx;

public class IOManager {

    //Singleton instance variable
    private static IOManager instance;

    //check if a key is currently pressed
    public boolean isKeyPressed(int key)
    {
        return Gdx.input.isKeyPressed(key);
    }

    //check if a key is just pressed
    public boolean isKeyJustPressed(int key){
        return Gdx.input.isKeyJustPressed(key);
    }
    //Get singleton instance of IOManager
    public static IOManager GetInstance(){
        if(instance == null){
            //If instance is null, create a new instance
            instance = new IOManager();
        }
        // Return the singleton instance
        return instance;
    }

}