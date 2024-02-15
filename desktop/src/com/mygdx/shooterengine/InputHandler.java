package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputHandler {
    private static PlayerControlManager playerControlManager;

    public static void setPlayerControl(PlayerControlManager pcm) {
        playerControlManager = pcm;
    }

    public static void checkInput() {
        boolean moveLeft = Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A);
        boolean moveRight = Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D);
        boolean moveUp = Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W);
        boolean moveDown = Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S);

        //call playercontrol class
        playerControlManager.Move(moveLeft, moveRight, moveUp, moveDown);
        
       
    }
}