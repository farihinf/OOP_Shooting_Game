package com.mygdx.shooterengine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class IOManager {

    public boolean CheckInput(int key)
    {
        if(key == Keys.SPACE){
            return Gdx.input.isKeyJustPressed(key);
        }
        return Gdx.input.isKeyPressed(key);
    }

}