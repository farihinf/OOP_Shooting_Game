package com.mygdx.shooterengine.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends Entity{

	// Inheritance from Entity
    public Bullet(int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
        this.texture = new Texture("EntitySprites\\Entity\\Bullet2.png");
        this.posX -= this.texture.getWidth() / 2;
	}


	// Update the position of the bullet
    public void UpdateBullet(int direction)
    {
        this.collisionRect.attachRect(posX, posY);
        posY += direction * speed * Gdx.graphics.getDeltaTime();
    }

	// Check the position of the bullet, if bullet is out of the map, it will be removed. 
    public boolean OutOfBounds()
    {
        if (posY > Gdx.graphics.getHeight() || posY < 0){
            return true;
        }

        return false;
    }


}
