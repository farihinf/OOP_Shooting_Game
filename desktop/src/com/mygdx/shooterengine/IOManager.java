package com.mygdx.shooterengine;
import com.badlogic.gdx.Gdx;

public class IOManager {

    private static IOManager instance;

    public boolean isKeyPressed(int key)
    {
        return Gdx.input.isKeyPressed(key);
    }

    public boolean isKeyJustPressed(int key){
        return Gdx.input.isKeyJustPressed(key);
    }

    public static IOManager GetInstance(){
        if(instance == null){
            instance = new IOManager();
        }
        return instance;
    }

}