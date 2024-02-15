package com.mygdx.shooterengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//import com.badlogic.gdx.Gdx;

public class SceneManager extends Game
{
    private Scene currentScene;
    private Texture background;
    public MainMenu mainmenu;
    
    SceneManager(SpriteBatch batch)
    {
    	mainmenu = new MainMenu(this);
    }
    
    public void changeScene(Scene scene) 
	{
		if (currentScene != null) 
    	{
            currentScene.dispose();
        }
    	 currentScene = scene;
         currentScene.show();
         setScreen((Screen) currentScene);
    }
    
    @Override
	public void create() 
    {
		
	}
    
	public void dispose() 
	{
		if (background != null) 
		{
	        background.dispose();
	    }    
	}
}

