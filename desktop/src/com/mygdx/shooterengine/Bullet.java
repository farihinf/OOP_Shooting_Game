package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends Entity{

    //CollisionRect collisionRect = null;

    Bullet(int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
        //collisionRect = new CollisionRect(this.posX, this.posY, this.texture.getWidth(), this.texture.getHeight());
	}

    public void UpdateBullet(int direction)
    {
        this.collisionRect.attachRect(posX, posY);
        posY += direction * speed * Gdx.graphics.getDeltaTime();
    }

    public boolean OutOfBounds()
    {
        if (posY > Gdx.graphics.getHeight() || posY < 0){
            return true;
        }

        return false;
    }


}
