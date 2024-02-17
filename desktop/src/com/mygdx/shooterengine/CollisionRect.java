package com.mygdx.shooterengine;

public class CollisionRect {

    private float x, y;
    private int width, height;
    private boolean collidedWith;

    public CollisionRect(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collidedWith = false;
    }

    public void attachRect(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean CollidesWith(CollisionRect rect)
    {
        if(!collidedWith)
        {    
            if(x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y)
            {
                return collidedWith = true;
            }         
        }
        return false;
    }
}
