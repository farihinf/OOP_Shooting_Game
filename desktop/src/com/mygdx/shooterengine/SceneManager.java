package com.mygdx.shooterengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class SceneManager extends Game 
{
    private SceneManager sceneManager;
    private Scene currentScene;
    public MainMenu mainMenu;

    SceneManager() 
    {
        mainMenu = new MainMenu(sceneManager);
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
