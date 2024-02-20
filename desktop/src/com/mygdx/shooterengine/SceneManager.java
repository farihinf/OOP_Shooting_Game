package com.mygdx.shooterengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneManager extends Game
{
    private Scene currentScene;
    private GameManager gameManager;
    
    SceneManager(SpriteBatch batch, GameManager gameManager)
    {
        this.gameManager = gameManager;
    }

    // Function responsibe for changing scene
    public void changeScene(Scene scene) 
	{
        // If current scene is not null, then it'll be dispose
		if (currentScene != null) 
    	{
            currentScene.dispose();
        }
    	 currentScene = scene;
         setScreen((Screen) currentScene);
        
        // Get the corresponding audio according to the type of Scene
        if (currentScene instanceof MainMenu)
        {
            AudioManager.GetInstance().PlayMusic("Audio\\MainMenuMusic.mp3");
        }
        else if (currentScene instanceof GameScene) 
        {
            AudioManager.GetInstance().PlayMusic("Audio\\GameMusic.mp3");
        }
        else
        {
            AudioManager.GetInstance().PlayMusic("Audio\\GameOver.mp3");
        }
    }

    // Function to return the GameManager
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
        AudioManager.Dispose();
	}
}

