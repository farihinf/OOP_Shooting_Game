package com.mygdx.shooterengine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerControlManager extends Entity{

	private float currX;

	public PlayerControlManager(int health, int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
		currX = getX();
	}
	
	public void Move(boolean moveLeft, boolean moveRight, boolean moveUp, boolean moveDown) {

        if (moveLeft) {
            //playermoveLeft
			setX(currX -= speed * Gdx.graphics.getDeltaTime());
        }
        if (moveRight) {
			//playermoveRight
			setX(currX += speed * Gdx.graphics.getDeltaTime());
        }
        if (moveUp) {
			//playermoveUp
			setY(getY() + speed * Gdx.graphics.getDeltaTime());
        }
        if (moveDown) {
			//playermoveDown
			setY(getY() - speed * Gdx.graphics.getDeltaTime());
        }

    }
}
