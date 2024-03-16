package com.mygdx.shooterengine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteBatchSingleton
{
    private static SpriteBatch spriteBatchInstance;

    // Private constructor to prevent instantiation from outside
    private SpriteBatchSingleton() 
    {
    }

    // Method to get the singleton instance of SpriteBatch
    public static SpriteBatch getInstance() 
    {
        if (spriteBatchInstance == null) {
            spriteBatchInstance = new SpriteBatch();
        }
        return spriteBatchInstance;
    }
}
