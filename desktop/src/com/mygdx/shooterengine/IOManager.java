package com.mygdx.shooterengine;

import java.security.Key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class IOManager {

    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean moveUp = false;
    private boolean moveDown = false;

    private boolean shooting = false;


    public void checkInput() {
        moveLeft = Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A);
        moveRight = Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D);
        moveUp = Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W);
        moveDown = Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S);
    }

    public boolean IsShooting()
    {
        return Gdx.input.isKeyJustPressed(Keys.SPACE);
    }

    public boolean moveLeft()
    {
        return moveLeft;
    }

    public boolean moveRight()
    {
        return moveRight;
    }

    public boolean moveUp()
    {
        return moveUp;
    }

    public boolean moveDown()
    {
        return moveDown;
    }
}