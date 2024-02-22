package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends Entity{

    Bullet(int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
        this.texture = new Texture("EntitySprites\\Bullet2.png");
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
