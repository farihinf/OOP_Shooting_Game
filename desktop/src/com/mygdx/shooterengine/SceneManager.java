package com.mygdx.shooterengine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;

public class SceneManager extends Game
{
    private Scene currentScene;
    private Texture background;
    private SpriteBatch batch;
    public MainMenu mainmenu;
    
    SceneManager(SpriteBatch batch)
    {
    	this.batch = batch;
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

