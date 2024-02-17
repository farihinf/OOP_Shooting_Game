package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.bullet.collision.Collision;

public class Bullet extends Entity{

    CollisionRect collisionRect = null;

    public Bullet(int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
        collisionRect = new CollisionRect(this.posX, this.posY, this.texture.getWidth(), this.texture.getHeight());
	}

    public void UpdateBullet()
    {
        collisionRect.attachRect(posX, posY);
        posY += speed * Gdx.graphics.getDeltaTime();
    }

    public void removeBullets(Bullet bullet)
    {
        if (posY > Gdx.graphics.getHeight())
        {
            //testing
        }
    }

    public CollisionRect GetCollsionRect()
    {
        return collisionRect;
    }


}
