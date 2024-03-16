package com.mygdx.shooterengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class SceneManager extends Game 
{
    private static SceneManager sceneManager;
    private Texture[] sceneTexture;
    private Scene currentScene;
    public MainMenu mainMenu;
    
    SceneManager() 
    {
        mainMenu = new MainMenu(sceneManager);
        sceneTexture = new Texture[]{
            new Texture(Gdx.files.internal("ScreenImages\\Scene\\SpaceBackground(1).png")),
            new Texture(Gdx.files.internal("ScreenImages\\Scene\\SpaceBackground(2).png")),
        };
    }

    public static SceneManager GetInstance() 
    {
        if (sceneManager == null) 
        {
            sceneManager = new SceneManager();
        }
        return sceneManager;
    } 

    // Function responsibe for changing scene
    public void changeScene(Scene scene) 
    {
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
        else if (currentScene instanceof EndScene) 
        {
            AudioManager.GetInstance().PlayMusic("Audio\\GameOver.mp3");
        }
    }

    // Function to return the GameManager
    @Override
    public void create() 
    {

    }

    public void dispose() 
    {
        AudioManager.Dispose();
    }
}
