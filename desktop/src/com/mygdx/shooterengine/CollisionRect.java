package com.mygdx.shooterengine;

public class CollisionRect {

    private float x, y;
    private int width, height;
    private boolean collidedWith;

    //Constructor to create the collision box
    CollisionRect(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collidedWith = false;
    }

    //Update the Collision Box's Position with whatever it is attached with
    public void attachRect(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    //Function to check if 2 collision rects collide with each other, returning a boolean
    public boolean CollidesWith(CollisionRect rect)
    {
        if(!collidedWith)
        {    
            //Checks whether the position of the current collision box is within the area of the comparing collision box
            if(x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y)
            {
                return collidedWith = true;
            }       
        }
        return false;
    }
}
