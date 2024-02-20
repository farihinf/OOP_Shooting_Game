package com.mygdx.shooterengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneManager extends Game
{
    private Scene currentScene;
    private Texture background;
    private GameManager gameManager;
    public MainMenu mainmenu;
    
    SceneManager(SpriteBatch batch, GameManager gameManager)
    {
    	mainmenu = new MainMenu(this);
        this.gameManager = gameManager;
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
        
        if (currentScene instanceof MainMenu)
        {
            AudioManager.GetInstance().PlayMusic("Audio\\MainMenuMusic.mp3");
        }
        else if (currentScene instanceof GameScreen) 
        {
            AudioManager.GetInstance().PlayMusic("Audio\\GameMusic.mp3");
        }
        else
        {
            AudioManager.GetInstance().PlayMusic("Audio\\GameOver.mp3");
        }
    }

    public GameManager getGameManager()
    {
        return gameManager;
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
        AudioManager.Dispose();
	}
}

