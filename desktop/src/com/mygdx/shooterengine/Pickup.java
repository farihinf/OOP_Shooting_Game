package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pickup extends Entity {
    private float lifetimeTimer = 5f;
    private PickupType pickupType;
    private boolean toRemove;
    
    Pickup(int damage, float speed, Texture texture, SpriteBatch sb, float x, float y, PickupType pickupType) {
        super(damage, speed, texture, sb, x, y);
        lifetimeTimer = 5f;
        this.pickupType = pickupType;
        toRemove = false;   
    }

    public void Update(){
        lifetimeTimer -= Gdx.graphics.getDeltaTime();
        posY -= 50 * Gdx.graphics.getDeltaTime();
        collisionRect.attachRect(posX, posY);
        if(lifetimeTimer <= 0){
            toRemove = true;
        }
    }

    public boolean ToRemove(){
        return toRemove;
    }

    public PickupType getPickupType(){
        return pickupType;
    }
    
}

enum PickupType{
    HEALTH, DAMAGE, SPEED
}
